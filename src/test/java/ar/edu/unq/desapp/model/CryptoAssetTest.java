package ar.edu.unq.desapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CryptoAssetTest {

    @Test
    void thereAreOnlyFourteenCryptoAssets() {
        assertEquals(14, CryptoAsset.values().length);
    }

    @Test
    void eachCryptoAssetCanBeInstantiatedByItsName() {
        assertNotNull(CryptoAsset.valueOf("ALICEUSDT"));
        assertNotNull(CryptoAsset.valueOf("MATICUSDT"));
        assertNotNull(CryptoAsset.valueOf("AXSUSDT"));
        assertNotNull(CryptoAsset.valueOf("AAVEUSDT"));
        assertNotNull(CryptoAsset.valueOf("ATOMUSDT"));
        assertNotNull(CryptoAsset.valueOf("NEOUSDT"));
        assertNotNull(CryptoAsset.valueOf("DOTUSDT"));
        assertNotNull(CryptoAsset.valueOf("ETHUSDT"));
        assertNotNull(CryptoAsset.valueOf("CAKEUSDT"));
        assertNotNull(CryptoAsset.valueOf("BTCUSDT"));
        assertNotNull(CryptoAsset.valueOf("BNBUSDT"));
        assertNotNull(CryptoAsset.valueOf("ADAUSDT"));
        assertNotNull(CryptoAsset.valueOf("TRXUSDT"));
        assertNotNull(CryptoAsset.valueOf("AUDIOUSDT"));
    }
}