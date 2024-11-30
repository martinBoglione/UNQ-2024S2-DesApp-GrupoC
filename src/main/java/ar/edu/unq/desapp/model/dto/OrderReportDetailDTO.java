package ar.edu.unq.desapp.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderReportDetailDTO {

    private String asset;
    private double quantity;
    private double currentCryptoValue;
    private double orderValueInPesos;

    public OrderReportDetailDTO(String asset, double quantity, double currentCryptoValue, double orderValueInPesos) {
        this.asset = asset;
        this.quantity = quantity;
        this.currentCryptoValue = currentCryptoValue;
        this.orderValueInPesos = orderValueInPesos;
    }
}
