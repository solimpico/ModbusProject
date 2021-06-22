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
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/telemetry")
public class ApiRestController {

    ModbusTcpClient modbusTcpClient = new ModbusTcpClient("192.168.43.159", 502);

    @GetMapping(value="/temperatureAndHumidity", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TelemetryDTO> getTelemetry() throws IOException, InvalidQuantityException, FunctionCodeNotSupportedException, ModbusException, InvalidStartingAddressException, InterruptedException {
        List<TelemetryDTO> telemetryDTOList = new ArrayList<>();
        //TO-DO
        //Connessione allo slave (raspberry)
        modbusTcpClient.connect();
        //Recuperare la temperatura dal raspberry
        //1 = temperature
        modbusTcpClient.writeHoldingRegister(0x01, 1);
        int []telem = modbusTcpClient.readHoldingRegisters(0x01, 2);
        //Inserirla in TelemetryDTOList
        telemetryDTOList.add(new TelemetryDTO("Temeperature", telem[0]));
        telemetryDTOList.add(new TelemetryDTO("Humidity", telem[1]));
        //ritornare telemetryDTOList
        return telemetryDTOList;
    }

}
