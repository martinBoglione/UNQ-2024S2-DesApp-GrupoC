package ar.edu.unq.desapp.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//Used in Postman's when getting all active Orders
public class UserDTO {
    private String name;
    private String surname;
    private Integer reputation;
    private Integer operationsQuantity;

    public UserDTO(String name, String surname, Integer reputation, Integer operationsQuantity) {
        this.name = name;
        this.surname = surname;
        this.reputation = reputation;
        this.operationsQuantity = operationsQuantity;
    }
}