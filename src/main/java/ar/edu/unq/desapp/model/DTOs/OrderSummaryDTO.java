package ar.edu.unq.desapp.model.DTOs;

import ar.edu.unq.desapp.model.CryptoAsset;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
//Used in Postman's when getting all active Orders
public class OrderSummaryDTO {
    private LocalDateTime timestamp;
    private CryptoAsset asset;
    private double quantity;
    private double price;
    private double amountArs;
    private UserDTO user;

    public OrderSummaryDTO(LocalDateTime timestamp, CryptoAsset asset, double quantity, double price, double amountArs, UserDTO user) {
        this.timestamp = timestamp;
        this.asset = asset;
        this.quantity = quantity;
        this.price = price;
        this.amountArs = amountArs;
        this.user = user;
    }
}