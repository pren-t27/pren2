package TestGUI;

import org.opencv.imgcodecs.Imgcodecs;
import java.util.Properties;

/**
 * Created by Peter Kuonen on 21.05.15
 */
public class Example {
    public void example() {

        // Frame initialisieren
        Mat frame = Imgcodecs.imread("Pictures/testpicture.jpg");
		
		//Initialise Erkenner
		Erkennung erkenner = new Erkennung();
		
		//process the frame and receive the double value of the angle
		double angle = erkenner.processFrame(frame);
    }
}
