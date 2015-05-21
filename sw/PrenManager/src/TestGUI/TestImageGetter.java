package ch.hslu.pren.ImageGetter;

import org.opencv.core.Core;
import org.opencv.core.Mat;

/**
 *
 * @author Kevin
 */
public class TestImageGetter {
    
     public static void main(String args[]) {
         
      System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
      ImageHandler myHandler = new ImageHandler();
      myHandler.startReceiving();
      Mat myImage = myHandler.getImage();
        System.out.println("Weite: "+myImage.width());
        System.out.println("Höhe: "+myImage.height());
     myHandler.terminateImages();
    }
 
}
