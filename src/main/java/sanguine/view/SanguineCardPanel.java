package sanguine.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sanguine.model.Card;
import sanguine.model.Team;

/**
 * An individual card's drawing. This is a subcomponent of the SanguineCardSectionPanel.
 */
public class SanguineCardPanel extends JPanel implements SubPanel {
  private final Card card;
  private boolean highlighted;

  /**
   * Constructs the image for an individual card based on the inputted Card object.
   *
   * @param card The inputted card that the drawing bases itself off of.
   */
  public SanguineCardPanel(Card card) {
    if (card == null) {
      throw new IllegalArgumentException("Card cannot be null.");
    }

    this.card = card;
    this.highlighted = false;
    this.setBorder(BorderFactory.createLineBorder(Color.black));
    this.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    final JLabel name = new JLabel(card.name());
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.2;
    c.gridx = 0;
    c.gridy = 0;
    name.setFont(new Font("SansSerif", Font.BOLD, 20));
    this.add(name, c);

    final JLabel cost = new JLabel("Cost: " + card.cost());
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.2;
    c.gridx = 0;
    c.gridy = 1;
    cost.setFont(new Font("SansSerif", Font.BOLD, 20));
    this.add(cost, c);

    final JLabel value = new JLabel("Value: " + card.value());
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.2;
    c.gridx = 0;
    c.gridy = 2;
    value.setFont(new Font("SansSerif", Font.BOLD, 20));
    this.add(value, c);

    final JPanel grid = new SanguineInfluenceGridPanel(card.grid());
    c.fill = GridBagConstraints.BOTH;
    c.insets = new Insets(20, 40, 20, 40);
    c.weightx = 1;
    c.weighty = 1;
    c.gridx = 0;
    c.gridy = 3;
    this.add(grid, c);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();

    Color color;

    if (card.team() == Team.BLUE) {
      color = new Color(90, 150, 210);
    } else {
      color = new Color(200, 120, 120);
    }

    if (highlighted) {
      color = Color.CYAN;
    }

    g2d.setColor(color);
    g2d.fillRect(0, 0, getWidth(), getHeight());
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
}
