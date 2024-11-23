package ar.edu.unq.desapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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


    @PrePersist
    public void prePersist() {
        if (this.timestamp == null) {
            this.timestamp = LocalDateTime.now();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CryptoAsset asset;

    @Column(nullable = false)
    private double quantity;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private double amountArs;

    @NotNull(message = "The user cannot be null.")
    @ManyToOne
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private OrderStatus status = OrderStatus.ACTIVE;

}
