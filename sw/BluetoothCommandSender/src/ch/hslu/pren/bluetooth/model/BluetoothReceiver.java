package ch.hslu.pren.bluetooth.model;

import ch.hslu.pren.bluetooth.events.BluetoothReceiverListener;
import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.ArrayList;

/**
 * Created by simonneidhart on 05.03.15.
 * Class used to receive messages from the connected bluetooth device
 */
public class BluetoothReceiver implements Runnable {

    private final SerialPort serialPort;
    private final ArrayList<BluetoothReceiverListener> btrListeners;

    public BluetoothReceiver(SerialPort serialPort) {
        this.serialPort = serialPort;
        btrListeners = new ArrayList<BluetoothReceiverListener>();
    }

    @Override
    /**
     * Reads the SerialPort and notifies all listeners about the received String
     */
    public void run() {
        byte[] messageBytes = new byte[0];
        StringBuilder messageBuilder = new StringBuilder();
        while (!Thread.interrupted()) {

            try {
                if (messageBytes != null) {
                    messageBuilder.append(new String(messageBytes));
                    if (messageBuilder.lastIndexOf("\n") > -1) {
                        String message = new String(messageBytes);
                        System.out.println(message);
                        notifyListeners(message);
                    }
                }
                messageBytes = serialPort.readBytes();
                //messageBuilder.delete(0, messageBuilder.length() - 1)
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds the overgiven BluetoothReceiverListener to the list
     * @param btrListener
     */
    public void addBluetoothReceiverListener(BluetoothReceiverListener btrListener) {
        System.out.println("added Listener: "+btrListener.toString());
        btrListeners.add(btrListener);
    }

    /**
     * Notifies all listeners about the received String
     * @param message
     */
    private void notifyListeners(String message) {
        for (BluetoothReceiverListener btrListener : btrListeners) {
            btrListener.onBluetoothMessageReceived(message);
        }
    }
}
