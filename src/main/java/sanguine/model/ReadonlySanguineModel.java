package sanguine.model;

import java.util.List;

/**
 * This is the read-only version of the sanguine model.
 */
public interface ReadonlySanguineModel {
  /**
   * Checks whether the game is over. Does so by checking the number of consecutive passes.
   *
   * @return the boolean value representing whether the game has ended
   */
  boolean isGameOver();

  /**
   * Figures out who is the winner of the game once it is over.
   *
   * @return the team that has won
   * @throws IllegalStateException tries to get winner when the game is not over
   */
  Team findWinner() throws IllegalStateException;

  /**
   * Observer for the Red players scores for each row.
   *
   * @return the array of Red's scores for each row
   */
  int[] getRedScore();

  /**
   * Observer for the Blue players scores for each row.
   *
   * @return the array of Blue's scores for each row
   */
  int[] getBlueScore();

  /**
   * Observer for the number of rows in the game.
   *
   * @return the integer number of the rows in the game
   */
  int getRows();

  /**
   * Observer for the number of columns in the game.
   *
   * @return the integer number of the columns in the game
   */
  int getCols();

  /**
   * Observer for the current player.
   *
   * @return the current player
   */
  Team getCurrentPlayer() throws IllegalStateException;

  /**
   * Observer for a cell, takes in the row and column coordinate of the game board
   * and returns the cell.
   *
   * @param row the integer row coordinate of the desired cell
   * @param col the integer column coordinate of the desired cell
   * @return the cell in the given coordinate
   * @throws IllegalArgumentException if invalid row or column values are provided
   */
  Cell getCell(int row, int col) throws IllegalArgumentException;

  /**
   * Gets the current score of the team.
   *
   * @param team of the score
   * @return the integer representation of the current score of the given team
   */
  int getCurrentScore(Team team);

  /**
   * Observer for the current players hand.
   *
   * @return the list of cards in the current players hand
   * @throws IllegalStateException if the game is over
   */
  List<Card> getCurrentHand() throws IllegalStateException;
}
