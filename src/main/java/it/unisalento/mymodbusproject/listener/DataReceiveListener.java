package it.unisalento.mymodbusproject.listener;

public interface DataReceiveListener {
    void onDataReceive(byte[] dataReceive);
}