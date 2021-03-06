package graphics;
import java.util.HashMap;

/**
 * Class describing a color thing
 * @author Dmitriy Stepanov
 */
public class ColorObject {
    public int r;
    public int g;
    public int b;
    private static final HashMap<Integer, ColorObject> colors = new HashMap<>();

    /**
     * Constructor - creating a new color with parameters
     * @param r red
     * @param g green
     * @param b blue
     * @see ColorObject#ColorObject(int,int,int)
     */
    private ColorObject(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Getting a color
     * @return a certain color
     */
    public static ColorObject rgb(double r, double g, double b) {
        return ColorObject.rgb((int)(r * 255), (int)(g * 255), (int)(b * 255));
    }

    /**
     * Getting the color index and putting it in the color table
     * @return color index
     */
    public static ColorObject rgb(int r, int g, int b) {
        int indice = (r << 16) | (g << 8) | b;
        if(!colors.containsKey(indice)) {
            colors.put(indice, new ColorObject(r, g, b));
        }
        return colors.get(indice);
    }
    
    public static ColorObject WHITECOLOR = ColorObject.rgb(1.0, 1.0, 1.0);
    public static ColorObject BLUECOLOR = ColorObject.rgb(0.0, 0.0, 1.0);
    public static ColorObject REDCOLOR = ColorObject.rgb(1.0, 0.0, 0.0);
    public static ColorObject GREENCOLOR = ColorObject.rgb(0.0, 1.0, 0.0);
}