package ar.edu.unq.desapp.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequestDTO {
    private String asset;
    private double quantity;
    private double price;
    private double amountArs;
    private Long userID;
    private String operationType;
}
