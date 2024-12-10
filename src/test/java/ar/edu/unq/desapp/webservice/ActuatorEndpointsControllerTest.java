package ar.edu.unq.desapp.webservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ActuatorEndpointsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void actuatorIsAccessible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/actuator"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void prometheusIsAccessible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/actuator/metrics"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void eastereggIsAccessible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/actuator/easteregg"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}