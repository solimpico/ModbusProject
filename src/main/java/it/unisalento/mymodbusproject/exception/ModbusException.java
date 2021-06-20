package it.unisalento.mymodbusproject.exception;

public class ModbusException extends Exception {
    public ModbusException() {}

    public ModbusException(String message) {
        super(message);
    }
}
