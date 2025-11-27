package sanguine.model;

/**
 * Class that represents the cells of the board.
 */
public class SanguineCell implements Cell {
  private int pawns;
  private Team team;
  private int valueOfCard;

  /**
   * Constructor for the initial Cell object.
   *
   * @param pawns integer number of pawns in the cell
   * @param team enum team of the cell
   */
  public SanguineCell(int pawns, Team team) {
    this.pawns = pawns;
    this.team = team;
    this.valueOfCard = 0;
  }

  /**
   * Constructor for the copy of a Cell object.
   *
   * @param pawns integer number of pawns in the cell
   * @param team enum team of the cell
   * @param valueOfCard integer number of the value of the card in cell
   */
  public SanguineCell(int pawns, Team team, int valueOfCard) {
    this.pawns = pawns;
    this.team = team;
    this.valueOfCard = valueOfCard;
  }

  @Override
  public void convertTeam(Team team) {
    this.team = team;
  }

  @Override
  public void addPawn() {
    if (this.pawns < 3) {
      this.pawns++;
    }
  }

  @Override
  public void placeCard(Card card) throws IllegalArgumentException {
    if (card == null) {
      throw new IllegalArgumentException("Card cannot be null.");
    }

    if (card.team() != this.team) {
      throw new IllegalArgumentException("This is not your cell.");
    }

    if (card.cost() > this.pawns) {
      throw new IllegalArgumentException("Not enough pawns to place this card.");
    }

    this.valueOfCard = card.value();
    this.pawns = 0;
  }

  @Override
  public int getPawns() {
    return this.pawns;
  }

  @Override
  public Team getTeam() {
    return this.team;
  }

  @Override
  public int getValue() {
    return this.valueOfCard;
  }

  @Override
  public String toString() {
    if (this.getValue() > 0) {
      if (this.team == Team.RED) {
        return "R";
      } else {
        return "B";
      }
    }

    if (this.pawns > 0) {
      return Integer.toString(this.pawns);
    }

    return "_";
  }
}
