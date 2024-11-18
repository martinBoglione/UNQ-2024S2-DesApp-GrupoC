package ar.edu.unq.desapp.service;

import ar.edu.unq.desapp.helpers.CurrentTime;
import ar.edu.unq.desapp.model.Crypto;
import ar.edu.unq.desapp.model.CryptoAsset;
import ar.edu.unq.desapp.model.CryptoList;
import ar.edu.unq.desapp.repositories.CryptoRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CryptoService {

    private final CryptoRepository cryptoRepository;
    private final BinanceCryptoService binanceCryptoService;

    public CryptoService(CryptoRepository cryptoRepository, BinanceCryptoService binanceCryptoService) {
        this.cryptoRepository = cryptoRepository;
        this.binanceCryptoService = binanceCryptoService;
    }


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


    @Cacheable(value = "cryptoCache", key = "#symbol")
    public Crypto getCryptoValue(
            String symbol) {
        Crypto entity = binanceCryptoService.getCryptoCurrencyValue(symbol);

        SimpleDateFormat formatter = CurrentTime.getNewDateFormatter();
        if (entity != null) {
            entity.setLastUpdateDateAndTime(formatter.format(new Date()));
        }
        return entity;
    }
}
