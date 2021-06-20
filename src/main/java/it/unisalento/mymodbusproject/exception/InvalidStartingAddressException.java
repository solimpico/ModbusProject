package it.unisalento.mymodbusproject.exception;

public class InvalidStartingAddressException extends Exception {
    public InvalidStartingAddressException() {}

    public InvalidStartingAddressException(String message) {
        super(message);
    }
}
