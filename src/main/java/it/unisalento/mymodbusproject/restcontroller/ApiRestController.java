package it.unisalento.mymodbusproject.restcontroller;

import it.unisalento.mymodbusproject.dto.TelemetryDTO;
import it.unisalento.mymodbusproject.exception.FunctionCodeNotSupportedException;
import it.unisalento.mymodbusproject.exception.InvalidQuantityException;
import it.unisalento.mymodbusproject.exception.InvalidStartingAddressException;
import it.unisalento.mymodbusproject.exception.ModbusException;
import it.unisalento.mymodbusproject.modbus.ModbusTcpClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiRestController {

    ModbusTcpClient modbusTcpClient = new ModbusTcpClient("192.168.1.52", 502);

    @GetMapping(value="/getTemperatureAndHumidity", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TelemetryDTO> getTelemetry() throws IOException, InvalidQuantityException,
            FunctionCodeNotSupportedException, ModbusException, InvalidStartingAddressException {
        List<TelemetryDTO> telemetryDTOList = new ArrayList<>();
        //TO-DO
        //Connessione allo slave (raspberry)
        modbusTcpClient.connect();
        //Recuperare la temperatura dal raspberry
        //1 = temperature
        modbusTcpClient.writeHoldingRegister(0x01, 1);
        int []telem = modbusTcpClient.readHoldingRegisters(0x01, 2);
        //Inserirla in TelemetryDTOList
        telemetryDTOList.add(new TelemetryDTO("Temperature", telem[0]));
        telemetryDTOList.add(new TelemetryDTO("Humidity", telem[1]));
        //ritornare telemetryDTOList
        modbusTcpClient.disconnect();
        return telemetryDTOList;
    }

    @PutMapping(value = "/motorOn", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean motorOn() throws IOException, InvalidQuantityException, FunctionCodeNotSupportedException,
            ModbusException, InvalidStartingAddressException {
        modbusTcpClient.connect();
        modbusTcpClient.writeCoil(0x10, true);
        boolean status = modbusTcpClient.readCoils(0x10, 1)[0];
        modbusTcpClient.disconnect();
        return status;
    }

    @PutMapping(value = "/motorOff", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean motorOff() throws IOException, InvalidQuantityException, FunctionCodeNotSupportedException,
            ModbusException, InvalidStartingAddressException {
        modbusTcpClient.connect();
        modbusTcpClient.writeCoil(0x10, false);
        boolean status = modbusTcpClient.readCoils(0x10, 1)[0];
        modbusTcpClient.disconnect();
        return status;
    }

    @GetMapping(value = "/getMotorStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getMotorStatus() throws IOException, InvalidQuantityException, FunctionCodeNotSupportedException, ModbusException, InvalidStartingAddressException {
        modbusTcpClient.connect();
        return modbusTcpClient.readCoils(0x10, 1)[0];
    }

}
