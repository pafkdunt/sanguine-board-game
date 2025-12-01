package sanguine.ai;

import sanguine.view.ModelStatusListener;

/**
 * Interface that represents the strategic computer players. Classes that implement this interface
 * will have unique strategies.
 */
public interface StrategicComputerPlayers {
  /**
   * Makes a move according to the set strategy while also abiding the rules of the game Sanguine.
   */
  void makeMove();

  /**
   * Assigns model status listener(s) to this strategic computer player object.
   *
   * @param listener the controlling listener object
   * @throws IllegalArgumentException if the input is null
   */
  void subscribe(ModelStatusListener listener) throws IllegalArgumentException;
}
