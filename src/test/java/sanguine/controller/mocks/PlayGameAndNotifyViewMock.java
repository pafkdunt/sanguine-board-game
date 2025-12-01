package sanguine.controller.mocks;

import java.io.IOException;
import sanguine.model.Team;
import sanguine.view.PlayerActionListener;
import sanguine.view.SanguineView;

/**
 * Mock of the view object that records a message to a log when the view is displayed or
 * sends out a notification.
 */
public class PlayGameAndNotifyViewMock implements SanguineView {
  private final Appendable log;

  /**
   * Constructor for PlayGameAndNotifyViewMock that initializes the Appendable
   * that will log what the view does when it is displayed or displays a notification.
   *
   * @param log the Appendable that logs what the view does
   * @throws IllegalArgumentException if the given log is null
   */
  public PlayGameAndNotifyViewMock(Appendable log) throws IllegalArgumentException {
    if (log == null) {
      throw new NullPointerException("log cannot be null.");
    }

    this.log = log;
  }

  @Override
  public void display(boolean show) {
    try {
      String input = show + "";
      log.append(input);
    } catch (IOException e) {
      // empty catch
    }
  }

  @Override
  public void subscribe(PlayerActionListener listener) throws IllegalArgumentException {

  }

  @Override
  public void notifyTurn() {
    try {
      String message = "it's now your turn";
      log.append(message);
    } catch (IOException e) {
      // empty catch
    }
  }

  @Override
  public void notifyError(String message) {
    try {
      log.append(message);
    } catch (IOException e) {
      // empty catch
    }
  }

  @Override
  public void notifyGameOver(Team winner, String message) {
    try {
      String output;
      if (winner == null) {
        output = "winner was null, meaning a tie";
      } else {
        output = "winner was this team: " + winner;
      }
      log.append(output);
    } catch (IOException e) {
      // empty catch
    }
  }
}
