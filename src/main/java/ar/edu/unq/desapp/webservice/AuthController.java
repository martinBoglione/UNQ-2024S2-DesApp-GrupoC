package ar.edu.unq.desapp.webservice;

import ar.edu.unq.desapp.helpers.aspects.LogExecutionTime;
import ar.edu.unq.desapp.model.dto.LoginResponseDto;
import ar.edu.unq.desapp.model.dto.LoginUserDto;
import ar.edu.unq.desapp.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unq.desapp.service.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication API")
public class AuthController {

    private final JwtUtil jwtService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(JwtUtil jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @LogExecutionTime
    @Operation(summary = "User login with email and password")
    @ApiResponse(responseCode = "200", description = "User logged in successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDto.class)))
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto) {
        UserDetails authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        return ResponseEntity.ok(new LoginResponseDto(jwtToken, jwtService.getExpirationTime()));

    }

}
