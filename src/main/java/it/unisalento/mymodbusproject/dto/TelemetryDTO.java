package it.unisalento.mymodbusproject.dto;

public class TelemetryDTO {
    String type;
    int value;

    public TelemetryDTO(){}

    public TelemetryDTO(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
