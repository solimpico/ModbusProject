package it.unisalento.mymodbusproject.listener;

public interface DataSendListener {
    void onDataSend(byte[] dataSend);
}