package ch.hslu.pren.bluetooth.control;

import ch.hslu.pren.bluetooth.events.BluetoothReceiverListener;
import jssc.SerialPort;
import jssc.SerialPortException;
import org.junit.Test;

import java.util.Arrays;
import java.util.Properties;

import static org.junit.Assert.*;

public class BluetoothControllerTest {

    @Test
    public void testInitConnections() throws Exception {
        BluetoothController instance = new BluetoothController();
        instance.initConnections("/dev/cu.Bluetooth-Incoming-Port");
        assertEquals(instance.getConnectionStatus(), true);
    }

    @Test
    public void testGetMacOSXPortNames() throws Exception {

        String os = "os.name";

        Properties prop = System.getProperties();
        String actualOS = prop.getProperty(os);
        if (actualOS.contains("Windows")) {
            assertEquals(Arrays.toString(BluetoothController.getMacOSXPortNames()), "[]");
        } else {
            assertEquals(BluetoothController.getMacOSXPortNames()[0], "/dev/cu.Bluetooth-Incoming-Port");
        }
    }

   /* @Test
    public void testGetWindowsPortNames() throws Exception {

        String os = "os.name";

        Properties prop = System.getProperties();
        String actualOS = prop.getProperty(os);
        if (actualOS.contains("Windows")) {
            assertEquals(BluetoothController.getWindowsPortNames()[0], "COM3");
        } else {
            //assertEquals(Arrays.toString(BluetoothController.getWindowsPortNames()), "[]");
        }
    }
*/
    @Test(expected = SerialPortException.class)
    public void testInitConnectionsWithWrongPortName() throws Exception {
        BluetoothController instance = new BluetoothController();
        instance.initConnections("noBluetoothPortName");
    }

    @Test(expected = NullPointerException.class)
    public void testSendCommandToDevice() throws Exception {
        new BluetoothController().sendCommandToDevice("command");
    }

    @Test(expected = NullPointerException.class)
    public void testTurnRight() throws Exception {
        new BluetoothController().turnRight(100);
    }

    @Test(expected = NullPointerException.class)
    public void testTurnLeft() throws Exception {
        new BluetoothController().turnLeft(100);
    }

    @Test(expected = NullPointerException.class)
    public void testShoot() throws Exception {
        new BluetoothController().shoot();
    }

    @Test(expected = NullPointerException.class)
    public void testResetTower() throws Exception {
        new BluetoothController().resetTower();
    }

    @Test
    public void testGetConnectionStatus() throws Exception {
        assertEquals(new BluetoothController().getConnectionStatus(), false);
    }

    @Test(expected = NullPointerException.class)
    public void testAddBluetoothReceiverListener() throws Exception {
        new BluetoothController().addBluetoothReceiverListener(new BluetoothReceiverListenerDummy());
    }

    private class BluetoothReceiverListenerDummy implements BluetoothReceiverListener {

        @Override
        public void onBluetoothMessageReceived(String message) {
            //Dummy method
        }
    }
}