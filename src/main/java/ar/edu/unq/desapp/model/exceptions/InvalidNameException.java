package ar.edu.unq.desapp.model.exceptions;

public class InvalidNameException extends RuntimeException {

    public InvalidNameException() {
        super("The user name and surname length must be between 3 and 30 characters");
    }


}