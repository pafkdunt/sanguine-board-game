package sanguine.player;

import sanguine.model.Team;
import sanguine.view.ModelStatusListener;

/**
 * Interface describing a user playing the game Sanguine.
 */
public interface UserPlayer {

  /**
   * Method which the player uses to make a move.
   *
   * @param card the index of the card the player wants to play from their hand of cards
   * @param row the row of the cell they want to play the card
   * @param col the column of the cell they want to play the card
   * @throws IllegalStateException if the game is over
   * @throws IllegalStateException if it is not the player who is trying to move's turn
   * @throws IllegalArgumentException if invalid row or column values are provided, meaning the
   *                                  desired cell to perform a move is out of bounds
   * @throws IllegalArgumentException if the player is tyring to move a card that isn't theirs
   * @throws IllegalArgumentException if there are not enough pawns to pay for the cost
   *                                  of the given card
   */
  void move(int card, int row, int col);

  /**
   * Method which the player uses to pass their turn.
   *
   * @throws IllegalStateException if the game is over
   * @throws IllegalStateException if it is not the turn of the person trying to pass
   */
  void passTurn();

  /**
   * Observer for the team of this player object.
   *
   * @return the team of this object
   */
  Team getTeam();

  /**
   * Assigns model status listener(s) to this player object.
   *
   * @param listener the controlling listener object
   * @throws IllegalArgumentException if the input is null
   */
  void subscribe(ModelStatusListener listener) throws IllegalArgumentException;

  /**
   * Indicates whether it is this player object's turn.
   *
   * @return true if it is this player's turn, false if not
   */
  boolean isItMyTurn();
}
