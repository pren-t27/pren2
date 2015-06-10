package ch.hslu.pren.bluetooth.control; /**
 * Created by simonneidhart on 19.02.15.
 */

import ch.hslu.pren.bluetooth.events.BluetoothReceiverListener;
import ch.hslu.pren.bluetooth.model.BluetoothReceiver;
import ch.hslu.pren.bluetooth.model.BluetoothSender;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @author simonneidhart
 * Class that is used to handle the sending and receiving of bluetooth-commands
 */
public class BluetoothController {
    private BluetoothReceiver btReceiver;
    private BluetoothSender btSender;
    private Thread receiverThread;
    private boolean isConnected;
    private SerialPort serialPort;

    /**
     * If the class is running on a MAC OS, this is the method used to get the portNames
     * @return the MAC OS port names as an array of Strings
     */
    public static String[] getMacOSXPortNames() {
        String[] returnArray = new String[0];
        File dir = new File("/dev");
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files.length > 0) {
                TreeSet<String> portsTree = new TreeSet<String>();
                ArrayList<String> portsList = new ArrayList<String>();
                for (File f : files) {
                    if (!f.isDirectory() && !f.isFile() && (f.getName().matches("cu.*") || f.getName().matches("tty.(serial|usbserial|usbmodem).*"))) {
                        portsTree.add("/dev/" + f.getName());
                    }
                }
                for (String portName : portsTree) {
                    portsList.add(portName);
                }

                returnArray = portsList.toArray(returnArray);
            }
        }
        return returnArray;
    }

    /**
     * If the class is running on a Windows OS, this is the method used to get the portNames
     * @return the Windows port names as an array of Strings
     */
    public static String[] getWindowsPortNames() {

        return SerialPortList.getPortNames();
    }

    /**
     * Initialises the connections and starts the thread used to listen on a port
     * @param serialPortName the name of the port used as a String
     * @throws SerialPortException
     */
    public void initConnections(String serialPortName) throws SerialPortException {
        if (serialPort != null) {
            receiverThread.interrupt();
            serialPort.closePort();
        }
        System.out.println("Connecting to " + serialPortName);
        serialPort = new SerialPort(serialPortName);
        btSender = new BluetoothSender(serialPort);
        if (serialPortName.matches(".*usb.*")) {
            System.out.println("Set port params");
            serialPort.setParams(SerialPort.BAUDRATE_38400, 8, 1, 0, false, false);
        }
        if(btReceiver!=null){
        }
        btReceiver = new BluetoothReceiver(serialPort);
            receiverThread = new Thread(btReceiver);
            receiverThread.start();

        this.isConnected = true;
    }

    /**
     * Sends a command to the device (Used for tests)
     * @param command the command as a String
     * @throws SerialPortException
     */
    public void sendCommandToDevice(String command) throws SerialPortException {
        btSender.sendCommandToDevice(command);
    }

    /**
     * command to prepare the hardware for moving and shooting operations
     * @throws SerialPortException
     */
    public void startDevice() throws SerialPortException {
        btSender.sendCommandToDevice("BLDC on\n");
    }

    /**
     * command to stop the device in operation
     * @throws SerialPortException
     */
    public void stopDevice() throws SerialPortException {
        btSender.sendCommandToDevice("BLDC off\n");
    }

    /**
     * command to prepare for a fill
     * @throws SerialPortException
     */
    public void fill() throws SerialPortException {
        btSender.sendCommandToDevice("fill\n");
    }

    /**
     * command to stop filling
     * @throws SerialPortException
     */
    public void fillStop() throws SerialPortException {
        btSender.sendCommandToDevice("fill stop\n");
    }

    /**
     * Sends the turnRight command to the connected device
     * @param ticks the number of overgiven ticks
     * @throws SerialPortException
     */
    public void turnRight(int ticks) throws SerialPortException {
        btSender.sendCommandToDevice("l6480 goto " + ticks + "\n");
        float t = ticks;
        try {
            Thread.sleep((long) (t/20));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        btSender.sendCommandToDevice("shoot");
    }

    /**
     * Sends the turnLeft command to the connected device
     * @param ticks the number of overgiven ticks
     * @throws SerialPortException
     */
    public void turnLeft(int ticks) throws SerialPortException {
        btSender.sendCommandToDevice("l6480 goto -" + ticks + "\n");
        float t = ticks;
        try {
            Thread.sleep((long) (t/20));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        btSender.sendCommandToDevice("shoot");
    }

    /**
     * sends the shoot command to the connected device
     * @throws SerialPortException
     */
    public void shoot() throws SerialPortException {
        btSender.sendCommandToDevice("shoot");
    }

    /**
     * sends the resetTower command to the connected device
     * @throws SerialPortException
     */
    public void resetTower() throws SerialPortException {
        btSender.sendCommandToDevice("l6480 goto 0");
        stopDevice();
    }

    public boolean getConnectionStatus() {
        return this.isConnected;
    }

    /**
     * calls the method of the bluetooth receiver so it can add the overgiven
     * BluetoothReceiverListener
     * @param btrListener
     */
    public void addBluetoothReceiverListener(BluetoothReceiverListener btrListener) {
        this.btReceiver.addBluetoothReceiverListener(btrListener);
    }
}
