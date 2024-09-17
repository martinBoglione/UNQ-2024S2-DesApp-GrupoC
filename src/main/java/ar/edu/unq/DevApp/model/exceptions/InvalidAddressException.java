package ar.edu.unq.DevApp.model.exceptions;

public class InvalidAddressException extends RuntimeException {

    public InvalidAddressException() {
        super("The address  length must be between 10 and 30 characters");
    }
}
