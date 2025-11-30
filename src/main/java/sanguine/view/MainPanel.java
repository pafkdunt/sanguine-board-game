package sanguine.view;

/**
 * Interface representing the main panels that build the JFrame
 * for the game Sanguine.
 */
public interface MainPanel {

  /**
   * Subscribe's the panel to a feature listener so that it can
   * handle certain features based on input to the panel.
   *
   * @param listener is the listener that acts based on input
   * @throws IllegalArgumentException if the listener is null
   */
  void subscribe(FeaturesListener listener) throws IllegalArgumentException;

  /**
   * Repaints the panel to reflect the changes made to the state of the game.
   */
  void refresh();
}
