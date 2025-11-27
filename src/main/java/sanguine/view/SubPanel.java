package sanguine.view;

/**
 * Sub panels of main panels that deal with highlighting when they are clicked.
 */
public interface SubPanel {

  /**
   * Highlights the panel a cyan color. Removes highlight if already highlighted.
   */
  void highlight();

  /**
   * Removes the highlight of a panel.
   */
  void removeHighlight();
}
