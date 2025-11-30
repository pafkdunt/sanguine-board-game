package sanguine.view;

import sanguine.model.Team;

/**
 * Interface that listens for certain changes in the model and
 * corresponds those to certain features provided by the listener.
 */
public interface ModelStatusListener {
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
