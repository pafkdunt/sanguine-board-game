package sanguine.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Class that represents a card in the game Sanguine, having a name, cost, value,
 * influence grid that shows the cells it influences when played, and what Team
 * it is associated with.
 *
 * @param name is the name of the card
 * @param cost is the cost to play the card
 * @param value is the value the card has
 * @param grid is the influence grind of the card
 * @param team is the team (Red or Blue) the card is on
 */
public record SanguineCard(String name, int cost, int value, boolean[][] grid, Team team)
    implements Card {

  @Override
  public SanguineCard oppositeCard() {
    boolean[][] flippedGrid = new boolean[5][5];

    for (int i = 0; i < 5; i++) {
      flippedGrid[i][0] = grid[i][4];
      flippedGrid[i][1] = grid[i][3];
      flippedGrid[i][3] = grid[i][1];
      flippedGrid[i][4] = grid[i][0];
      flippedGrid[i][2] = grid[i][2];
    }

    return new SanguineCard(name, cost, value, flippedGrid, Team.BLUE);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    } else if (other instanceof SanguineCard otherCard) {
      return (otherCard.compareInfluenceGrids(this.grid))
          && this.hashCode() == otherCard.hashCode();
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, cost, value, team);
  }

  /**
   * Compares two influence grinds (2D array of booleans) to see
   * if they are equal. Helper for the equals() method. Purpose is to
   * allow us to access the grid field of an object that has a private field
   * using delegation.
   *
   * @param otherGrid is the other influence grid we are trying to compare to
   * @return true if they are the same, false if not
   */
  private boolean compareInfluenceGrids(boolean[][] otherGrid) {
    return Arrays.deepEquals(this.grid, otherGrid);
  }
}
