package sanguine.view;

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
}
