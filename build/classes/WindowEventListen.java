/**
 * ************************************
 *
 * Dale Frank CS 3 – 0119 - Java Programming Assignment 6 WindowEventListen
 * Class
 *
 ***********************************
 */
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicToolBarUI;

public class WindowEventListen extends JFrame {
  
  public WindowEventListen(String windowTitle){
    super(windowTitle);
    WindowEventListen.FrameListener listen = new WindowEventListen.FrameListener();
    addWindowListener(listen);
  }
  private class FrameListener extends WindowAdapter
   {
    public void windowClosing (WindowEvent we){
      ChoicesFrame.windowClose();
    }   
  }

  
}
