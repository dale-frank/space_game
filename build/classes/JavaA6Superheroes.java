
/**
 * ************************************
 *
 * Dale Frank CS 3 – 0119 - Java Programming Assignment 6 JavaA6Superheroes
 * Class
 *
 ***********************************
 */
import javax.swing.JFrame;

public class JavaA6Superheroes {

  public static JFrame frame;
  public static AccountWindow acctWin;

  public static void main(String[] args) throws InterruptedException {

    frame = new JFrame("Superheroes vs. Asteroids");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.getContentPane().add(new Game());
    frame.setLocation(55, 0);
    frame.pack();
    frame.setVisible(false);
    acctWin = new AccountWindow();


  }

  public static void gameStart() {
//    JFrame frame = new JFrame ("Superheroes vs. Asteroids");
//      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//
//      frame.getContentPane().add(new Game());
//      frame.setLocation(55, 0);
//      frame.pack();
    frame.setVisible(true);


  }

  public static void gameEnd(int amount) {
    frame.setVisible(false);
    ChoicesFrame.frameShow();
    ChoicesFrame.runGameTrans(amount);
  }
}
