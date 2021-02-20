import java.util.HashMap;

/**
 * Class describing a color thing
 * @author Dmitriy Stepanov
 */
public class ColorThing {
    public int r;
    public int g;
    public int b;
    private static final HashMap<Integer, ColorThing> colors = new HashMap<>();

    /**
     * Конструктор - создание нового цвета c параметрами
     * @param r red
     * @param g green
     * @param b blue
     * @see ColorThing#ColorThing(int,int,int)
     */
    private ColorThing(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Getting a color
     * @return a certain color
     */
    public static ColorThing rgb(double r, double g, double b) {
        return ColorThing.rgb((int)(r * 255), (int)(g * 255), (int)(b * 255));
    }

    /**
     * Getting the color index and putting it in the color table
     * @return color index
     */
    public static ColorThing rgb(int r, int g, int b) {
        int indice = (r << 16) | (g << 8) | b;
        if(!colors.containsKey(indice)) {
            colors.put(indice, new ColorThing(r, g, b));
        }
        return colors.get(indice);
    }
    
    public static ColorThing WHITECOLOR = ColorThing.rgb(1.0, 1.0, 1.0);
    public static ColorThing BLUECOLOR = ColorThing.rgb(0.0, 0.0, 1.0);
    public static ColorThing REDCOLOR = ColorThing.rgb(1.0, 0.0, 0.0);
    public static ColorThing GREENCOLOR = ColorThing.rgb(0.0, 1.0, 0.0);
}
