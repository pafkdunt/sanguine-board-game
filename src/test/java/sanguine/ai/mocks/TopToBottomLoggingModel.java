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
 * move is a valid move so the AI makes that move.
 */
public class TopToBottomLoggingModel implements SanguineModel {
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
  public TopToBottomLoggingModel(String file, Appendable log) throws IllegalArgumentException {
    if (log == null) {
      throw new IllegalArgumentException("log cannot be null");
    }

    this.cards = new ArrayList<>();
    this.log = log;
    DeckCreator.createDeck(cards, false, file);
  }

  /**
   * Only considers the very last choice to be the best(valid) choice
   * otherwise it will print out the invalid move that was made and throw
   * an exception so FillFirst can continue loop through every option.
   *
   * @param card the index of the card that the player
   *             wants to move relative to the players hand
   * @param row the row of the cell we are trying to make move into
   * @param col the col of the cell we are trying to make move into
   * @throws IllegalArgumentException if the move that is attempted is not the
   *                                  last possible option
   */
  public void move(int card, int row, int col) throws IllegalArgumentException {
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

  @Override
  public int[] getRedScore() {
    return new int[0];
  }

  @Override
  public int[] getBlueScore() {
    return new int[0];
  }

  // always will be a board with 3 rows
  @Override
  public int getRows() {
    return 3;
  }

  // always will be a board with 5 columns
  @Override
  public int getCols() {
    return 5;
  }

  // always will be RED
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
