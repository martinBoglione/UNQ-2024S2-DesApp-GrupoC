package ar.edu.unq.desapp.model;

import java.util.ArrayList;
import java.util.List;

public class CryptoList {
    public List<Crypto> cryptos = new ArrayList<>();

    public void cryptoCurrencyList() {
        //Empty Constructor
    }

    public void addCrypto(Crypto crypto) {
        cryptos.add(crypto);
    }
}
