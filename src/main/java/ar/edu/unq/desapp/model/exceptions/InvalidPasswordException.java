package ar.edu.unq.desapp.model.exceptions;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() {
        super("Password must have a minimum 6 characters long and contain 1 uppercase and lowercase letter and a special character");
    }
}
