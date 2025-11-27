package sanguine.ai;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import sanguine.ai.mocks.ArbitraryValidMoveModel;
import sanguine.ai.mocks.FillInModel;
import sanguine.ai.mocks.FirstMoveValidModel;
import sanguine.ai.mocks.NoValidMoveModel;
import sanguine.ai.mocks.TopToBottomLoggingModel;
import sanguine.model.SanguineModel;
import sanguine.model.Team;

/**
 * Testing class for the FillFirst AI strategy of playing Sanguine.
 */
public class FillFirstTest {

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorNullModel() {
    StrategicComputerPlayers fillFirst = new FillFirst(null, Team.RED);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorNullTeam() {
    SanguineModel model = new FillInModel();
    StrategicComputerPlayers fillFirst = new FillFirst(model, null);
  }

  // FillFirst offers no observers to check construction so if no error is
  // thrown it is a valid construction.
  @Test
  public void testConstructorValidConstruction() {
    SanguineModel model = new FillInModel();
    StrategicComputerPlayers fillFirst = new FillFirst(model, Team.RED);
  }

  // tests specific case where the last card is the best option and logs every move
  @Test
  public void testLooksTopToBottomAndLogs() {
    String file = "docs/example.deck";
    Appendable log = new StringBuilder();
    SanguineModel model = new TopToBottomLoggingModel(file, log);
    StrategicComputerPlayers fillFirst = new FillFirst(model, Team.RED);
    fillFirst.makeMove();

    assertEquals("Card Index: 14\nRow: 2\nColumn: 4\nMove was VALID\n", log.toString());
  }

  // tests specific case where the first card is the best option
  @Test
  public void testFirstAvailableMoveIsValid() {
    Appendable log = new StringBuilder();
    SanguineModel model = new FirstMoveValidModel(log);
    StrategicComputerPlayers fillFirst = new FillFirst(model, Team.RED);

    fillFirst.makeMove();

    assertEquals("Card Index: 0\nRow: 0\nColumn: 0\n",  log.toString());
  }

  @Test
  public void testNoAvailableMovesPassesTurn() {
    Appendable log = new StringBuilder();
    SanguineModel model = new NoValidMoveModel(log);
    StrategicComputerPlayers fillFirst = new FillFirst(model, Team.RED);

    fillFirst.makeMove();

    assertEquals("Passed turn",  log.toString());
  }

  //
  @Test
  public void testArbitraryMoveIsValid() {
    Appendable log = new StringBuilder();
    SanguineModel model = new ArbitraryValidMoveModel(log);
    StrategicComputerPlayers fillFirst = new FillFirst(model, Team.RED);

    fillFirst.makeMove();

    assertEquals("Card Index: 1\nRow: 1\nColumn: 3\n",   log.toString());
  }
}
