import sun.audio.*;
import java.io.*;

/**
 * @author Bugrahan Cigdemoglu bcigdem1
 *
 */
public class GameFX {
    
    private String COIN;
    private String LOST;
    private String GOODJOB;
    private String SCOREDHIGH;
    
    /**
     * 
     */
    public GameFX() {
         this.COIN = "coin.wav";
         this.LOST = "lost.wav";
         this.GOODJOB = "goodJob.wav";
         this.SCOREDHIGH = "scoredHigh.wav";
    }
    
    public void playScored()
    {
      try
      {
        InputStream inputStream = getClass().getResourceAsStream(COIN);
        AudioStream audioStream = new AudioStream(inputStream);
        AudioPlayer.player.start(audioStream);
      }
      catch (Exception e) {
        // a special way i'm handling logging in this application
        e.printStackTrace(System.out);
      }
    }
    
    public void playLost()
    {
      try
      {
        InputStream inputStream = getClass().getResourceAsStream(LOST);
        AudioStream audioStream = new AudioStream(inputStream);
        AudioPlayer.player.start(audioStream);
      }
      catch (Exception e) {
        // a special way i'm handling logging in this application
        e.printStackTrace(System.out);
      }
    }
    
    public void playGoodJob()
    {
      try
      {
        InputStream inputStream = getClass().getResourceAsStream(GOODJOB);
        AudioStream audioStream = new AudioStream(inputStream);
        AudioPlayer.player.start(audioStream);
      }
      catch (Exception e) {
        // a special way i'm handling logging in this application
        e.printStackTrace(System.out);
      }
    }
    
    public void playScoredHigh()
    {
        try
        {
            InputStream inputStream = getClass().getResourceAsStream(SCOREDHIGH);
            AudioStream audioStream = new AudioStream(inputStream);
            AudioPlayer.player.start(audioStream);
        }
        catch (Exception e) {
            // a special way i'm handling logging in this application
            e.printStackTrace(System.out);
        }
    }
}
