
/**
 * ************************************
 *
 * Dale Frank CS 3 – 0119 - Java Programming Assignment 6 Sound Class
 *
 ***********************************
 */
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

public class Sound {

  public static AudioClip music;

  public static void playSound(String url) throws InterruptedException {
    try {
      long delay = 10;
      AudioClip clip = Applet.newAudioClip(new URL(url));
      clip.play();
      Thread.sleep(delay);

    } catch (MalformedURLException murle) {
      System.out.println(murle);
    }
  }

  public static void playMusic(String url) throws InterruptedException {
    try {

      music = Applet.newAudioClip(new URL(url));
      music.loop();

    } catch (MalformedURLException murle) {
      System.out.println(murle);
    }
  }

  public static void stopMusic() {
    music.stop();
  }
}