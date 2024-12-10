package ar.edu.unq.desapp.model.exceptions;

public class TransactionPreconditionException extends RuntimeException {
    public TransactionPreconditionException(String message) {
        super(message);
    }
}
