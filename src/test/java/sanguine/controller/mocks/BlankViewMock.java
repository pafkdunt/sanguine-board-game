package sanguine.controller.mocks;

import sanguine.model.Team;
import sanguine.view.PlayerActionListener;
import sanguine.view.SanguineView;

/**
 * Mock of the view object that is blank and meant to act as a fill in
 * where a valid SanguineView is required for the controller.
 */
public class BlankViewMock implements SanguineView {
  @Override
  public void display(boolean show) {

  }

  @Override
  public void subscribe(PlayerActionListener listener) throws IllegalArgumentException {

  }

  @Override
  public void notifyTurn() {

  }

  @Override
  public void notifyError(String message) {

  }

  @Override
  public void notifyGameOver(Team winner, String message) {

  }
}
