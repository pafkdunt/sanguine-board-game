package sanguine.ai;

import java.util.ArrayList;
import java.util.List;
import sanguine.model.Card;
import sanguine.model.SanguineModel;
import sanguine.model.Team;
import sanguine.view.ModelStatusListener;

/**
 * Class representing the strategy of placing first card that would win a row, moving from the top.
 */
public class MaximizeRowScore implements StrategicComputerPlayers {
  private final SanguineModel model;
  private final Team team;
  private final List<ModelStatusListener> modelStatusListeners;

  /**
   * Constructs an object that behaves as the specified computer player.
   *
   * @param model the model of the Sanguine game this strategy will be applied on
   * @param team the team of this computer player
   */
  public MaximizeRowScore(SanguineModel model, Team team) {
    if (model == null || team == null) {
      throw new IllegalArgumentException("Null argument found");
    }
    this.model = model;
    this.team = team;
    this.modelStatusListeners = new ArrayList<>();
  }

  @Override
  public void makeMove() {
    if (model.getCurrentPlayer() != team) {
      return;
    }

    List<Card> hand = model.getCurrentHand();

    int[] ourScores;
    int[] opponentScores;

    if (team == Team.BLUE) {
      ourScores = model.getBlueScore();
      opponentScores = model.getRedScore();
    } else {
      ourScores = model.getRedScore();
      opponentScores = model.getBlueScore();
    }

    for (int index = 0; index < hand.size(); index++) {
      for (int row = 0; row < model.getRows(); row++) {
        for (int col = 0; col < model.getCols(); col++) {
          if (hand.get(index).value() + ourScores[row] > opponentScores[row]) {
            try {
              model.move(index, row, col);
              return;
            } catch (IllegalArgumentException e) {
              // empty catch
            }
          }
        }
      }
    }

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
  }

  @Override
  public void subscribe(ModelStatusListener listener) throws IllegalArgumentException {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null");
    }

    this.modelStatusListeners.add(listener);
  }
}
