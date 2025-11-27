package sanguine.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import sanguine.model.ViewModel;

/**
 * Draws an interactable playable Sanguine game in a JFrame.
 */
public class SanguineFrameView extends JFrame implements SanguineView {
  private final SanguineBoardPanel board;
  private final SanguineCardSectionPanel cards;

  /**
   * Constructs the playable JFrame based on a ViewModel that it
   * ses to observe the state of the game.
   *
   * @param model is the ViewModel that has observers to look at the game state
   * @throws IllegalArgumentException if the model is null
   */
  public SanguineFrameView(ViewModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }

    JPanel main = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
      }
    };
    main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

    this.board = new SanguineBoardPanel(model);
    this.cards = new SanguineCardSectionPanel(model.getCurrentHand(), model.getCurrentPlayer());

    board.setPreferredSize(new Dimension(0, 500));
    cards.setPreferredSize(new Dimension(0, 300));
    cards.setLayout(new BoxLayout(cards, BoxLayout.X_AXIS));

    main.add(board);
    main.add(Box.createRigidArea(new Dimension(0, 10)));
    main.add(cards);

    this.add(main);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1200, 800);
    this.setTitle("Player: " + model.getCurrentPlayer());
  }

  @Override
  public void display(boolean show) {
    this.setVisible(show);
  }

  @Override
  public void subscribe(FeaturesListener listener) throws IllegalArgumentException {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null.");
    }

    board.subscribe(listener);
    cards.subscribe(listener);

    this.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_P) {
          listener.handleKeyPressed("Pass turn.");
        } else if (keyCode == KeyEvent.VK_ENTER) {
          listener.handleKeyPressed("Confirm move.");
        }
      }
    });
  }
}
