package sanguine.ai;

/**
 * Interface that represents the strategic computer players. Classes that implement this interface
 * will have unique strategies.
 */
public interface StrategicComputerPlayers {
  /**
   * Makes a move according to the set strategy while also abiding the rules of the game Sanguine.
   */
  void makeMove();
}
