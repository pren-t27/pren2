package ch.hslu.pren.bluetooth.model;

import ch.hslu.pren.bluetooth.events.BluetoothReceiverListener;
import jssc.SerialPort;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by simonneidhart on 26.03.15.
 */
public class BluetoothReceiverTest {

    @Test
    public void testAddBluetoothReceiverListener() throws Exception {
        new BluetoothReceiver(new SerialPort("/dev/cu.Bluetooth-Incoming-Port")).addBluetoothReceiverListener(new BluetoothReceiverListenerDummy());
    }

    private class BluetoothReceiverListenerDummy implements BluetoothReceiverListener {

        @Override
        public void onBluetoothMessageReceived(String message) {
            //Dummy method
        }
    }
}