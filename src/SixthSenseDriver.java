/**
 * 
 */

/**
 * @author Bugrahan Cigdemoglu bcigdem1
 *
 */
public class SixthSenseDriver {

    /**
     * 
     */
    public SixthSenseDriver() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Game game = new Game(10);
        GameView gameView = new GameView();
        GameFX gameFX = new GameFX();
        GameController gameController = new GameController(game, gameView, gameFX);
        gameController.initialize();
    }
}
