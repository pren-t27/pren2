package TestGUI;

import ch.hslu.pren.bluetooth.control.BluetoothController;
import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.Properties;

/**
 * Created by simonneidhart on 16.04.15.
 */
public class newClass {
    public void test() {

        // get the property containing the OS-name
        String os = "os.name";
        Properties prop = System.getProperties();
        String actualOS = prop.getProperty(os);
        System.out.println(actualOS);

        String[] portNames;

        if (actualOS.contains("Windows")) {
            portNames = BluetoothController.getWindowsPortNames();
        } else {
            portNames = BluetoothController.getMacOSXPortNames();
        }

        String definitiveDeviceName = "";
        for (String ourDeviceName : portNames) {
            if (ourDeviceName.equals("ourDevice")) {
                definitiveDeviceName = ourDeviceName;
            }
        }

        // create the controller and init the connections
        BluetoothController controller = new BluetoothController();
        try {
            controller.initConnections(definitiveDeviceName);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }

    }
}
