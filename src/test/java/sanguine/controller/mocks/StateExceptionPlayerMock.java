package sanguine.controller.mocks;

import sanguine.model.Team;
import sanguine.player.UserPlayer;
import sanguine.view.ModelStatusListener;

/**
 * Mocked player object to test how the controller responds when player.move() or player.passTurn()
 * throws an IllegalStateException.
 */
public class StateExceptionPlayerMock implements UserPlayer {
  @Override
  public void move(int card, int row, int col) {
    throw new IllegalStateException("test for when player.move() throws a ISE");
  }

  @Override
  public void passTurn() {
    throw new IllegalStateException("test for when player.passTurn() throws a ISE");
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
