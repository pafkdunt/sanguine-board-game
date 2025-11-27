package sanguine.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Drawing of a score panel for an individual teams score for a specific row. Created in this way
 * to support SanguineBoardPanel's GridLayout. This is a subcomponent of the SanguineBoardPanel.
 */
public class SanguineScorePanel extends JPanel {
  private final boolean winning;
  private final Color color;

  /**
   * Constructs the image of a score panel for a specific team at a specific row.
   *
   * @param score   is the score for that team at that row
   * @param winning whether they are winning that row or not
   * @param color   the color this score panel will show if they are winning
   */
  public SanguineScorePanel(int score, boolean winning, Color color) {
    this.winning = winning;
    this.color = color;
    this.setBorder(BorderFactory.createLineBorder(Color.black));
    this.setLayout(new BorderLayout());

    JLabel value = new JLabel(score + "");
    value.setVerticalAlignment(JLabel.CENTER);
    value.setHorizontalAlignment(JLabel.CENTER);
    value.setFont(new Font("SansSerif", Font.BOLD, 40));
    this.add(value, BorderLayout.CENTER);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setColor(color);
    double diameter = (double) getWidth() / 2;
    Shape circle = new Ellipse2D.Double((double) getWidth() / 2 - diameter / 2,
        (double) getHeight() / 2 - diameter / 2, diameter, diameter);
    g2d.setColor(color);
    if (winning) {
      g2d.fill(circle);
    }
  }
}
