package sanguine.Player;

import sanguine.model.SanguineModel;
import sanguine.model.Team;

/**
 * Class representing the Red player. They could be AI/machine.
 */
public class RedPlayer extends AbstractUserPlayer {
  private final boolean machine;

  /**
   * Constructor for the Red player. Feeds in the model and
   * a boolean of whether they are a machine.
   *
   * @param model is the model for the player
   * @param machine is the boolean determining if they are a machine or not
   */
  public RedPlayer(SanguineModel model, boolean machine) {
    super(model);
    this.machine = machine;
  }

  @Override
  public Team getTeam() {
    return Team.RED;
  }
}
