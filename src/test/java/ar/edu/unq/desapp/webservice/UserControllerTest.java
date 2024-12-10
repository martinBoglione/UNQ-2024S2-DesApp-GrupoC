package ar.edu.unq.desapp.webservice;

import ar.edu.unq.desapp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import ar.edu.unq.desapp.model.User;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    void createValidUser() throws Exception {

        User user = User.builder().name("Sub").surname("Zero").email("subzero@mortalkombat.com").address("Midway Games")
                .password("SomePassword123!").cvu("1234567550123456789012").walletAddress("0xw9y2di")
                .build();

        Mockito.when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("subzero@mortalkombat.com"))
                .andExpect(jsonPath("$.walletAddress").value("0xw9y2di"));
    }

    @Test
    void cannotCreateUserWithShortCVU() throws Exception {

        User user = User.builder().name("Sub").surname("Zero").email("subzero@mortalkombat.com").address("Midway Games")
                .password("SomePassword123!").cvu("567550123456789012").walletAddress("0xw9y2di")
                .build();

        Mockito.when(userService.createUser(any(User.class))).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "The CVU length must be 22 characters"));

        mockMvc.perform(post("/api/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("The CVU length must be 22 characters"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void gettingAllRegisteredUsers() throws Exception {
        User user1 = User.builder().name("Hanzo").surname("Hasashi").email("scorpion@mortalkombat.com").address("Midway Games")
                .password("PassForUserOne1").cvu("1234567550123456789012").walletAddress("0xw9y2di")
                .operationsQuantity(0).reputation(0).build();

        User user2 = User.builder().name("Liu").surname("Kang").email("liukang@mortalkombat.com").address("Midway Games")
                .password("PassForUserTwo2").cvu("1234567660123456789012").walletAddress("0xm2y9wi")
                .operationsQuantity(0).reputation(0).build();

        Mockito.when(userService.getAllUsers()).thenReturn(List.of(user1, user2));

        mockMvc.perform(get("/api/users/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("scorpion@mortalkombat.com"))
                .andExpect(jsonPath("$[1].email").value("liukang@mortalkombat.com"));
    }


    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void gettingUserWithIdHundred() throws Exception {
        User user1 = User.builder().id(100L).name("Hanzo").surname("Hasashi").email("scorpion@mortalkombat.com").address("Midway Games")
                .password("PassForUserOne1").cvu("1234567550123456789012").walletAddress("0xw9y2di")
                .operationsQuantity(0).reputation(0).build();

        Mockito.when(userService.getUserById(any(Long.class))).thenReturn(user1);

        mockMvc.perform(get("/api/users/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("scorpion@mortalkombat.com"))
                .andExpect(jsonPath("$.id").value("100"));
    }

}