package sanguine.ai.mocks;

import java.util.List;
import sanguine.model.Card;
import sanguine.model.Cell;
import sanguine.model.SanguineModel;
import sanguine.model.Team;

/**
 * Purpose of this mock is to provide a valid SanguineModel for testing that the AI
 * fails when taking in a valid model but a null team.
 */
public class FillInModel implements SanguineModel {
  @Override
  public void move(int card, int row, int col)
      throws IllegalStateException, IllegalArgumentException {

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
    return 0;
  }

  @Override
  public int getCols() {
    return 0;
  }

  @Override
  public Team getCurrentPlayer() throws IllegalStateException {
    return null;
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
    return List.of();
  }
}
