package logic;
import graphics.Screen;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.TreeSet;

/**
 * Class describing the mechanics of the game
 * @author Dmitriy Stepanov
 */
public class Engine {
    private final Game game;
    private final BufferStrategy strategy;
    private final TreeSet<String> keySet = new TreeSet<>();

    /**
     * Constructor - creating new game mechanics
     * @param game - game
     * @see Engine#Engine(Game)
     */
    public Engine(Game game) {
        this.game = game;
        Canvas canvas = new Canvas();
        JFrame container = new JFrame(this.game.getTitle());
        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(new Dimension(this.game.getWidth(), this.game.getHeight()));
        panel.setLayout(null);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        Rectangle bounds = gs[gs.length - 1].getDefaultConfiguration().getBounds();
        container.setResizable(false);

        Image windowIcon = null;
        try {
            windowIcon = ImageIO.read(Engine.class.getResource("/flappybird.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        container.setIconImage(windowIcon);
        container.setBounds(bounds.x + (bounds.width - this.game.getWidth()) / 2,
                bounds.y + (bounds.height - this.game.getHeight()) / 2, this.game.getWidth(),
                this.game.getHeight());
        canvas.setBounds(0,0, this.game.getWidth(), this.game.getHeight());
        panel.add(canvas);        
        canvas.setIgnoreRepaint(true);
        container.pack();
        container.setVisible(true);

        // connecting handlers
        container.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        canvas.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {
                keySet.add(keyString(evt));
            }
            @Override
            public void keyReleased(KeyEvent evt) {
                keySet.remove(keyString(evt));
            }
            @Override
            public void keyTyped(KeyEvent evt) {
                Engine.this.game.enterKey(keyString(evt));
            }
        });

        canvas.createBufferStrategy(2);
        strategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        mainLoop();
    }

    /**
     * Playing the main game loop
     */
    private void mainLoop() {
        Timer t = new Timer(5, new ActionListener() {
            public long t0;
            public void actionPerformed(ActionEvent evt) {
                long t1 = System.currentTimeMillis();
                if(t0 == 0)
                    t0 = t1;
                if(t1 > t0) {
                    double dt = (t1 - t0) / 1000.0;
                    t0 = t1;
                    game.tickGame(keySet, dt);
                    Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
                    g.setColor(Color.black);
                    g.fillRect(0,0, game.getWidth(), game.getHeight());
                    game.drawScreen(new Screen(g));
                    strategy.show();
                }
            }
        });
        t.start();
    }


    private static String keyString(KeyEvent evt) {
        if(evt.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
            return String.valueOf(evt.getKeyChar()).toLowerCase();
        } else {
            switch(evt.getKeyCode()) {
                case KeyEvent.VK_ALT: return "alt";
                case KeyEvent.VK_CONTROL: return "control";
                case KeyEvent.VK_SHIFT: return "shift";
                case KeyEvent.VK_LEFT: return "left";
                case KeyEvent.VK_RIGHT: return "right";
                case KeyEvent.VK_UP: return "up";
                case KeyEvent.VK_DOWN: return "down";
                case KeyEvent.VK_ENTER: return "enter";
                case KeyEvent.VK_DELETE: return "delete";
                case KeyEvent.VK_TAB: return "tab";
                case KeyEvent.VK_WINDOWS: return "windows";
                case KeyEvent.VK_BACK_SPACE: return "backspace";
                case KeyEvent.VK_ALT_GRAPH: return "altgr";
                case KeyEvent.VK_F1: return "F1";
                case KeyEvent.VK_F2: return "F2";
                case KeyEvent.VK_F3: return "F3";
                case KeyEvent.VK_F4: return "F4";
                case KeyEvent.VK_F5: return "F5";
                case KeyEvent.VK_F6: return "F6";
                case KeyEvent.VK_F7: return "F7";
                case KeyEvent.VK_F8: return "F8";
                case KeyEvent.VK_F9: return "F9";
                case KeyEvent.VK_F10: return "F10";
                case KeyEvent.VK_F11: return "F11";
                case KeyEvent.VK_F12: return "F12";
                default: return "";
            }
        }
    }
}