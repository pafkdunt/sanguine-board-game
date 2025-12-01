package sanguine.controller.mocks;

import sanguine.model.Team;
import sanguine.player.UserPlayer;
import sanguine.view.ModelStatusListener;

/**
 * Mocked player object to test how the controller responds when player.move() throws an
 * IllegalArgumentException.
 */
public class ArgsExceptionPlayerMock implements UserPlayer {
  @Override
  public void move(int card, int row, int col) {
    throw new IllegalArgumentException("test for when player.move() throws a IAE");
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
