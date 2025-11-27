package sanguine.model;

/**
 * Interface for an individual cell's design in the game Sanguine.
 */
public interface Cell {

  /**
   * Converts this cell to the given team.
   *
   * @param team the team to convert to
   */
  void convertTeam(Team team);

  /**
   * Adds a pawn to the cell, increasing its pawns number by one.
   * Only adds if the number of pawns is 2 or less before addition.
   */
  void addPawn();

  /**
   * Places a card in this cell. The cell is checked whether the card can be placed,
   * then pawns are reset for clarification purposes,
   * and the value of the cell changes to that of the card.
   *
   * @param card the card that is being placed
   * @throws IllegalArgumentException if the team is different from the card
   * @throws IllegalArgumentException if there are not enough pawns to pay for the cost
   *                                  of the given card
   */
  void placeCard(Card card) throws IllegalArgumentException;

  /**
   * Observer for the number of pawns in the cell.
   *
   * @return the integer number of the pawns in the cell
   */
  int getPawns();

  /**
   * Getter for the team of the cell.
   *
   * @return the enum team of the cell
   */
  Team getTeam();

  /**
   * Observer for the value of the cell (a.k.a. the value of the card placed in the cell).
   *
   * @return the integer number of the value
   */
  int getValue();

  /**
   * The cell as a string representation.
   *
   * @return the appropriate string for this cell: If a card is placed in it, R if the team is Red
   *         and B is it is Blue. Also returns the number of pawns as a string if no card is placed
   *         on this cell. Otherwise, if the cell is empty, returns an underscore
   */
  String toString();
}
