import gameobjects.Bird;
import gameobjects.Hitbox;
import gameobjects.Pipe;
import graphics.ColorObject;
import graphics.ScoreNumber;
import graphics.Screen;
import logic.Action;
import logic.Engine;
import logic.Game;
import logic.Timer;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * Class describing a bird
 * @author Dmitriy Stepanov
 */
public class FlappyBird implements Game {
	private Bird bird;
	private final Random generator = new Random();
	private final int record = 0;
	private final ScoreNumber scorenumber;

	private int game_state = 0;        // [0 -> Start Screen]
	                                   // [1 -> Get Ready] [2 -> Game] [3 -> Game Over]

	private double scene_offset = 0;
	private double ground_offset = 0;
	private ArrayList<Pipe> pipes = new ArrayList<>();
	private final Timer pipetimer;
	private final Hitbox groundbox;
	private Timer auxtimer;

	/**
	 * Add pipe in  the game
	 * @return добавленную трубу
	 */
	private Action addPipe() {
		return () -> pipes.add(new Pipe(getWidth(),
				generator.nextInt(getHeight() - 206)));
	}

	/**
	 * Closing state game
	 * @return initial state game
	 */
	private Action close(){
		return () -> {
			game_state += 1;
			game_state = game_state % 4;
		};
	}

	/**
	 * Constructor - creating a new game
	 * @see FlappyBird#FlappyBird()
	 */
	public FlappyBird(){
		bird = new Bird(50, (double) getHeight() / 4);
		pipetimer = new Timer(2,true, addPipe());
		scorenumber = new ScoreNumber(0);
		groundbox = new Hitbox(0, getHeight() - 112, getWidth(), getHeight());
	}

	// getters for getting fields
	public String getTitle(){
		return "Flappy gameobjects.Bird";
	}
	public int getWidth(){
		return 384;
	}
	public int getHeight(){
		return 512;
	}

	private void gameOver() {
		pipes = new ArrayList<>();
		bird = new Bird(50, (double) getHeight() / 4);
		close().execute();
	}

	public void tickGame(Set<String> keys, double dt) {
		scene_offset += dt * 25;
		scene_offset = scene_offset % 288;
		ground_offset += dt * 100;
		ground_offset = ground_offset % 308;
		switch(game_state){
			case 0:
			case 3:
				break;
			case 1:
				auxtimer.tickTimer(dt);
				bird.updateSprite(dt);
				break;
			case 2:
				pipetimer.tickTimer(dt);
				bird.update(dt);
				bird.updateSprite(dt);
				if(groundbox.intersection(bird.box) != 0) {
					gameOver();
					return;
				}

				if(bird.y < -5) {
					gameOver();
					return;
				}

				for(Pipe pipe : pipes) {
					pipe.tickPipe(dt);
					if(pipe.boxup.intersection(bird.box) != 0 || pipe.boxdown.intersection(bird.box) != 0) {
						if(scorenumber.getScore() > ScoreNumber.record) {
							ScoreNumber.record = scorenumber.getScore();
						}
						gameOver();
						return;
					}

					if(!pipe.counted && pipe.x < bird.x) {
						pipe.counted = true;
						scorenumber.modifyScore(1);
					}
				}

				if(pipes.size() > 0 && pipes.get(0).x < -70) {
					pipes.remove(0);
				}
				break;
		}
	}

	public void enterKey(String key) {
		switch(game_state) {
			case 0:
				if(key.equals(" ")) {
					auxtimer = new Timer(1.6, false, close());
					close().execute();
				}
				break;
			case 1:
				break;
			case 2:
				if(key.equals(" ")) {
					bird.flap();
				}
				break;
			case 3:
				if(key.equals(" ")) {
					scorenumber.setScore(0);
					close().execute();
				}
				break;
			}
	}

	/**
	 * Drawing the game screen
	 * @param screen screen game
	 */
	public void drawScreen(Screen screen) {
		screen.setImage("/sprites.png", 0, 0, 288, 512, 0,
				(int) -scene_offset, 0);
		screen.setImage("/sprites.png", 0, 0, 288, 512, 0,
				(int) (288 - scene_offset), 0);
		screen.setImage("/sprites.png", 0, 0, 288, 512, 0,
				(int) (576 - scene_offset), 0);

		for(Pipe pipe : pipes) {
			pipe.drawPipe(screen);
		}

		screen.setImage("/sprites.png", 292, 0, 308, 112, 0,
				-ground_offset, getHeight() - 112);
		screen.setImage("/sprites.png", 292, 0, 308, 112, 0,
				308 -ground_offset, getHeight() - 112);
		screen.setImage("/sprites.png", 292, 0, 308, 112, 0,
				616 - ground_offset, getHeight() - 112);

		switch(game_state) {
			case 0:
				screen.setImage("/sprites.png", 292, 346, 192, 44, 0,
						(double)getWidth() / 2 - (double)192 / 2, 100);
				screen.setImage("/sprites.png", 352, 306, 70, 36, 0,
						(double)getWidth() / 2 - (double)70 / 2, 175);
				screen.textmessage("Press space", 100, getHeight()/2 - 16,
						32, ColorObject.WHITECOLOR);
				break;
			case 1:
				bird.drawScreen(screen);
				screen.setImage("/sprites.png",292,442,174,44, 0,
						(double)getWidth() / 2 - (double)174 / 2, (double)getHeight() / 3);
				scorenumber.drawScore(screen, 5, 5);
				break;
			case 2:
				scorenumber.drawScore(screen, 5, 5);
				bird.drawScreen(screen);
				break;
			case 3:
				screen.setImage("/sprites.png", 292, 398, 188, 38, 0,
						(double)getWidth() / 2 - (double)188 / 2, 100);
				screen.setImage("/sprites.png", 292, 116, 226, 116, 0,
						(double)getWidth() / 2 - (double)226 / 2,
						(double)getHeight() / 2 - (double)116 / 2);
				scorenumber.drawScore(screen, getWidth()/2 + 50, getHeight()/2 - 25);
				scorenumber.drawRecord(screen, getWidth()/2 + 55, getHeight()/2 + 16);
				break;
		}		
	}

	public static void main(String[] args) {
		new Engine(new FlappyBird());
    }
}