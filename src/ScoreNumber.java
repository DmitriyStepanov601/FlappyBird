/**
 * Class describing a score
 * @author Dmitriy Stepanov
 */
public class ScoreNumber {
	public int number;
	public String snumber;
	public static int record = 0;

	public static int[][] numberData = {
			{ 576, 200 }, { 578, 236 }, { 578, 268 }, { 578, 300 }, { 574, 346 },
			{ 574, 370 }, { 330, 490 }, { 350, 490 }, { 370, 490 }, { 390, 490 }
	};

	/**
	 * Конструктор - создание нового счета игры
	 * @param n number
	 * @see ScoreNumber#ScoreNumber(int)
	 */
	public ScoreNumber(int n){
		this.number = n;
		setSNumber();
	}

	// getters and setters for fields
	public void setSNumber(){
		snumber = String.valueOf(number);
	}

	public void setScore(int n){
		number = n;
		setSNumber();
	}

	public int getScore(){
		return number;
	}

	/**
	 * Процедура определения производителя
	 * @param dn change number
	 */
	public void modifyScore(int dn){
		number += dn;
		setSNumber();
	}

	/**
	 * Drawing the game score
	 * @param screen game screen
	 * @param x X-coordinate
	 * @param y Y-coordinate
	 */
	public void drawScore(Screen screen, int x, int y){
		for(int i = 0; i < snumber.length(); i++){
			drawNumber(screen, Integer.parseInt(snumber.substring(i, i + 1)), x + 15 * i, y);
		}
	}

	/**
	 * Drawing a game record
	 * @param screen game screen
	 * @param x X-coordinate
	 * @param y Y-coordinate
	 */
	public void drawRecord(Screen screen, int x, int y){
		String srecord = String.valueOf(ScoreNumber.record);
		for(int i = 0; i < srecord.length(); i++){
			drawNumber(screen, Integer.parseInt(srecord.substring(i, i + 1)), x + 15 * i, y);
		}
	}

	/**
	 * Drawing numbers
	 * @param screen game screen
	 * @param number number
	 * @param x X-coordinate
	 * @param y Y-coordinate
	 */
	public void drawNumber(Screen screen, int number, int x, int y){
		screen.setImage("/sprites.png", numberData[number][0], numberData[number][1], 14, 20, 0, x, y);
	}
}
