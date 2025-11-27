package sanguine.ai;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import sanguine.ai.mocks.ArbitraryMoveWinsRowModel;
import sanguine.ai.mocks.FillInModel;
import sanguine.ai.mocks.FirstMoveWinsRowModel;
import sanguine.ai.mocks.NoMovesThatWinRowsModel;
import sanguine.ai.mocks.TopToBottomLoggingRowsModel;
import sanguine.model.SanguineModel;
import sanguine.model.Team;

/**
 * Testing class for the MaximizeRowScore AI strategy of playing Sanguine.
 */
public class MaximizeRowTest {

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorNullModel() {
    StrategicComputerPlayers maxRow = new MaximizeRowScore(null, Team.RED);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorNullTeam() {
    SanguineModel model = new FillInModel();
    StrategicComputerPlayers maxRow = new MaximizeRowScore(model, null);
  }

  // FillFirst offers no observers to check construction so if no error is
  // thrown it is a valid construction.
  @Test
  public void testConstructorValidConstruction() {
    SanguineModel model = new FillInModel();
    StrategicComputerPlayers maxRow = new MaximizeRowScore(model, Team.RED);
  }

  @Test
  public void testNoValidMovesSoPassesTurn() {
    Appendable log = new StringBuilder();
    SanguineModel model = new NoMovesThatWinRowsModel(log);
    StrategicComputerPlayers maxRow = new MaximizeRowScore(model, Team.RED);

    maxRow.makeMove();

    assertEquals("Passed turn", log.toString());
  }

  @Test
  public void testFirstMoveIsValid() {
    Appendable log = new StringBuilder();
    SanguineModel model = new FirstMoveWinsRowModel(log);
    StrategicComputerPlayers maxRow = new MaximizeRowScore(model, Team.RED);

    maxRow.makeMove();

    assertEquals("Card Index: 0\nRow: 0\nColumn: 0\n", log.toString());
  }

  @Test
  public void testArbitraryMoveIsValid() {
    Appendable log = new StringBuilder();
    SanguineModel model = new ArbitraryMoveWinsRowModel(log);
    StrategicComputerPlayers maxRow = new MaximizeRowScore(model, Team.RED);

    maxRow.makeMove();

    assertEquals("Card Index: 0\nRow: 1\nColumn: 0\n",   log.toString());
  }

  @Test
  public void testLooksTopToBottomAndLogs() {
    String file = "docs/example.deck";
    Appendable log = new StringBuilder();
    SanguineModel model = new TopToBottomLoggingRowsModel(file, log);
    StrategicComputerPlayers maxRow = new MaximizeRowScore(model, Team.RED);

    maxRow.makeMove();

    assertEquals("Card Index: 14\nRow: 2\nColumn: 4\nMove was VALID\n", log.toString());
  }
}
