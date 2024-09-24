package ar.edu.unq.desapp.model.exceptions;

public class InvalidCVUException extends RuntimeException {

    public InvalidCVUException() {
        super("The CVU length must be 22 characters");
    }
}
