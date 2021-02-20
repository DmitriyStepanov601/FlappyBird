/**
 * Class describing a pipe
 * @author Dmitriy Stepanov
 */
public class Pipe {
	public double x,y;
	public static int xspeed = -100;
	public static int HOLESIZE = 94;
	public boolean counted = false;

	public Hitbox boxup;
	public Hitbox boxdown;

	/**
	 * Constructor - creating a new pipe
	 * @param x X-coordinate
	 * @param y Y-coordinate
	 * @see Pipe#Pipe(double,double)
	 */
	public Pipe(double x, double y){
		this.x = x;
		this.y = y;
		this.boxup = new Hitbox(x, y - 270 - 220 ,x + 52 ,y);
		this.boxdown = new Hitbox(x, y + HOLESIZE, x + 52, y + HOLESIZE + 442);
	}

	/**
	 * The placement of the pipes after some distance
	 * @param dt time
	 */
	public void tickPipe(double dt){
		x += xspeed * dt;
		boxup.move(xspeed * dt, 0);
		boxdown.move(xspeed * dt, 0);
	}

	/**
	 * Drawing of pipe
	 * @param screen - screen game
	 */
	public void drawPipe(Screen screen){
		screen.setImage("/sprites.png", 660, 0, 52, 242, 0, x, y + HOLESIZE);
		screen.setImage("/sprites.png", 660, 42, 52, 200, 0, x, y + HOLESIZE + 242);
		screen.setImage("/sprites.png", 604, 0, 52, 270, 0, x, y - 270);
		screen.setImage("/sprites.png", 604, 0, 52, 220, 0, x, y - 270 - 220);
	}
}
