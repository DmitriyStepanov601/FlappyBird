package gameobjects;
import graphics.Screen;
import logic.Action;
import logic.Timer;

/**
 * Class describing a bird
 * @author Dmitriy Stepanov
 */
public class Bird {
	public double x, y, vy;
	private static final double G = 1200;
	private static final double FLAP = -350;

	public Hitbox box;
	private final Timer sprite_timer;
	private int sprite_state = 0;
	private final int[] sprite_states = { 0, 1, 2, 1 };
	private final int[] sprites_x = { 528, 528, 446 };
	private final int[] sprites_y = { 128, 180, 248 };

	/**
	 * Changing the sprite
	 * @return altered state of sprite
	 */
	private Action changessprite() {
		return () -> {
			sprite_state += 1;
			sprite_state = sprite_state % sprite_states.length;
		};
	}

	/**
	 * Constructor - create a new bird with coordinates
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @see Bird#Bird(double,double)
	 */
	public Bird(double x, double y) {
		this.x = x;
		this.y = y;
		this.vy = 0;
		this.box = new Hitbox(x, y, x + 34, y + 24);
		sprite_timer = new Timer(0.1, true, changessprite());
	}

	/**
	 * Update state the bird
	 * @param dt time
	 */
	public void update(double dt) {
		vy += G * dt;
		y += vy * dt;
		this.box.move(0, vy * dt);
	}

	/**
	 * Update the sprite
	 * @param dt time
	 */
	public void updateSprite(double dt){
		sprite_timer.tickTimer(dt);
	}

	/**
	 * Draw a sprite
	 * @param t экран игры
	 */
	public void drawScreen(Screen t) {
		t.setImage("/sprites.png", sprites_x[sprite_states[sprite_state]],
				sprites_y[sprite_states[sprite_state]], 34, 24, Math.atan(vy / 200), x, y);
	}
	
	public void flap(){
		this.vy = FLAP;
	}
}