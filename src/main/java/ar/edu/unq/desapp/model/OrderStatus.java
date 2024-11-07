package ar.edu.unq.desapp.model;

public enum OrderStatus {
    ACTIVE,                // When offer is published
    INPROCESS,             // When offer is accepted and transaction is reported
    FILLED,                // When received transaction is confirmed by issuer
    CANCELLED_BY_SYSTEM,   // When offer is cancelled by system
    CANCELLED_BY_USER      // When offer is cancelled by user
}
