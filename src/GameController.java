import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * @author Bugrahan Cigdemoglu bcigdem1
 *
 */
@SuppressWarnings("serial")
public class GameController extends JPanel {

    /**
     * Tells if the game began.
     */
    private static boolean begin = false;
    /**
     * Tells if the game is over.
     */
    private static boolean end = false;
    /**
     * Running thread.
     */
    private static GameRunner gameRunner;
    /**
     * Frame height.
     */
    private static final int FRAME_HEIGHT = 240;
    /**
     * Frame height.
     */
    private static final int FRAME_WIDTH = 1000;
    /**
     * Font size.
     */
    private static final int FONT_SIZE = 72;
    /**
     * Label height.
     */
    private static final int LABEL_HEIGHT = 150;
    /**
     * Label width.
     */
    private static final int LABEL_WIDTH = 1000;
    /**
     *  Model.
     */
    private Game game;
    /**
     * View.
     */
    private GameView gameView;
    /**
     * Audio Effects.
     */
    private GameFX gameFX;
    /**
     * Frame.
     */
    private JFrame frame;
    /**
     * Instructions and score.
     */
    private JLabel score;
    


    /**
     * Constructor.
     * @param gameModel Model.
     * @param gameViewer View.
     */
    public GameController(Game gameModel, GameView gameViewer, GameFX gameAFX) {
        this.game = gameModel;
        this.gameView = gameViewer;
        this.gameFX = gameAFX;
        this.getGameView().displayScore(this.getGame().getScoreP1());

        KeyListener listener = new KeyListener() {
            @Override
            public void keyPressed(final KeyEvent key) {
                if (key.getKeyCode() == KeyEvent.VK_UP) {
                    if (!begin && !end) {
                        runGame();
                    } else {
                        p1keyPressed();
                    }
                }
                if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.exit(0);
                }
                if (key.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (end) {
                        resetGame();
                    }
                }
            }
            
            @Override
            public void keyReleased(final KeyEvent e) { }

            @Override
            public void keyTyped(final KeyEvent e) { }
        };
        setFocusable(true);
        addKeyListener(listener);
    }

    /**
     * Initialize frames.
     */
    public final void initialize() {
        this.createFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Starts thread.
     */
    public final void runGame() {
        GameController.gameRunner = new GameRunner();
        GameController.gameRunner.start();
        GameController.begin = true;
    }

    /**
     * Ends thread.
     */
    public final void endGame() {
        this.game.setGameOver();
        this.gameFX.playLost();
        GameController.gameRunner.shutdown();
        try {
            GameController.gameRunner.join();
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }
    }
    
    public final void resetGame() {
        this.game.resetGame();
        GameController.end = false;
        this.runGame();
    }

    /**
     * @return gameModel.
     */
    private Game getGame() {
        return this.game;
    }

    /**
     * @return gameView.
     */
    private GameView getGameView() {
        return this.gameView;
    }

    /**
     * Executes when P1 presses the designated key.
     */
    private void p1keyPressed() {
        int scoreP1 = this.game.getScoreP1();
        if (this.game.isPlayable()) {
            if ((scoreP1 + 1) % 10 != 0 || scoreP1 == 0) {
                this.gameFX.playScored();
            } else {
                this.gameFX.playScoredHigh();
            }
            this.game.increaseScoreP1();
            this.gameView.displayScore(scoreP1);
        } else if (!end) {
            this.endGame();
            this.gameView.displayFinalScore(scoreP1);
            updateFinalScore();
            GameController.begin = false;
            GameController.end = true;
        }
    }

    /**
     * Generate frame.
     */
    public final void createFrame() {
        frame = new JFrame("Sixth Sense");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
        frame.getContentPane().setLayout(new FlowLayout());
        score = new JLabel("0");
        score.setFont(new Font("Serif", Font.PLAIN, FONT_SIZE));
        score.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        score.setSize(score.getPreferredSize());
        frame.add(score);
        frame.add(this);
        score.setText("<html>Press up arrow to play.</html>");
    }

    /**
     * Update score GUI.
     */
    public final void updateScore() {
        if (this.score != null) {
            score.setText("<html>Score: " + this.game.getStrScoreP1()
                    + "<br></html>");
        }
    }

    /**
     * Update score GUI to end game.
     */
    public final void updateFinalScore() {
        if (this.score != null) {
            score.setText("<html>Final Score: " + this.game.getStrScoreP1()
                    + "<br>Right to exit, Left to replay.</html>");
        }
    }


    /**
     * @author Bugrahan Cigdemoglu bcigdem1
     * Game runner thread.
     */
    class GameRunner extends Thread {
        /**
         * Stop flag.
         */
        private volatile boolean running = true;
        @Override
        public void run() {
            while (running) {
                updateScore();
                getGame().updateTimer();
            }
        }
        /**
         * Stop switch.
         */
        public void shutdown() {
            running = false;
        }
    }

}
