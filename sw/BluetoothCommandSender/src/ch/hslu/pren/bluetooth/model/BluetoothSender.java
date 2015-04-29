package ch.hslu.pren.bluetooth.model;

import jssc.SerialPort;
import jssc.SerialPortException;

/**
 * Created by simonneidhart on 19.02.15.
 */
public class BluetoothSender {
    private SerialPort connectionPort;
    private boolean isConnected;

    public BluetoothSender(SerialPort serialPort) throws SerialPortException{
        connectionPort = serialPort;
        if (this.connectionPort != null) {
            this.connectionPort.openPort();
        }
    }

    public void sendCommandToDevice(String command) throws SerialPortException {
        this.connectionPort.writeString(command+"\n");
    }

    public void setConnectionPort(SerialPort serialPort) {
        this.connectionPort = serialPort;
    }
}
