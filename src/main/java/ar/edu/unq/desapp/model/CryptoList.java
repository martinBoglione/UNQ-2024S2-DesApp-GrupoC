package ar.edu.unq.desapp.model;

import java.util.ArrayList;

public class CryptoList {
    public ArrayList<Crypto> cryptos = new ArrayList<>();

    public void CryptoCurrencyList() {
        //Empty Constructor
    }

    public void addCrypto(Crypto crypto) {
        cryptos.add(crypto);
    }
}
