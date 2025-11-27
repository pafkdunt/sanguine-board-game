package sanguine.ai;

import java.util.List;
import sanguine.model.Card;
import sanguine.model.SanguineModel;
import sanguine.model.Team;

/**
 * Class representing the strategy of choosing the first card and location that can be played
 * and making the move there.
 */
public class FillFirst implements StrategicComputerPlayers {
  private final SanguineModel model;
  private final Team team;

  /**
   * Constructs an object that behaves as the specified computer player.
   *
   * @param model the model of the Sanguine game this strategy will be applied on
   * @param team the team of this computer player
   */
  public FillFirst(SanguineModel model, Team team) {
    if (model == null || team == null) {
      throw new IllegalArgumentException("Null argument found");
    }
    this.model = model;
    this.team = team;
  }

  @Override
  public void makeMove() {
    if (model.getCurrentPlayer() != team) {
      return;
    }

    List<Card> hand = model.getCurrentHand();
    for (int index = 0; index < hand.size(); index++) {
      for (int row = 0; row < model.getRows(); row++) {
        for (int col = 0; col < model.getCols(); col++) {
          try {
            model.move(index, row, col);
            return;
          } catch (IllegalArgumentException e) {
            // empty catch
          }
        }
      }
    }

    model.passTurn();
  }
}
