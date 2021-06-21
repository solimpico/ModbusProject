package it.unisalento.mymodbusproject.restcontroller;

import it.unisalento.mymodbusproject.dto.TelemetryDTO;
import it.unisalento.mymodbusproject.exception.FunctionCodeNotSupportedException;
import it.unisalento.mymodbusproject.exception.InvalidQuantityException;
import it.unisalento.mymodbusproject.exception.InvalidStartingAddressException;
import it.unisalento.mymodbusproject.exception.ModbusException;
import it.unisalento.mymodbusproject.modbus.ModbusTcpClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.ByteOrder;

@RestController
public class ApiRestController {

    ModbusTcpClient modbusTcpClient = new ModbusTcpClient("192.168.1.52", 502);

    @GetMapping(value="temperature", produces = MediaType.APPLICATION_JSON_VALUE)
    public TelemetryDTO getTemperature() throws IOException, InvalidQuantityException, FunctionCodeNotSupportedException, ModbusException, InvalidStartingAddressException {
        TelemetryDTO telemetryDTO = new TelemetryDTO();

        //TO-DO
        //Connessione allo slave (raspberry)
        modbusTcpClient.connect();
        //Recuperare la temperatura dal raspberry
        //1 = temperature
        modbusTcpClient.writeHoldingRegister(0x01, 1);
        int []temp = modbusTcpClient.readHoldingRegisters(0x02, 1);
        System.out.println("ciao");
        //Inserirla in TelemetryDTO
        //ritornare telemetryDTO
        return telemetryDTO;
    }

}
