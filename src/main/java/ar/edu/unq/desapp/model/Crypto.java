package ar.edu.unq.desapp.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Crypto {

    private String symbol;
    private Float price;
    private String lastUpdateDateAndTime;

}
