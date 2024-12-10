package ar.edu.unq.desapp.webservice;

import ar.edu.unq.desapp.model.dto.LoginUserDto;
import ar.edu.unq.desapp.security.JwtUtil;
import ar.edu.unq.desapp.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    void loginWithInexistentUserFailed() throws Exception {
        LoginUserDto loginUserDTO = new LoginUserDto("nonexistent@email.com", "ItDoesnMatterPass");

        Mockito.when(authenticationService.authenticate(any(LoginUserDto.class)))
                .thenThrow(new UsernameNotFoundException("User not found"));

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginUserDTO)))
                .andExpect(status().isForbidden());
    }

    @Test
    void loginSuccessWithSubZero() throws Exception {
        LoginUserDto loginUserDTO = new LoginUserDto("subzero@mortalkombat.com", "contraseniaDeTest");

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername("subzero@mortalkombat.com")
                .password("contraseniaDeTest")
                .roles("USER")
                .build();

        String mockToken = "test-jwt-token";

        Mockito.when(authenticationService.authenticate(any(LoginUserDto.class)))
                        .thenReturn(userDetails);

        Mockito.when(jwtUtil.generateToken(userDetails)).thenReturn(mockToken);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginUserDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("test-jwt-token"));
    }
}