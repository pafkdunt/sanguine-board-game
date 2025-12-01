package sanguine.controller.mocks;

import sanguine.model.Team;
import sanguine.player.UserPlayer;
import sanguine.view.ModelStatusListener;

/**
 * Mock of the player object that is blank and meant to act as a fill in
 * where a valid UserPlayer is required for the controller.
 */
public class BlankPlayerMock implements UserPlayer {
  @Override
  public void move(int card, int row, int col) {

  }

  @Override
  public void passTurn() {

  }

  @Override
  public Team getTeam() {
    return null;
  }

  @Override
  public void subscribe(ModelStatusListener listener) throws IllegalArgumentException {

  }

  @Override
  public boolean isItMyTurn() {
    return false;
  }
}
