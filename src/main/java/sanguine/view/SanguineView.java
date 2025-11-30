package sanguine.view;

import sanguine.model.Team;

/**
 * Represents the view section of the game Sanguine
 * in a Model-View-Controller design.
 */
public interface SanguineView {

  /**
   * Displays the view.
   *
   * @param show displays the view if true, else it does not
   */
  void display(boolean show);

  /**
   * Subscribes a given FeatureListener to the view so that inputs in the view
   * can be reflected in other components.
   *
   * @param listener is the FeaturesListener that deals with the view's inputs
   * @throws IllegalArgumentException if the listener is null
   */
  void subscribe(FeaturesListener listener) throws IllegalArgumentException;

  /**
   * Indicates the change of the turn by displaying a pop-up to the player whose turn it became.
   */
  void notifyTurn();

  /**
   * Indicates an error made by the player by displaying a pop-up to the same player
   * including what invalid action was done.
   *
   * @param message the string describing what error was made
   */
  void notifyError(String message);

  /**
   * Indicates the end of the game to both players through a pop-up,
   * which tells who won with what score. If the game ended in a tie, both players receive same
   * messages indicating a tie.
   *
   * @param winner the team of the winning player, if it is a tie, this is null
   * @param message the message to display that indicates game over
   */
  void notifyGameOver(Team winner, String message);
}
