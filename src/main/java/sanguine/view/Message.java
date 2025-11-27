package sanguine.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Message extends JFrame {
  private String message;

  public Message(String message) {
    this.message = message;

    JPanel panel = new JPanel();
    JLabel label = new JLabel(message);
    panel.add(label);

    this.add(panel);
  }

  public void display(boolean show) {
    this.setVisible(show);
  }


}
