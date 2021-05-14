package Game.resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Resources {
    public static BufferedImage[] letters;
    private static final String X_PATH =
            "C:/Users/namde/OneDrive/Desktop/OOP/ticTacToeGame/Resources/Image/X.png";
    private static final String O_PATH =
            "C:/Users/namde/OneDrive/Desktop/OOP/ticTacToeGame/Resources/Image/O.png";

    static {
        letters = new BufferedImage[2];
        letters[0] = loadImage(X_PATH);
        letters[1] = loadImage(O_PATH);
    }

    private static BufferedImage loadImage (String path) {
        try {
            return ImageIO.read(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
}
