package sanguine.model;

/**
 * Interface for a card design in the game Sanguine.
 */
public interface Card {

  /**
   * Observer for the name of the card.
   *
   * @return the name of the card
   */
  String name();

  /**
   * Observer for the cost of the card.
   *
   * @return the cost of the card
   */
  int cost();

  /**
   * Observer for the value of the card.
   *
   * @return the value of the card
   */
  int value();

  /**
   * Observer for the influence grid of the card.
   *
   * @return the influence grid of the card
   */
  boolean[][] grid();

  /**
   * Observer for the Team of the card.
   *
   * @return the Team of the card
   */
  Team team();

  /**
   * Returns the appropriate opposite card for this specific card.
   *
   * @return the "flipped" card, with flipped influence grid and team
   */
  Card oppositeCard();

  /**
   * Checks whether two Cards are equal based on their fields, meaning
   * they represent the same card in the respect to the game.
   *
   * @param other the reference object with which to compare.
   * @return true if they represent the same card, false if they do not
   */
  boolean equals(Object other);

  /**
   * Gets the hashcode of this Card object.
   *
   * @return the hashcode of this Card
   */
  int hashCode();
}
