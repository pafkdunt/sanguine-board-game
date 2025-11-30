package sanguine.model;

/**
 * Represents the model section of the game Sanguine
 * in a Model-View-Controller design.
 */
public interface SanguineModel extends ReadonlySanguineModel {

  /**
   * Makes a move in the game.
   *
   * @param card the index of the card that the player
   *             wants to move relative to the players hand
   * @param row the row of the cell we are trying to make move into
   * @param col the col of the cell we are trying to make move into
   * @throws IllegalStateException if the game is over
   * @throws IllegalArgumentException if invalid row or column values are provided, meaning the
   *                                  desired cell to perform a move is out of bounds
   * @throws IllegalArgumentException if there are not enough pawns to pay for the cost
   *                                  of the given card
   *
   */
  void move(int card, int row, int col)
      throws IllegalStateException, IllegalArgumentException;

  /**
   * Passes the turn. If the game is already over, or it is not their turn, throws an error.
   *
   * @throws IllegalStateException if the game is over
   */
  void passTurn() throws IllegalStateException;
}