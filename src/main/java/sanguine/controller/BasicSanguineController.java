package sanguine.controller;

import sanguine.Player.UserPlayer;
import sanguine.model.Team;
import sanguine.view.FeaturesListener;
import sanguine.view.SanguineView;

/**
 * Class that facilitate the connection between the model and view components so the Sanguine game
 * is playable.
 */
public class BasicSanguineController implements SanguineController, FeaturesListener {
  private int cardIndex;
  private final UserPlayer player;
  private int row;
  private int col;
  private SanguineView view;

  /**
   * Constructs this controller to facilitate a connection between the model and view. It
   * currently only supports the view.
   *
   * @param view is the view object that displays the game.
   * @throws IllegalArgumentException if the view is null
   */
  public BasicSanguineController(SanguineView view, UserPlayer player) throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("View cannot be null");
    }

    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }

    this.cardIndex = -1;
    this.view = view;
    this.view.subscribe(this);
    this.player = player;
    this.row = -1;
    this.col = -1;
  }

  @Override
  public void playGame() {
    view.display(true);
  }

  @Override
  public void handleCellClick(int row, int col) {
    this.row = row;
    this.col = col;
  }

  @Override
  public void handleCardClick(int index, Team team) {
    if (index == cardIndex) {
      cardIndex = -1;
    } else {
      cardIndex = index;
    }
  }

  @Override
  public void handleKeyPressed(String key) {
    if (key.equals("ENTER")) {
      if (row == -1 || col == -1 || cardIndex == -1) {
        // change this to be a message box shown in the view
        throw new IllegalArgumentException("Must select a cell and card to make a move");
      }

      player.move(cardIndex, row, col);
      this.row = -1;
      this.col = -1;
      this.cardIndex = -1;
    } else if (key.equals("P")) {
      player.passTurn();
      this.row = -1;
      this.col = -1;
      this.cardIndex = -1;
    }
  }

  @Override
  public void handleTeamChange() {
    view.notifyPlayer();
  }
}
