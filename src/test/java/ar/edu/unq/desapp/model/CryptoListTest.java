package ar.edu.unq.desapp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CryptoListTest {

    @Test
    void aCryptoListCanBeEmpty() {
        CryptoList list = new CryptoList();
        assertTrue(list.getCryptos().isEmpty());

    }

    @Test
    void aCryptoListCanHoldMoreThanOneCryptoQuote() {
        CryptoList list = new CryptoList();

        list.addCrypto(Crypto.builder().build());
        assertEquals(1, list.getCryptos().size());

        list.addCrypto(Crypto.builder().build());
        assertEquals(2, list.getCryptos().size());
    }

    @Test
    void aCryptoListIncludesAddedCryptoQuote() {
        Crypto quote = Crypto.builder().build();
        CryptoList list = new CryptoList();

        list.addCrypto(quote);
        assertTrue(list.getCryptos().contains(quote));
    }
}