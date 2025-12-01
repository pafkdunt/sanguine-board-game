package sanguine.view;

import sanguine.model.Team;

/**
 * Interface that listens for certain player inputs in the view and
 * corresponds those to certain features provided by the listener.
 */
public interface PlayerActionListener {

  /**
   * Handles when a cell is clicked on the board for Sanguine.
   *
   * @param row the row of the cell that was clicked
   * @param col the column of the cell that was clicked
   */
  void handleCellClick(int row, int col);

  /**
   * Handles when a card is clicked/selected for the game Sanguine.
   *
   * @param index the index of the card relative the players hand
   */
  void handleCardClick(int index);

  /**
   * Handles when a key is pressed for a player to either move or pass their turn.
   *
   * @param key the key they pressed
   */
  void handleKeyPressed(String key);
}
