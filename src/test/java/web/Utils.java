package web;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Utils {
    public static void makeScreenShot(String s) throws Exception {
        Thread.sleep(3000);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        image.createGraphics();
        ImageIO.write(image, "PNG", new File(".\\src\\main\\resources\\" + s + ".png"));
    }
}
