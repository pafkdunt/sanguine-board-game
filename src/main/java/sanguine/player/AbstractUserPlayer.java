package sanguine.player;

import java.util.ArrayList;
import java.util.List;
import sanguine.ai.FillFirst;
import sanguine.ai.MaximizeRowScore;
import sanguine.ai.StrategicComputerPlayers;
import sanguine.model.SanguineModel;
import sanguine.model.Team;
import sanguine.view.ModelStatusListener;

/**
 * Abstract class implements functionality of the Player
 * and uses the factory method to decide which player is making the move.
 */
public abstract class AbstractUserPlayer implements UserPlayer {
  private final SanguineModel model;
  private final List<ModelStatusListener> modelStatusListeners;
  private final String playerType;
  private final StrategicComputerPlayers fillFirst;
  private final StrategicComputerPlayers maxRow;

  /**
   * Constructs an abstract player.
   *
   * @param model is the model of the game Sanguine
   * @param playerType is the type the player is (human, strategy1, or strategy2)
   * @throws IllegalArgumentException if the model is null
   */
  public AbstractUserPlayer(SanguineModel model, String playerType) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }

    this.model = model;
    this.playerType = playerType;
    this.modelStatusListeners = new ArrayList<>();
    this.fillFirst = new FillFirst(this.model, this.getTeam());
    this.maxRow = new MaximizeRowScore(this.model, this.getTeam());
  }

  @Override
  public void move(int card, int row, int col) {
    if (isItMyTurn()) {
      if (playerType.equals("strategy1")) {
        fillFirst.makeMove();
      } else if (playerType.equals("strategy2")) {
        maxRow.makeMove();
      } else {
        model.move(card, row, col);
      }
      modelStatusListeners.getFirst().handleTeamChange();
    }
  }

  @Override
  public void passTurn() {
    if (isItMyTurn() && playerType.equals("human")) {
      model.passTurn();
      if (model.isGameOver()) {
        String message;
        Team winner = model.findWinner();

        if (winner == null) {
          message = "Game ended in a tie.";
        } else {
          message = winner + " won with score of " + model.getCurrentScore(winner);
        }

        modelStatusListeners.get(0).handleGameOver(winner, message);
        modelStatusListeners.get(1).handleGameOver(winner, message);
      }
      modelStatusListeners.getFirst().handleTeamChange();
    }
  }

  public boolean isItMyTurn() {
    return getTeam() == model.getCurrentPlayer();
  }

  @Override
  public void subscribe(ModelStatusListener listener) throws IllegalArgumentException {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null.");
    }

    this.modelStatusListeners.add(listener);

  }

  /**
   * Factory method that allows subclasses to select the correct team
   * so the above methods can work appropriately. Also acts as on observer
   * for this plays team.
   *
   * @return the team of this player
   */
  public abstract Team getTeam();
}
