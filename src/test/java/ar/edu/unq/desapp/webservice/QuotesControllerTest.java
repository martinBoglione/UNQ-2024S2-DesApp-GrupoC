package ar.edu.unq.desapp.webservice;

import ar.edu.unq.desapp.model.Crypto;
import ar.edu.unq.desapp.service.BinanceCryptoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuotesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BinanceCryptoService binanceCryptoService;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void getLastQuoteForMatic() throws Exception {
        Crypto builderQuote = Crypto.builder()
                .symbol("MATICUSDT")
                .price(159.25F)
                .lastUpdateDateAndTime("03/04/2012 06:15:55")
                .build();

        Mockito.when(binanceCryptoService.getCryptoCurrencyValue(any(String.class))).thenReturn(builderQuote);

        mockMvc.perform(get("/api/quotes/BTCUSDT"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.symbol").value("MATICUSDT"));
    }
}