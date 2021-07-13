# ModbusProject

This project implements the back-end of "ModbusProject". 

The Tomcat server exposes three APIs: 1) for acquiring telemetry, 2) for switching on the actuator 3) for switching off the actuator.

The project is development using Java with the Spring framework (Springboot, Springweb).

## Execute server

Change your cwd with the command: "cd MyModbusProject/build/libs/" and run the server with "java -jar MyModbusProject-0.0.1-SNAPSHOT.jar".

## COMPLETE GUIDE FOR THE EXECUTION OF THE USE CASE
1) Execute the simulated machinery [ModbusProjectSlave: https://github.com/solimpico/ModbusProjectSlave.git] in your raspberry with the comand "sudo python3 namefile.py".
For more details, read the README file in  solimpico/ModbusProjectSlave.
2) Execute the backend [this project] in your gateway with the comands above. 
3) Exectute the frontend server [ModbusProjectFrontend: https://github.com/solimpico/ModbusProjectFrontend.git] with the command "ng serve" (more details in the README file).
