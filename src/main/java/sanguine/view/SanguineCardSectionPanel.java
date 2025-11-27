package sanguine.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPanel;
import sanguine.model.Card;
import sanguine.model.Team;

/**
 * Represents the lower half of the frame, showing the cards in the hand of the current player.
 */
public class SanguineCardSectionPanel extends JPanel implements MainPanel {
  private final List<Card> hand;
  private final Team team;
  private int previousCardIndex;

  /**
   * Constructs the drawing of the hand for the current player.
   *
   * @param hand is the hand of the current player
   * @param team is the team of that current player
   * @throws IllegalArgumentException hand is null
   * @throws IllegalArgumentException team is null
   */
  public SanguineCardSectionPanel(List<Card> hand, Team team) throws IllegalArgumentException {
    if (hand == null) {
      throw new IllegalArgumentException("Hand cannot be null.");
    }

    if (team == null) {
      throw new IllegalArgumentException("Team of the hand cannot be null.");
    }
    this.hand = hand;
    this.team = team;
    this.previousCardIndex = -1;

    for (Card card : hand) {
      SanguineCardPanel cardPanel = new SanguineCardPanel(card);
      this.add(cardPanel);
    }
  }

  @Override
  public void subscribe(FeaturesListener listener) throws IllegalArgumentException {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null.");
    }

    this.highlightCard(listener);
  }

  /**
   * Highlights a card that has been clicked on with the mouse, and
   * calls the listener to handle the card click.
   *
   * @param listener is the FeaturesListener that calls it's method to handle a card click
   * @throws IllegalArgumentException if the listener is null
   */
  private void highlightCard(FeaturesListener listener) throws IllegalArgumentException {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null.");
    }

    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int handSize = hand.size();
        double cardWidth = (double) getWidth() / handSize;
        int index = ((int) Math.ceil(e.getX() / cardWidth)) - 1;

        listener.handleCardClick(index, team);
        SanguineCardSectionPanel.this.highlighting(index);
      }
    });
  }

  /**
   * Highlights a card that has been clicked on with the mouse, and calls the listener
   * to handle the card click. If a new card is selected, the highlight moves to the newly selected
   * card. If the selected card is the same as the previous, highlight is removed.
   *
   * @param index is the index of the card component clicked in this panel
   */
  private void highlighting(int index) {
    SanguineCardPanel selectedCard =
        (SanguineCardPanel) SanguineCardSectionPanel.this.getComponent(index);

    if (previousCardIndex == -1) {
      selectedCard.highlight();
      previousCardIndex = index;
      return;
    }

    if (previousCardIndex == index) {
      selectedCard.removeHighlight();
      previousCardIndex = -1;
      return;
    }

    SanguineCardPanel previousCard =
        (SanguineCardPanel) SanguineCardSectionPanel.this.getComponent(previousCardIndex);

    previousCard.removeHighlight();
    selectedCard.highlight();
    previousCardIndex = index;
  }
}
