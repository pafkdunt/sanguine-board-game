package sanguine.Player;


import sanguine.model.SanguineModel;
import sanguine.model.Team;
import sanguine.view.FeaturesListener;

/**
 * Abstract class implements functionality of the Player
 * and uses the factory method to decide which player is making the move.
 */
public abstract class AbstractUserPlayer implements UserPlayer {
  private final SanguineModel model;
  private FeaturesListener listener;

  /**
   * Constructs an abstract player.
   *
   * @param model is the model of the game Sanguine
   */
  public AbstractUserPlayer(SanguineModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }

    this.model = model;
  }

  @Override
  public void move(int card, int row, int col) {
    if (isItMyTurn()) {
      model.move(card, row, col);
      listener.handleTeamChange();
    }
  }

  @Override
  public void passTurn() {
    if (isItMyTurn()) {
      model.passTurn();
      listener.handleTeamChange();
    }
  }

  public boolean isItMyTurn() {
    return getTeam() == model.getCurrentPlayer();
  }

  @Override
  public void subscribe(FeaturesListener listener) {
    this.listener = listener;
  }

  /**
   * Factory method that allows subclasses to select the correct team
   * so the above methods can work appropriately. Also acts as on observor
   * for this plays team.
   *
   * @return the team of this player
   */
  public abstract Team getTeam();
}
