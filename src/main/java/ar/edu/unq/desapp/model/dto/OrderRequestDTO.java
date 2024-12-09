package ar.edu.unq.desapp.model.dto;

import ar.edu.unq.desapp.model.CryptoAsset;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
// Used in Postman's body to create an Order
public class OrderRequestDTO {
    private CryptoAsset asset;
    private double quantity;
    private double price;
    private Long userID;
    private String operationType;
}
