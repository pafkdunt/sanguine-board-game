package sanguine.player;

import java.util.ArrayList;
import java.util.List;
import sanguine.ai.MaximizeRowScore;
import sanguine.ai.StrategicComputerPlayers;
import sanguine.model.SanguineModel;
import sanguine.model.Team;
import sanguine.view.FeaturesListener;

/**
 * Abstract class implements functionality of the Player
 * and uses the factory method to decide which player is making the move.
 */
public abstract class AbstractUserPlayer implements UserPlayer {
  private final SanguineModel model;
  private final List<FeaturesListener> listeners;
  private final boolean machine;

  /**
   * Constructs an abstract player.
   *
   * @param model is the model of the game Sanguine
   */
  public AbstractUserPlayer(SanguineModel model, boolean machine) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }

    this.model = model;
    this.machine = machine;
    this.listeners = new ArrayList<>();
  }

  @Override
  public void move(int card, int row, int col) {
    if (isItMyTurn()) {
      if (machine) {
        StrategicComputerPlayers maxRow = new MaximizeRowScore(this.model, this.getTeam());
        maxRow.makeMove();
      } else {
        model.move(card, row, col);
      }
      listeners.getFirst().handleTeamChange();
    }
  }

  @Override
  public void passTurn() {
    if (isItMyTurn()) {
      model.passTurn();
      if (model.isGameOver()) {
        String message;
        Team winner = model.findWinner();

        if (winner == null) {
          message = "Game ended in a tie.";
        } else {
          message = winner + " won with score of " + model.getCurrentScore(winner);
        }

        listeners.get(0).handleGameOver(winner, message);
        listeners.get(1).handleGameOver(winner, message);
      }
      listeners.getFirst().handleTeamChange();
    }
  }

  public boolean isItMyTurn() {
    return getTeam() == model.getCurrentPlayer();
  }

  @Override
  public void subscribe(FeaturesListener listener) {
    this.listeners.add(listener);
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
