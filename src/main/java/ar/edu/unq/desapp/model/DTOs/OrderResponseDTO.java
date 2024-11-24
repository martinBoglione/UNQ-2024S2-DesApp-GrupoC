package ar.edu.unq.desapp.model.DTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//Used in Postman's response when creating an BUR or SELL Order
public class OrderResponseDTO {
    private String asset;
    private double quantity;
    private double price;
    private double amountArs;
    private String operationType;
    private String userName;
    private String userSurname;

    public OrderResponseDTO(String asset, double quantity, double price, double amountArs, String operationType, String userName, String userSurname) {
        this.asset = asset;
        this.quantity = quantity;
        this.price = price;
        this.amountArs = amountArs;
        this.operationType = operationType;
        this.userName = userName;
        this.userSurname = userSurname;
    }

}