package sanguine.player;

import sanguine.model.Team;
import sanguine.view.ModelStatusListener;

/**
 * Empty implementation of ModelStatusListener for the purpose of testing the player classes.
 */
public class MockModelStatusListener implements ModelStatusListener {
  @Override
  public void handleTeamChange() {

  }

  @Override
  public void handleGameOver(Team winner, String message) {

  }
}
