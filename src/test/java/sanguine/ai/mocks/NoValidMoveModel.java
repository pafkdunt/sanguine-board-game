package sanguine.ai.mocks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sanguine.model.Card;
import sanguine.model.Cell;
import sanguine.model.SanguineModel;
import sanguine.model.Team;

/**
 * Mocked model that has no possible valid moves so AI passes turn.
 */
public class NoValidMoveModel implements SanguineModel {
  private final Appendable log;

  /**
   * Constructs the mocked model with a log to write the result.
   *
   * @param log writes the result
   * @throws IllegalArgumentException log is null
   */
  public NoValidMoveModel(Appendable log) throws IllegalArgumentException {
    if (log == null) {
      throw new IllegalArgumentException("log cannot be null");
    }

    this.log = log;
  }


  @Override
  public void move(int card, int row, int col)
      throws IllegalStateException, IllegalArgumentException {
    throw new IllegalArgumentException();
  }

  @Override
  public void passTurn() throws IllegalStateException {
    try {
      log.append("Passed turn");
    } catch (IOException e) {
      // empty catch
    }
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
    for (int cardIdx = 0; cardIdx < 3; cardIdx++) {
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
