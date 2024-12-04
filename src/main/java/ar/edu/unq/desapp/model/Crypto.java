package ar.edu.unq.desapp.model;

import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crypto {

    private String symbol;
    private Float price;
    private String lastUpdateDateAndTime;

}
