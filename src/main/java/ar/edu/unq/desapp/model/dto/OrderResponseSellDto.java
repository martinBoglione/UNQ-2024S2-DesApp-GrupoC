package ar.edu.unq.desapp.model.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//Used in Postman's response when creating an SELL Order
public class OrderResponseSellDto {
    private String asset;
    private double quantity;
    private double price;
    private double amountArs;
    private String operationType;
    private String userName;
    private String userSurname;
    private String userCVU;

    public OrderResponseSellDto(String asset, double quantity, double price, double amountArs, String operationType, String userName, String userSurname, String userCvu) {
        this.asset = asset;
        this.quantity = quantity;
        this.price = price;
        this.amountArs = amountArs;
        this.operationType = operationType;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userCVU = userCvu;
    }

}
