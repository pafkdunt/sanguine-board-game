package sanguine.ai.mocks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sanguine.model.Card;
import sanguine.model.Cell;
import sanguine.model.SanguineCard;
import sanguine.model.SanguineModel;
import sanguine.model.Team;

/**
 * Mocked model where an arbitrary move wins a row for the RED team.
 */
public class ArbitraryMoveWinsRowModel implements SanguineModel {
  private final Appendable log;

  /**
   * Constructs the mocked model with a log to write the result.
   *
   * @param log writes the result
   * @throws IllegalArgumentException log is null
   */
  public ArbitraryMoveWinsRowModel(Appendable log) throws IllegalArgumentException {
    if (log == null) {
      throw new IllegalArgumentException("log cannot be null");
    }

    this.log = log;
  }

  @Override
  public void move(int card, int row, int col)
      throws IllegalStateException, IllegalArgumentException {
    try {
      String moveMade = "Card Index: " + card + "\nRow: " + row + "\nColumn: " + col + "\n";
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
    return new int[] {2, 2, 5};
  }

  @Override
  public int[] getBlueScore() {
    return new int[] {5, 2, 5};
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
      Card card = new SanguineCard("Filler", 0, 1, null, Team.RED);
      hand.add(card);
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
