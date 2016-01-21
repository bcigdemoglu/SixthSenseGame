/**
 * 
 */

/**
 * @author Bugrahan Cigdemoglu bcigdem1
 *
 */
public class GameView {

    public void displayScore(int score) {
        System.out.print("\rCurrent score: " + score);
    }
    
    public void displayFinalScore(int score) {
        System.out.print("\nGame over.");
        System.out.print("\nFinal score: " + score);
    }

}
