package it.unisalento.mymodbusproject.exception;

public class FunctionCodeNotSupportedException extends Exception {
    public FunctionCodeNotSupportedException() {}

    public FunctionCodeNotSupportedException(String message) {
        super(message);
    }
}
