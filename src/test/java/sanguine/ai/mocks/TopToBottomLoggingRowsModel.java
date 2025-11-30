package sanguine.ai.mocks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sanguine.model.Card;
import sanguine.model.Cell;
import sanguine.model.DeckCreator;
import sanguine.model.SanguineModel;
import sanguine.model.Team;

/**
 * Mocked model with a hand that uses the deck file form HW5 and logs the moves that the AI makes
 * to see that it is going top to bottom, left to right, and also checks that the last possible
 * move is a move that wins the row so the AI makes that move.
 */
public class TopToBottomLoggingRowsModel implements SanguineModel {
  private final List<Card> cards;
  private final Appendable log;

  /**
   * Constructs the mocked model with hand that is created from a given file,
   * and a log to write the result.
   *
   * @param file the file that is used to create the hand for the AI
   * @param log writes the result
   * @throws IllegalArgumentException log is null
   */
  public TopToBottomLoggingRowsModel(String file, Appendable log) throws IllegalArgumentException {
    if (log == null) {
      throw new IllegalArgumentException("log cannot be null");
    }

    this.cards = new ArrayList<>();
    this.log = log;
    DeckCreator.createDeck(cards, false, file);
  }

  @Override
  public void move(int card, int row, int col)
      throws IllegalStateException, IllegalArgumentException {
    if (card == (cards.size() - 1) && row == 2 && col == 4) {
      String validMove = "Card Index: " + card + "\nRow: " + row
          + "\nColumn: " + col + "\nMove was VALID\n";

      System.out.println(validMove);

      try {
        log.append(validMove);
      } catch (IOException e) {
        // empty catch
      }

      return;
    }

    System.out.println("Card Index: " + card + "\nRow: " + row
        + "\nColumn: " + col + "\nMove was INVALID\n");
    throw new IllegalArgumentException();
  }

  @Override
  public void passTurn() throws IllegalStateException {

  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public Team findWinner() throws IllegalStateException {
    return null;
  }

  // all 0's so that an opponents card with any value greater than 1 will win that row
  @Override
  public int[] getRedScore() {
    return new int[] {0, 0, 0};
  }

  // all 0's so that an opponents card with any value greater than 1 will win that row
  @Override
  public int[] getBlueScore() {
    return new int[] {0, 0, 0};
  }

  @Override
  public int getRows() {
    return 3;
  }

  @Override
  public int getCols() {
    return 5;
  }

  @Override
  public Team getCurrentPlayer() throws IllegalStateException {
    return Team.RED;
  }

  @Override
  public Cell getCell(int row, int col) throws IllegalArgumentException {
    return null;
  }

  @Override
  public int getCurrentScore(Team team) {
    return 0;
  }

  // if every card has a value 1 then it passes the check that the row score + card value
  // will win the row
  @Override
  public List<Card> getCurrentHand() throws IllegalStateException {
    return this.cards;
  }

  @Override
  public List<Card> getRedHand() {
    return List.of();
  }

  @Override
  public List<Card> getBlueHand() {
    return List.of();
  }
}
