package web;

import org.apache.commons.lang3.RandomStringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Утилиты
 */
public class Utils {
    /**
     * Делает скриншот.
     * @param fileName имя файла.
     */
    public static void makeScreenShot(String fileName) throws Exception {
        Thread.sleep(3000);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        image.createGraphics();
        ImageIO.write(image, "PNG", new File(".\\src\\main\\resources\\" + fileName + ".png"));
    }

    /**
     * @return рандомное имя пользователя.
     */
    public static String generateRandomUsername(){
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        return RandomStringUtils.random( 6, uppercaseLetters+lowercaseLetters+numbers);
    }
}
