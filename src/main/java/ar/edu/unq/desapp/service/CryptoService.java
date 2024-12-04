package ar.edu.unq.desapp.service;

import ar.edu.unq.desapp.helpers.CurrentTime;
import ar.edu.unq.desapp.model.Crypto;
import ar.edu.unq.desapp.model.CryptoAsset;
import ar.edu.unq.desapp.model.CryptoList;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class CryptoService {

    private final BinanceCryptoService binanceCryptoService;

    public CryptoService(BinanceCryptoService binanceCryptoService) {
        this.binanceCryptoService = binanceCryptoService;
    }

    @Cacheable(value = "cacheCrypto")
    public CryptoList getAllCryptoPrices() {
        CryptoList list = new CryptoList();
        for (CryptoAsset crypto : CryptoAsset.values()) {
            Crypto entity = binanceCryptoService.getCryptoCurrencyValue(crypto.name());

            if (entity != null) {
                entity.setLastUpdateDateAndTime(CurrentTime.getNewDateString());
            }
            list.addCrypto(entity);

        }
        return list;
    }


    @Cacheable(value = "cacheCrypto")
    public Crypto getCryptoValue(String symbol) {
        Crypto entity = binanceCryptoService.getCryptoCurrencyValue(symbol);

        SimpleDateFormat formatter = CurrentTime.getNewDateFormatter();
        if (entity != null) {
            entity.setLastUpdateDateAndTime(formatter.format(new Date()));
        }
        return entity;
    }


    public List<Crypto> getCryptoClosingPricesLast24Hours(String symbol) {
        return binanceCryptoService.getCryptoClosingPricesLast24Hours(symbol);
    }

}
