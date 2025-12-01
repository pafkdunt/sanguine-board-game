package sanguine.player;

import sanguine.model.SanguineModel;
import sanguine.model.Team;

/**
 * Class representing the Blue player. They could be AI/machine.
 */
public class BluePlayer extends AbstractUserPlayer {
  /**
   * Constructor for the Blue player. Feeds in the model and a boolean whether they are a machine.
   *
   * @param model is the model for the player
   * @param playerType is the type the player is (human, strategy1, or strategy2)
   */
  public BluePlayer(SanguineModel model, String playerType) {
    super(model, playerType);
  }

  @Override
  public Team getTeam() {
    return Team.BLUE;
  }
}
