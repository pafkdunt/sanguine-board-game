package sanguine.controller;

import sanguine.model.Team;
import sanguine.view.FeaturesListener;
import sanguine.view.SanguineView;

/**
 * Class that facilitate the connection between the model and view components so the Sanguine game
 * is playable.
 */
public class BasicSanguineController implements SanguineController, FeaturesListener {
  private int cardIndex;

  /**
   * Constructs this controller to facilitate a connection between the model and view. It
   * currently only supports the view.
   *
   * @param view is the view object that displays the game.
   * @throws IllegalArgumentException if the view is null
   */
  public BasicSanguineController(SanguineView view) throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("View cannot be null");
    }

    this.cardIndex = -1;
    view.subscribe(this);
  }

  @Override
  public void handleCellClick(int row, int col) {
    System.out.println(row + " " + col);
  }

  @Override
  public void handleCardClick(int index, Team team) {
    if (index == cardIndex) {
      cardIndex = -1;
      return;
    } else {
      cardIndex = index;
    }

    if (team == Team.RED) {
      System.out.println("RED clicked card: " + index);
    } else {
      System.out.println("BLUE clicked card: " + index);
    }
  }

  @Override
  public void handleKeyPressed(String key) {
    System.out.println(key);
  }
}
