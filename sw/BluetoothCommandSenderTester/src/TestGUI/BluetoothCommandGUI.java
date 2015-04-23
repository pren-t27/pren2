package TestGUI;

import ch.hslu.pren.bluetooth.control.BluetoothController;
import ch.hslu.pren.bluetooth.view.JReceiverTextArea;
import jssc.SerialPort;
import jssc.SerialPortException;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Properties;

/**
 * Created by simonneidhart on 09.04.15.
 */
public class BluetoothCommandGUI {
    private JPanel pnlContent;
    private JList portList;
    private JReceiverTextArea txtAreaReceived;
    private JTextField txtFldCommand;
    private SerialPort serialPort;
    BluetoothController btController;
    Thread receiverThread;

    public BluetoothCommandGUI(String[] listItems) {
        JFrame frame = new JFrame();
        btController = new BluetoothController();
        initListeners();
        portList.setListData(listItems);
        initFrame(frame);
    }

    private void initListeners() {
        txtFldCommand.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10 && btController.getConnectionStatus()) {
                    try {
                        btController.sendCommandToDevice(txtFldCommand.getText());
                        txtFldCommand.setText("");
                    } catch (SerialPortException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println();
                }
            }
        });

        portList.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    System.out.println("Connecting to " + portList.getSelectedValue().toString().substring(8));
                    try {
                        btController.initConnections(portList.getSelectedValue().toString());
                        System.out.println(txtAreaReceived.getText());
                        btController.addBluetoothReceiverListener(txtAreaReceived);
                    } catch (SerialPortException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

    }

    private void initFrame(JFrame frame) {
        frame.setContentPane(pnlContent);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(pnlContent.getMinimumSize());
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        String os = "os.name";

        Properties prop = System.getProperties();
        String actualOS = prop.getProperty(os);
        System.out.println(actualOS);

        /*JDialog meinJDialog = new JDialog();
        meinJDialog.setTitle(actualOS);
        meinJDialog.setSize(200,200);
        meinJDialog.setModal(true);
        meinJDialog.setVisible(true);*/

        if (actualOS.contains("Windows")) {
            new BluetoothCommandGUI(BluetoothController.getWindowsPortNames());
        } else {
            new BluetoothCommandGUI(BluetoothController.getMacOSXPortNames());
        }
    }
}
