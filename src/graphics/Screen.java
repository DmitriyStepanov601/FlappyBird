package graphics;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class describing a screen
 * @author Dmitriy Stepanov
 */
public class Screen {
    private final Graphics2D g;
    private static final HashMap<String, BufferedImage> sprites = new HashMap<>();

    /**
     * Constructor - creating a new game screen
     * @param g - drawing the screen
     * @see Screen#Screen(Graphics2D)
     */
    public Screen(Graphics2D g) {
        this.g = g;
        g.setColor(Color.white);
    }

    public void triangle(int x1, int y1, int x2, int y2, int x3, int y3, ColorObject colorObject) {
        g.setColor(new Color(colorObject.r, colorObject.g, colorObject.b));
        g.fillPolygon(new int[] { x1, x2, x3 }, new int[] { y1, y2, y3 }, 3);
    }


    public void triangle(double x1, double y1, double x2, double y2, double x3, double y3, ColorObject colorObject) {
        triangle((int) Math.round(x1), (int) Math.round(y1), (int) Math.round(x2), (int) Math.round(y2),
                  (int) Math.round(x3), (int) Math.round(y3), colorObject);
    }


    public void circle(int cx, int cy, int radius, ColorObject colorObject) {
        g.setColor(new Color(colorObject.r, colorObject.g, colorObject.b));
        g.fillOval(cx - radius, cy - radius, radius * 2, radius * 2);
    }


    public void circle(double cx, double cy, int radius, ColorObject colorObject) {
        circle((int) Math.round(cx), (int) Math.round(cy), radius, colorObject);
    }


    public void square(int x, int y, int side, ColorObject colorObject) {
        g.setColor(new Color(colorObject.r, colorObject.g, colorObject.b));
        g.fillRect(x, y, side, side);
    }

    public void square(double x, double y, int side, ColorObject colorObject) {
        square((int) Math.round(x), (int) Math.round(y), side, colorObject);
    }

    public void rectangle(int x, int y, int width, int height, ColorObject colorObject) {
        g.setColor(new Color(colorObject.r, colorObject.g, colorObject.b));
        g.fillRect(x, y, width, height);
    }

    public void rectangle(double x, double y, int width, int height, ColorObject colorObject) {
        rectangle((int) Math.round(x), (int) Math.round(y), width, height, colorObject);
    }

    public void textmessage(String text, int x, int y, int size, ColorObject colorObject) {
        g.setColor(new Color(colorObject.r, colorObject.g, colorObject.b));
        g.setFont(new Font("Arial", Font.BOLD, size));
        g.drawString(text, x, y);
    }
    
    public void textmessage(String text, double x, double y, int size, ColorObject colorObject) {
        textmessage(text, (int) Math.round(x), (int) Math.round(y), size, colorObject);
    }
    
    public void setImage(String pictureURL, int xa, int ya, int width, int height, double dir, double x, double y) {
        if(!sprites.containsKey(pictureURL)) {
            try {
                sprites.put(pictureURL, ImageIO.read(getClass().getResource(pictureURL)));
            } catch(IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        AffineTransform trans = g.getTransform();
        g.rotate(dir, x + (double) width / 2, y + (double) height / 2);
        g.drawImage(sprites.get(pictureURL), (int) Math.round(x), (int) Math.round(y), (int) Math.round(x) + width,
                (int) Math.round(y) + height, xa, ya, xa + width, ya + height, null);
        g.setTransform(trans);
    }
}