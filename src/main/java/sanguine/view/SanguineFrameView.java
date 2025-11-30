package sanguine.view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sanguine.model.Team;
import sanguine.model.ViewModel;

/**
 * Draws an interactable playable Sanguine game in a JFrame.
 */
public class SanguineFrameView extends JFrame implements SanguineView {
  private final SanguineBoardPanel board;
  private final SanguineCardSectionPanel cards;
  private final ViewModel viewModel;
  private final Team team;

  /**
   * Constructs the playable JFrame based on a ViewModel that it
   * ses to observe the state of the game.
   *
   * @param model is the ViewModel that has observers to look at the game state
   * @param team the team of this frame
   * @throws IllegalArgumentException if the model is null
   */
  public SanguineFrameView(ViewModel model, Team team) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }

    if (team == null) {
      throw new IllegalArgumentException("Team cannot be null.");
    }

    this.viewModel = model;
    this.team = team;

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
    this.cards = new SanguineCardSectionPanel(model, team);

    board.setPreferredSize(new Dimension(0, 500));
    cards.setPreferredSize(new Dimension(0, 300));
    cards.setLayout(new BoxLayout(cards, BoxLayout.X_AXIS));

    main.add(board);
    main.add(Box.createRigidArea(new Dimension(0, 10)));
    main.add(cards);

    this.add(main);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1200, 800);
    this.setTitle("Player: " + team);
  }

  @Override
  public void display(boolean show) {
    this.setVisible(show);
  }

  @Override
  public void subscribe(PlayerActionListener listener) throws IllegalArgumentException {
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
          listener.handleKeyPressed("P");
          // refreshes to clear the highlighted parts of the game
          // if the player passes their turn
          board.refresh();
          cards.refresh();
        } else if (keyCode == KeyEvent.VK_ENTER) {
          listener.handleKeyPressed("ENTER");
          // refreshes to show the move the player made in this Frame
          board.refresh();
          cards.refresh();
        }
      }
    });
  }

  @Override
  public void notifyTurn() {
    if (viewModel.getCurrentPlayer() != team) {
      return;
    }

    board.refresh();
    cards.refresh();

    JOptionPane pane = new JOptionPane("It's your turn!", JOptionPane.INFORMATION_MESSAGE,
        JOptionPane.DEFAULT_OPTION);

    JDialog dialog = pane.createDialog("Turn changed.");

    dialog.setModalityType(Dialog.ModalityType.MODELESS);
    dialog.setLocationRelativeTo(this);
    dialog.setVisible(true);
  }

  @Override
  public void notifyError(String message) {
    board.refresh();
    cards.refresh();

    JOptionPane.showMessageDialog(this, message,
        "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void notifyGameOver(Team winner, String message) {
    board.refresh();
    cards.refresh();

    JOptionPane pane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE,
        JOptionPane.DEFAULT_OPTION);

    JDialog dialog;
    if (winner == null) {
      dialog = pane.createDialog("Tie");
    } else if (winner == this.team) {
      dialog = pane.createDialog("Victory!");
    } else {
      dialog = pane.createDialog("Loss");
    }

    dialog.setModalityType(Dialog.ModalityType.MODELESS);
    dialog.setLocationRelativeTo(this);
    dialog.setVisible(true);
  }
}
