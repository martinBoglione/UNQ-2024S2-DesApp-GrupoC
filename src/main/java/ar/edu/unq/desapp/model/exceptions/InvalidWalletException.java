package ar.edu.unq.desapp.model.exceptions;

public class InvalidWalletException extends RuntimeException {

    public InvalidWalletException() {
        super("The CVU length must be 8 characters");
    }
}
