package ar.edu.unq.desapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CryptoList {

    private List<Crypto> cryptos = new ArrayList<>();

    public void addCrypto(Crypto crypto) {
        cryptos.add(crypto);
    }
}
