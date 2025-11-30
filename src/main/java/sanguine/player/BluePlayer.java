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
   * @param machine is the boolean determining if they are a machine or not
   */
  public BluePlayer(SanguineModel model, boolean machine) {
    super(model, machine);
  }

  @Override
  public Team getTeam() {
    return Team.BLUE;
  }
}
