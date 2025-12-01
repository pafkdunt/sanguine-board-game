package sanguine.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import sanguine.controller.mocks.ArgsExceptionPlayerMock;
import sanguine.controller.mocks.BlankPlayerMock;
import sanguine.controller.mocks.BlankViewMock;
import sanguine.controller.mocks.PlayGameAndNotifyViewMock;
import sanguine.controller.mocks.PlayerMoveAndPass;
import sanguine.controller.mocks.StateExceptionPlayerMock;
import sanguine.model.Team;
import sanguine.player.UserPlayer;
import sanguine.view.ModelStatusListener;
import sanguine.view.PlayerActionListener;
import sanguine.view.SanguineView;

/**
 * Testing class to test a basic Sanguine controller ensuring proper interaction
 * between the player/model and view through the use of mocks.
 */
public class BasicSanguineControllerTest {

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidConstructionNullView() {
    SanguineController controller = new BasicSanguineController(null, new BlankPlayerMock());
  }

  // used a mock from a later test for the view here to better
  // test the controller and remove the variables that come
  // with using the actual View
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidConstructionNullPlayer() {
    SanguineController controller = new BasicSanguineController(
        new PlayGameAndNotifyViewMock(new StringBuilder()), null);
  }

  @Test
  public void testValidConstruction() {
    SanguineController controller = new BasicSanguineController(
        new PlayGameAndNotifyViewMock(new StringBuilder()), new BlankPlayerMock());
  }

  @Test
  public void testPlayGame() {
    Appendable log = new StringBuilder();
    SanguineView view = new PlayGameAndNotifyViewMock(log);

    SanguineController controller = new BasicSanguineController(view, new BlankPlayerMock());

    controller.playGame();
    assertEquals("true",  log.toString());
  }


  // ====== Player Action Tests ====== \\

  // - test handleCellClick ???
  @Test
  public void testCellClick() {

  }

  // - test handleCardClick ???
  @Test
  public void testCardClick() {

  }


  @Test
  public void testHandleKeyPressedInvalidKey() {
    Appendable log = new StringBuilder();
    SanguineView view = new PlayGameAndNotifyViewMock(log);

    PlayerActionListener controller = new BasicSanguineController(view, new BlankPlayerMock());

    controller.handleKeyPressed("H");

    assertEquals("Invalid key", log.toString());
  }

  @Test
  public void testHandleKeyPressedMoveNoSelection() {
    Appendable log = new StringBuilder();
    UserPlayer player = new PlayerMoveAndPass(log);


    PlayerActionListener controller = new BasicSanguineController(new BlankViewMock(), player);

    controller.handleKeyPressed("ENTER");

    assertEquals("Card: -1, Row: -1, Col: -1", log.toString());
  }

  @Test
  public void testHandleKeyPressedMoveSelection() {
    Appendable log = new StringBuilder();
    UserPlayer player = new PlayerMoveAndPass(log);


    PlayerActionListener controller = new BasicSanguineController(new BlankViewMock(), player);

    controller.handleCardClick(2);
    controller.handleCellClick(2, 3);
    controller.handleKeyPressed("ENTER");

    assertEquals("Card: 2, Row: 2, Col: 3", log.toString());
  }

  @Test
  public void testHandleKeyPressedMoveIllegalArgsException() {
    Appendable log = new StringBuilder();
    UserPlayer player = new ArgsExceptionPlayerMock();
    SanguineView view = new PlayGameAndNotifyViewMock(log);


    PlayerActionListener controller = new BasicSanguineController(view, player);

    controller.handleKeyPressed("ENTER");

    assertEquals("test for when player.move() throws a IAE", log.toString());
  }

  @Test
  public void testHandleKeyPressedMoveIllegalStateException() {
    Appendable log = new StringBuilder();
    UserPlayer player = new StateExceptionPlayerMock();
    SanguineView view = new PlayGameAndNotifyViewMock(log);


    PlayerActionListener controller = new BasicSanguineController(view, player);

    controller.handleKeyPressed("ENTER");

    assertEquals("test for when player.move() throws a ISE", log.toString());
  }

  @Test
  public void testHandleKeyPressedPass() {
    Appendable log = new StringBuilder();
    UserPlayer player = new PlayerMoveAndPass(log);


    PlayerActionListener controller = new BasicSanguineController(new BlankViewMock(), player);

    controller.handleKeyPressed("P");

    assertEquals("player passes their turn", log.toString());
  }

  @Test
  public void testHandleKeyPressedPassIllegalStateException() {
    Appendable log = new StringBuilder();
    UserPlayer player = new StateExceptionPlayerMock();
    SanguineView view = new PlayGameAndNotifyViewMock(log);


    PlayerActionListener controller = new BasicSanguineController(view, player);

    controller.handleKeyPressed("P");

    assertEquals("test for when player.passTurn() throws a ISE", log.toString());
  }


  // ====== Model Status Tests ====== \\

  @Test
  public void testHandleTeamChange() {
    Appendable log = new StringBuilder();
    SanguineView view = new PlayGameAndNotifyViewMock(log);

    ModelStatusListener controller = new BasicSanguineController(view, new BlankPlayerMock());

    controller.handleTeamChange();

    assertEquals("it's now your turn",  log.toString());
  }

  @Test
  public void testHandleGameOver() {
    Appendable log = new StringBuilder();
    SanguineView view = new PlayGameAndNotifyViewMock(log);

    ModelStatusListener controller = new BasicSanguineController(view, new BlankPlayerMock());

    controller.handleTeamChange();

    assertEquals("it's now your turn",  log.toString());
  }

  @Test
  public void testHandleGameOverTie() {
    Appendable log = new StringBuilder();
    SanguineView view = new PlayGameAndNotifyViewMock(log);

    ModelStatusListener controller = new BasicSanguineController(view, new BlankPlayerMock());

    controller.handleGameOver(null, "");

    assertEquals("winner was null, meaning a tie",  log.toString());
  }

  @Test
  public void testHandleGameOverRedWins() {
    Appendable log = new StringBuilder();
    SanguineView view = new PlayGameAndNotifyViewMock(log);

    ModelStatusListener controller = new BasicSanguineController(view, new BlankPlayerMock());

    controller.handleGameOver(Team.RED, "");

    assertEquals("winner was this team: RED",  log.toString());
  }

  @Test
  public void testHandleGameOverBlueWins() {
    Appendable log = new StringBuilder();
    SanguineView view = new PlayGameAndNotifyViewMock(log);

    ModelStatusListener controller = new BasicSanguineController(view, new BlankPlayerMock());

    controller.handleGameOver(Team.BLUE, "");

    assertEquals("winner was this team: BLUE",  log.toString());
  }
}
