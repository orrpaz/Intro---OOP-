package game;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Or on 13/06/2017.
 */
public class Filling  {
    /**
     * members.
     */
    private Color color;
    private Image image;

    /**
     * constructor.
     *
     * @param color the color
     * @param image the image
     */
    public Filling(Color color, Image image) {
        this.color = color;
        this.image = image;
    }

    /**
     * get the color.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * get the image.
     *
     * @return the image
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * figuring if its an image.
     *
     * @return true if it is
     */
    public boolean isItImage() {
        if (this.color == null) {
            return true;
        }
        return false;
    }

    /**
     * figuring if its an color.
     *
     * @return true if it is
     */
    public boolean isItColor() {
        if (this.image == null) {
            return true;
        }
        return false;
    }

    /**
     * parse color definition and return the specified color.
     *
     * @param s the string
     * @return the color
     */
    public static java.awt.Color colorFromString(String s) {
        if (s.contains("RGB")) {
            String[] helper = s.split("\\(");
            String[] helper2 = helper[2].split("\\,");
            String[] helper3 = helper2[2].split("\\)");
            int r = Integer.parseInt(helper2[0]);
            int g = Integer.parseInt(helper2[1]);
            int b = Integer.parseInt(helper3[0]);
            return (new Color(r, g, b));
        }
        String[] helper = s.split("\\(");
        String[] helper2 = helper[1].split("\\)");
        Map<String, Color> colors = new HashMap<String, Color>();
        colors.put("red", java.awt.Color.RED);
        colors.put("yellow", java.awt.Color.YELLOW);
        colors.put("blue", java.awt.Color.BLUE);
        colors.put("cyan", java.awt.Color.CYAN);
        colors.put("green", java.awt.Color.GREEN);
        colors.put("magenta", java.awt.Color.MAGENTA);
        colors.put("black", java.awt.Color.BLACK);
        colors.put("white", java.awt.Color.WHITE);
        colors.put("pink", java.awt.Color.PINK);
        colors.put("orange", java.awt.Color.ORANGE);
        colors.put("gray", java.awt.Color.GRAY);
        colors.put("lightGray", java.awt.Color.LIGHT_GRAY);
        colors.put("darkGray", java.awt.Color.DARK_GRAY);
        return colors.get(helper2[0]);
    }

    /**
     * getting the image.
     *
     * @param path the path
     * @return the image
     */
    public static Image imageFromString(String path) {
        Image image = null;
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("Error: failed to load image");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println("Failed closing the image");
                }
            }
        }
        return image;
    }
}
