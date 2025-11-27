package sanguine.model;

import java.util.List;

/**
 * A view only model that adapts a model that implements SanguineModel with the purpose
 * of preventing clients from typecasting what should be a model providing only
 * observer functionality and making it something that can mutate the model.
 */
public class ViewModel implements ReadonlySanguineModel {
  private final SanguineModel model;

  /**
   * Constructs the view model with a given model that implements the SanguineModel interface.
   *
   * @param model the given model that implements the SanguineModel interface
   */
  public ViewModel(SanguineModel model) {
    this.model = model;
  }

  @Override
  public boolean isGameOver() {
    return model.isGameOver();
  }

  @Override
  public Team findWinner() throws IllegalStateException {
    return model.findWinner();
  }

  @Override
  public int[] getRedScore() {
    return model.getRedScore();
  }

  @Override
  public int[] getBlueScore() {
    return model.getBlueScore();
  }

  @Override
  public int getRows() {
    return model.getRows();
  }

  @Override
  public int getCols() {
    return model.getCols();
  }

  @Override
  public Team getCurrentPlayer() throws IllegalStateException {
    return model.getCurrentPlayer();
  }

  @Override
  public Cell getCell(int row, int col) throws IllegalArgumentException {
    return model.getCell(row, col);
  }

  @Override
  public int getCurrentScore(Team team) {
    return model.getCurrentScore(team);
  }

  @Override
  public List<Card> getCurrentHand() throws IllegalStateException {
    return model.getCurrentHand();
  }
}
