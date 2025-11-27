package sanguine.model;

/**
 * Interface for a board design in the game Sanguine.
 */
public interface Board {
  /**
   * Performs a move of the game. Takes in a card and the coordinate for the cell
   * to use the card on (as row and col), checks for valid move, then performs the move.
   *
   * @param card the card to use
   * @param row the integer row coordinate of the board to perform move on
   * @param col the integer column coordinate of the board to perform move on
   * @throws IllegalArgumentException if the desired cell to perform a move is out of bounds
   * @throws IllegalArgumentException if the team is different from the card
   * @throws IllegalArgumentException if there are not enough pawns to pay for the cost
   *                                  of the given card
   */
  void performMove(Card card, int row, int col) throws IllegalArgumentException;

  /**
   * Affects the cell influenced by a placed card. Takes in the row and col coordinate of the cell
   * that is influenced and its team, then modifies the values accordingly.
   *
   * @param row the integer row coordinate of the cell to be influenced
   * @param col the integer column coordinate of the cell to be influenced
   * @param affectingTeam the current team of the cell to be influenced
   * @throws IllegalArgumentException if the affecting team is null
   */
  void affectCell(int row, int col, Team affectingTeam) throws IllegalArgumentException;

  /**
   * Observer for a cell, takes in the row and column coordinate of the board and returns the cell.
   *
   * @param row the integer row coordinate of the desired cell
   * @param col the integer column coordinate of the desired cell
   * @return the cell in the given coordinate
   * @throws IllegalArgumentException if invalid row or column values are provided
   */
  Cell getCell(int row, int col) throws IllegalArgumentException;
}
