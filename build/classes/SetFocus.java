
/**
 * Dale Frank CS 3 – 0119 - Java Programming Assignment 6 SetFocus Class
 * 
 * Class to request focus on a first field in deposit entry box.
 *
 */

import javax.swing.event.AncestorListener;
import javax.swing.*;
import javax.swing.event.*;


public class SetFocus implements AncestorListener {



  public void ancestorAdded(AncestorEvent e) {
    JComponent component = e.getComponent();
    component.requestFocusInWindow();

   
  }

  public void ancestorMoved(AncestorEvent e) {
  }

  public void ancestorRemoved(AncestorEvent e) {
  }
}
