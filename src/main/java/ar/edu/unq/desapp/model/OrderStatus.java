package ar.edu.unq.desapp.model;

public enum OrderStatus {
    OPEN,       // When offer is published
    INPROCESS,  // When offer is accepted and transaction is reported
    FILLED,     // When received transaction is confirmed by issuer
    CANCELLED   // When offer is cancelled by system
}
