package sanguine.player;

import sanguine.model.SanguineModel;
import sanguine.model.Team;

/**
 * Class representing the Red player. They could be AI/machine.
 */
public class RedPlayer extends AbstractUserPlayer {
  /**
   * Constructor for the Red player. Feeds in the model and a boolean whether they are a machine.
   *
   * @param model is the model for the player
   * @param playerType is the type the player is (human, strategy1, or strategy2)
   */
  public RedPlayer(SanguineModel model, String playerType) {
    super(model, playerType);
  }

  @Override
  public Team getTeam() {
    return Team.RED;
  }
}
