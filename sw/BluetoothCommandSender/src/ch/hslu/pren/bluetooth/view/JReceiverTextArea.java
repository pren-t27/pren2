package ch.hslu.pren.bluetooth.view;

import ch.hslu.pren.bluetooth.events.BluetoothReceiverListener;

import javax.swing.*;
import javax.swing.JTextArea;
import java.awt.*;
import java.lang.Override;
import java.lang.String;

/**
 * Created by simonneidhart on 12.03.15.
 * Class used to give the possibility to a TextArea to become a BluetoothReceiverListener
 */
public class JReceiverTextArea extends JTextArea implements BluetoothReceiverListener {
    public JReceiverTextArea(){
        super();
        this.setAutoscrolls(true);
    }

    @Override
    public void onBluetoothMessageReceived(String message) {
        this.setText(this.getText() + message);
    }

    @Override
    public String toString(){
        return this.getName();
    }
}
