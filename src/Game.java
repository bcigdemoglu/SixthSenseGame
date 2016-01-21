import java.util.Random;


/**
 * @author Bugrahan Cigdemoglu bcigdem1
 *
 */
public class Game {
    
    private int scoreP1;
    private int round;
    
    private long gameStartTime;
    private long roundStartTime;
    private long roundEndTime;
    private int maxGameTime;
    private boolean gameOver;
    Random randomGenerator = new Random();


    public Game(int maxTime) {
        this.maxGameTime = maxTime;
        this.round = 1;
        this.scoreP1 = 0;
        this.gameOver = false;
        this.updateTimer();
    }
    
    public void setEndTime() {
        this.roundEndTime = (long) randomGenerator.nextInt(this.maxGameTime) * 1000;
    }
    
    public void setStartTime() {
        this.roundStartTime = System.currentTimeMillis();
    }
    
    public boolean isPlayable() {
        return this.getElapsed() > 1000 && !this.gameOver;
    }
    
    private long getElapsed() {
        return System.currentTimeMillis() - this.roundStartTime;
    }
    
    private long getRemaining() {
        return this.roundEndTime - this.getElapsed();
    }
    
    public void updateTimer() {
        if (this.updateRequired()) {
            this.setStartTime();
            this.setEndTime();
            this.round++;
        }
    }
    
    public boolean updateRequired() {
        return this.getRemaining() <= 0;
    }
    
    public void increaseScoreP1() {
        this.scoreP1++;
    }
    
    public int getScoreP1() {
        return this.scoreP1;
    }
    
    public String getStrScoreP1() {
        return String.valueOf(this.scoreP1);
    }
    
    public int getRound() {
        return this.round;
    }
    
    public void setGameOver() {
        this.gameOver = true;
    }
    
    public boolean getGameOver() {
        return this.gameOver;
    }
    
    public void resetGame() {
        this.scoreP1 = 0;
        this.round = 0;
        this.gameOver = false;
    }

}
