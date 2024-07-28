package src.services.images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageService {
    public static int DEFAULT_WIDTH_SCALE = 14;
    public static int DEFAULT_HEIGHT_SCALE = 14;

    public static BufferedImage readAndScale(String pathToImage, int w, int h) {
        BufferedImage source;
        try {
            source = ImageIO.read(new File(pathToImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image scaledImage = source.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        BufferedImage scaledBufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = scaledBufferedImage.createGraphics();

        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        return scaledBufferedImage;
    }

    public static BufferedImage readAndScale(String pathToImage) {
        return ImageService.readAndScale(pathToImage, DEFAULT_WIDTH_SCALE, DEFAULT_HEIGHT_SCALE);
    }
}
