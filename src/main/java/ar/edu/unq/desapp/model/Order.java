package ar.edu.unq.desapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    // TODO: Constraint: price should be +/- 5% from last registered quote
    //
    // La validación de precio y el cálculo del monto en ARS se pueden omitir hasta tener funcionando los servicios que traen los precios.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private CryptoAsset asset;

    @Column(nullable = false)
    private double quantity;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private double amount_ars;

    @Column(nullable = false)
    private User user;

    @Column(nullable = false)
    private OperationType operation_type;

    @Column(nullable = false)
    private OrderStatus status;

}
