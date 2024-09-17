package ar.edu.unq.DevApp.model.exceptions;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException() {
        super("The email is not valid.");
    }
}
