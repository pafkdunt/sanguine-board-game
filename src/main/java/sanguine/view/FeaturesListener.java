package sanguine.view;

import sanguine.model.Team;

/**
 * Interface that listens for certain inputs in the view and
 * corresponds those to certain features provided by the listener.
 */
public interface FeaturesListener {

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
   * @param team the team of the player that clicked the card
   */
  void handleCardClick(int index, Team team);

  /**
   * Handles when a key is pressed for a player to either move or pass their turn.
   *
   * @param key the key they pressed
   */
  void handleKeyPressed(String key);

  /**
   * Handles when the turn changes due to making a move or passing.
   * Shows a pop-up window for the player whose turn it became.
   */
  void handleTeamChange();

  /**
   * Handles when the game is over by the turn being consecutively passed twice.
   * Relays to the view to reflect this.
   *
   * @param winner the team of the winning player
   * @param message the string containing the player who won and their score
   */
  void handleGameOver(Team winner, String message);
}
