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
import sanguine.model.Cell;
import sanguine.model.Team;

/**
 * Draws the individual cells of the board of the game.
 * This is a subcomponent of the SanguineBoardPanel.
 */
public class SanguineCellPanel extends JPanel implements SubPanel {
  private final Cell cell;
  private boolean highlighted;

  /**
   * Constructs the image of the cell that is being drawn based on an inputted Cell object.
   *
   * @param cell is the inputted Cell object
   */
  public SanguineCellPanel(Cell cell) {
    if (cell == null) {
      throw new IllegalArgumentException("Cell cannot be null.");
    }

    this.cell = cell;
    this.highlighted = false;
    this.setBorder(BorderFactory.createLineBorder(Color.black));
    this.setLayout(new BorderLayout());

    if (this.cell.getValue() > 0) {
      JLabel value = new JLabel(this.cell.getValue() + "");
      value.setVerticalAlignment(JLabel.CENTER);
      value.setHorizontalAlignment(JLabel.CENTER);
      value.setFont(new Font("SansSerif", Font.BOLD, 40));
      this.add(value, BorderLayout.CENTER);
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();

    g2d.setColor(new Color(120, 120, 120));
    g2d.fillRect(0, 0, getWidth(), getHeight());

    Color color;

    if (cell.getTeam() == Team.BLUE) {
      color = new Color(90, 150, 210);
    } else {
      color = new Color(200, 120, 120);
    }

    if (cell.getValue() > 0) {
      g2d.setColor(color);
      g2d.fillRect(0, 0, getWidth(), getHeight());
      return;
    }

    if (highlighted) {
      g2d.setColor(Color.CYAN);
      g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    switch (cell.getPawns()) {
      case 1:
        drawOneCircle(g2d, color);
        break;
      case 2:
        drawTwoCircle(g2d, color);
        break;
      case 3:
        drawThreeCircle(g2d, color);
        break;
      default:
        break;
    }
  }

  @Override
  public void highlight() {
    highlighted = !highlighted;
    repaint();
  }

  @Override
  public void removeHighlight() {
    highlighted = false;
    repaint();
  }

  /**
   * Draws one circle to show there is one pawn.
   *
   * @param g2d   the graphics object that does the drawing
   * @param color is the color of the circles that are being draw which
   *              is depending on the player that owns the Cell object
   */
  private void drawOneCircle(Graphics2D g2d, Color color) {
    double diameter = (double) getWidth() / 10;
    Shape circle = new Ellipse2D.Double((double) getWidth() / 2 - diameter / 2,
        (double) getHeight() / 2 - diameter / 2, diameter, diameter);
    g2d.setColor(color);
    g2d.fill(circle);
  }

  /**
   * Draws two circles to show there are two pawns.
   *
   * @param g2d   the graphics object that does the drawing
   * @param color is the color of the circles that are being draw which
   *              is depending on the player that owns the Cell object
   */
  private void drawTwoCircle(Graphics2D g2d, Color color) {
    double diameter = (double) getWidth() / 10;
    Shape circle1 = new Ellipse2D.Double((double) getWidth() / 2 - diameter * 1.5,
        (double) getHeight() / 2 - diameter / 2, diameter, diameter);
    Shape circle2 = new Ellipse2D.Double((double) getWidth() / 2 + diameter / 2,
        (double) getHeight() / 2 - diameter / 2, diameter, diameter);
    g2d.setColor(color);
    g2d.fill(circle1);
    g2d.fill(circle2);
  }

  /**
   * Draws three circles to show there are three pawns.
   *
   * @param g2d   the graphics object that does the drawing
   * @param color is the color of the circles that are being draw which
   *              is depending on the player that owns the Cell object
   */
  private void drawThreeCircle(Graphics2D g2d, Color color) {
    double diameter = (double) getWidth() / 10;
    Shape circle1 = new Ellipse2D.Double((double) getWidth() / 2 - diameter * 1.5,
        (double) getHeight() / 2 + diameter / 2, diameter, diameter);
    Shape circle2 = new Ellipse2D.Double((double) getWidth() / 2 + diameter / 2,
        (double) getHeight() / 2 + diameter / 2, diameter, diameter);
    Shape circle3 = new Ellipse2D.Double((double) getWidth() / 2 - diameter / 2,
        (double) getHeight() / 2 - diameter * 1.5, diameter, diameter);
    g2d.setColor(color);
    g2d.fill(circle1);
    g2d.fill(circle2);
    g2d.fill(circle3);
  }
}
