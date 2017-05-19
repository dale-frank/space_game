
/**
 * ************************************
 *
 * Dale Frank CS 3 – 0119 - Java Programming Assignment 5 Account Class
 *
 ***********************************
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MenuBar;
import javax.swing.*;

public class AccountWindow extends JFrame {

  public static WindowEventListen frame;
  private static MenuBar MenuBar;
  //public static CheckingAccount TransCheckAccount;
  public static JTextArea transListArea;

  public AccountWindow() {
    String enterBalance, message;
    String customerName;
    int transactionCode;
    double transactionAmount;
    double initialBalance;
    frame = new ChoicesFrame("Superheros vs.Asteroids");
    frame.setPreferredSize(new Dimension(1100, 800));
    ;
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    transListArea = new JTextArea(22, 26);
    transListArea.setFont(new Font("Courier", Font.PLAIN, 16));
    transListArea.setBackground(Color.black);
    transListArea.setForeground(Color.white);
    transListArea.setLocation(666, 666);
    frame.getContentPane().add(transListArea);
    transListArea.setText("\n\n\n    Welcome to Superhero Bank.\n\n"
            + "    Do your banking, save the world, and\n"
            + "    win some money at the same time.\n\n"
            + "    Select a saved account or create \n"
            + "    a new account to get started.");
    //contentPane.add(transListArea);
    frame.setVisible(true);
    frame.pack();
  }

  public static void frameHide() {
    frame.setVisible(false);
  }

  public static void frameShow() {
    frame.setVisible(true);
  }
}
