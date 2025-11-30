package sanguine.ai.mocks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sanguine.model.Card;
import sanguine.model.Cell;
import sanguine.model.SanguineModel;
import sanguine.model.Team;

/**
 * Mocked model where the first move possible is a valid move so it is made.
 */
public class FirstMoveValidModel implements SanguineModel {
  private final Appendable log;

  /**
   * Constructs the mocked model with a log to write the result.
   *
   * @param log writes the result
   * @throws IllegalArgumentException log is null
   */
  public FirstMoveValidModel(Appendable log) throws IllegalArgumentException {
    if (log == null) {
      throw new IllegalArgumentException("log cannot be null");
    }

    this.log = log;
  }

  @Override
  public void move(int card, int row, int col)
      throws IllegalStateException, IllegalArgumentException {
    String moveMade = "Card Index: " + card + "\nRow: " + row + "\nColumn: " + col + "\n";
    try {
      log.append(moveMade);
    } catch (IOException e) {
      // empty catch
    }
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

  @Override
  public List<Card> getCurrentHand() throws IllegalStateException {
    List<Card> hand = new ArrayList<>();

    // create a hand of size three. Contents of the hand are irrelevant
    for (int cardIndex = 0; cardIndex < 3; cardIndex++) {
      hand.add(null);
    }

    return hand;
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
