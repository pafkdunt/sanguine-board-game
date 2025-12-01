package sanguine.controller.mocks;

import java.io.IOException;
import sanguine.model.Team;
import sanguine.player.UserPlayer;
import sanguine.view.ModelStatusListener;

/**
 * Mock of the player object that records a message to a log when the player moved or passes
 * their turn.
 */
public class PlayerMoveAndPass implements UserPlayer {
  private final Appendable log;

  /**
   * Constructor for PlayerMoveAndPass that initializes the Appendable
   * that will log what the player does.
   *
   * @param log the Appendable that logs what the player does
   * @throws IllegalArgumentException if the given log is null
   */
  public PlayerMoveAndPass(Appendable log) throws IllegalArgumentException {
    if (log == null) {
      throw new IllegalArgumentException("log cannot be null.");
    }

    this.log = log;
  }

  @Override
  public void move(int card, int row, int col) {
    String message = "Card: " + card + ", Row: " + row + ", Col: " + col;
    try {
      log.append(message);
    } catch (IOException e) {
      // empty catch
    }
  }

  @Override
  public void passTurn() {
    try {
      log.append("player passes their turn");
    } catch (IOException e) {
      // empty catch
    }
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
