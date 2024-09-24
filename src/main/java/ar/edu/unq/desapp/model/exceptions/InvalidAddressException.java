package ar.edu.unq.desapp.model.exceptions;

public class InvalidAddressException extends RuntimeException {

    public InvalidAddressException() {
        super("The address  length must be between 10 and 30 characters");
    }
}
