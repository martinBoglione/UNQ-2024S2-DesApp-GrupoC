package ar.edu.unq.desapp.model.DTOs;

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
    private double amountArs;
    private Long userID;
    private String operationType;
}
