import java.util.Set;

/**
 * The interface that defines the game
 * @author Dmitriy Stepanov
 */
public interface Game {
    String getTitle();
    int getWidth();
    int getHeight();
    void tickGame(Set<String> keys, double dt);
    void enterKey(String key);
    void drawScreen(Screen screen);
}