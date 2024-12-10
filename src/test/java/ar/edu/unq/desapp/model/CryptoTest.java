package ar.edu.unq.desapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CryptoTest {

    @Test
    void instanceCreationIsCorrect() {
        Crypto myQuote = new Crypto("MATICUSDT", 159.25F, "03/04/2012 06:15:55");
        assertEquals(CryptoAsset.MATICUSDT.name(), myQuote.getSymbol());
        assertEquals(159.25F, myQuote.getPrice());
        assertEquals("03/04/2012 06:15:55", myQuote.getLastUpdateDateAndTime());
    }

    @Test
    void quotesWithSameDataAreEquals() {
        Crypto myQuote = new Crypto("MATICUSDT", 159.25F, "03/04/2012 06:15:55");
        Crypto builderQuote = Crypto.builder()
                .symbol("MATICUSDT")
                .price(159.25F)
                .lastUpdateDateAndTime("03/04/2012 06:15:55")
                .build();
        assertEquals(myQuote, builderQuote);
    }

}