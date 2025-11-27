package sanguine.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Draws the influence grid for a SanguineCardPanel.
 */
public class SanguineInfluenceGridPanel extends JPanel {

  /**
   * Constructs the drawing of the influence grid for a SanguineCardPanel.
   *
   * @param grid the influence grid fed in from the SanguineCardPanel
   */
  public SanguineInfluenceGridPanel(boolean[][] grid) {
    this.setLayout(new GridLayout(5, 5));

    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        Color color;

        if (grid[row][col]) {
          color = Color.CYAN;
        } else if (row == 2 && col == 2) {
          color = Color.YELLOW;
        } else {
          color = Color.DARK_GRAY;
        }

        JPanel panel = new JPanel() {
          @Override
          protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);
            g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
          }
        };
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(panel);
      }
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setColor(Color.DARK_GRAY);
    g2d.fillRect(0, 0, getWidth(), getHeight());
  }
}
