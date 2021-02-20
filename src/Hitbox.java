/**
 * Class describing a position of the game entity
 * @author Dmitriy Stepanov
 */
public class Hitbox {
    public static int TOP = 1;
    public static int LEFT = 2;
    public static int BOTTOM = 4;
    public static int RIGHT = 8;
    private double x0, y0, x1, y1;

    /**
     * Constructor - creating a new position of the game entity
     * @param x0 the initial X-coordinate
     * @param y0 the initial Y-coordinate
     * @param x1 final X coordinate
     * @param y1 final Y coordinate
     * @see Hitbox#Hitbox(double,double,double,double)
     */
    public Hitbox(double x0, double y0, double x1, double y1) {
        if(x0 < x1) {
            this.x0 = x0;
            this.x1 = x1;
        } else {
            this.x0 = x1;
            this.x1 = x0;
        }
        if(y0 < y1) {
            this.y0 = y0;
            this.y1 = y1;
        } else {
            this.y0 = y1;
            this.y1 = y0;
        }
    }

    /**
     * Moving a game entity by coordinates
     * @param x0 the initial X-coordinate
     * @param y0 the initial Y-coordinate
     * @param x1 final X coordinate
     * @param y1 final Y coordinate
     */
    public void move(double x0, double y0, double x1, double y1) {
        if(x0 < x1) {
            this.x0 = x0;
            this.x1 = x1;
        } else {
            this.x0 = x1;
            this.x1 = x0;
        }
        if(y0 < y1) {
            this.y0 = y0;
            this.y1 = y1;
        } else {
            this.y0 = y1;
            this.y1 = y0;
        }
    }

    /**
     * Moving a game entity
     * @param dx some offset by X
     * @param dy some offset by Y
     */
    public void move(double dx, double dy) {
        x0 += dx;
        x1 += dx;
        y0 += dy;
        y1 += dy;
    }

    /**
     * Checking for a game entity hitting the screen
     * @return index of the position of the game entity
     */
    public int intersection(Hitbox hb) {
        double w = ((x1 - x0) + (hb.x1 - hb.x0)) / 2;
        double h = ((y1 - y0) + (hb.y1 - hb.y0)) / 2;
        double dx = ((x1 + x0) - (hb.x1 + hb.x0)) / 2;
        double dy = ((y1 + y0) - (hb.y1 + hb.y0)) / 2;

        if (Math.abs(dx) <= w && Math.abs(dy) <= h) {
            double wy = w * dy; double hx = h * dx;
            if (wy > hx) {
                if (wy > -hx) return BOTTOM;
                else return LEFT;
            } else {
                if (wy > -hx) return RIGHT;
                else return TOP;
            }
        }
        return 0;
    }
}