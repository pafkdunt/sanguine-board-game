package sanguine.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Testing class to test a basic Sanguine model.
 */
public class BasicSanguineModelTest {

  @Test (expected = IllegalArgumentException.class)
  public void testHandSizeTooLarge() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 100,
        file, file, false);
  }

  @Test
  public void testValidConstruction() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    assertEquals(2, model.getCurrentHand().size());
    assertEquals(Team.RED, model.getCurrentPlayer());
  }

  @Test (expected = IllegalStateException.class)
  public void testMoveGameIsOver() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.passTurn();
    model.passTurn();

    model.move(0, 0, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveNegativeRow() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.move(0, -1, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveTooLargeRow() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.move(0, 3, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveNegativeCol() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.move(0, 0, -1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveTooLargeCol() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.move(0, 0, 5);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveNegativeCard() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.move(-1, 0, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveTooLargeCard() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.move(2, 0, 0);
  }

  @Test
  public void testRedMoves() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.move(0, 0, 0);

    assertEquals(1, model.getRedScore()[0]);
  }

  @Test
  public void testBlueMoves() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.passTurn();
    model.move(0, 0, 4);

    assertEquals(1, model.getBlueScore()[0]);
  }

  @Test (expected = IllegalStateException.class)
  public void testPassTurnGameIsOver() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.passTurn();
    model.passTurn();
    model.passTurn();
  }

  @Test
  public void testRedPassTurn() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.passTurn();
    assertEquals(0, model.getRedScore()[0]);
  }

  @Test
  public void testBluePassTurn() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.move(0, 0, 0);
    model.passTurn();
    assertEquals(1, model.getRedScore()[0]);
    assertEquals(0, model.getBlueScore()[0]);
  }

  // tests isGameOver() and passTurn().
  // Tests the case where passTurn() leads to a game over.
  @Test
  public void testPassTurnLeadsToGameOverAndIsGameOver() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.passTurn();
    model.passTurn();
    assertTrue(model.isGameOver());
  }

  @Test (expected = IllegalStateException.class)
  public void testFindWinnerGameIsNotOver() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.findWinner();
  }

  @Test
  public void testFindWinnerGameIsOverTie() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.passTurn();
    model.passTurn();

    assertNull(model.findWinner());
  }

  @Test
  public void testFindWinnerGameIsOverRedWins() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.move(0, 0, 0);
    model.passTurn();
    model.passTurn();

    assertEquals(Team.RED, model.findWinner());
  }

  @Test
  public void testFindWinnerGameIsOverBlueWins() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.passTurn();
    model.move(0, 0, 4);
    model.passTurn();
    model.passTurn();

    assertEquals(Team.BLUE, model.findWinner());
  }

  @Test
  public void testGetCurrentHandRedPlaying() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    assertEquals(2, model.getCurrentHand().size());
  }

  @Test
  public void testGetCurrentHandBluePlaying() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.passTurn();
    assertEquals(2, model.getCurrentHand().size());
  }

  @Test
  public void testGetRedScore() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.move(0, 0, 0);
    assertEquals(3, model.getRedScore().length);
    assertEquals(1, model.getRedScore()[0]);
    for (int i = 1; i < model.getRows(); i++) {
      assertEquals(0, model.getRedScore()[i]);
    }
  }

  @Test
  public void testGetBlueScore() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.passTurn();
    model.move(0, 0, 4);
    assertEquals(3, model.getBlueScore().length);
    assertEquals(1, model.getBlueScore()[0]);
    for (int i = 1; i < model.getRows(); i++) {
      assertEquals(0, model.getBlueScore()[i]);
    }
  }

  @Test
  public void testGetRows() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    assertEquals(3, model.getRows());
  }

  @Test
  public void testGetCols() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    assertEquals(5, model.getCols());
  }

  @Test
  public void testGetCurrentPlayerGameIsOver() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.passTurn();
    model.passTurn();

    assertEquals(Team.BLUE, model.getCurrentPlayer());
  }

  @Test
  public void testGetCurrentPlayer() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    assertEquals(Team.RED, model.getCurrentPlayer());
    model.passTurn();
    assertEquals(Team.BLUE, model.getCurrentPlayer());
  }

  @Test
  public void testGetCell() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    assertEquals("1", model.getCell(0, 0).toString());
    model.move(0, 0, 0);
    assertEquals("R", model.getCell(0, 0).toString());
  }

  @Test
  public void testGetCurrentScoreNoMoves() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    assertEquals(0, model.getCurrentScore(Team.RED));
    assertEquals(0, model.getCurrentScore(Team.BLUE));
  }

  @Test
  public void testGetCurrentScoreRedHasPoints() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.move(0, 0, 0);
    assertEquals(1, model.getCurrentScore(Team.RED));
  }

  @Test
  public void testGetCurrentScoreBlueHasPoints() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.passTurn();
    model.move(0, 0, 4);
    assertEquals(1, model.getCurrentScore(Team.BLUE));
  }

  @Test
  public void testGetCurrentScoreMovesLeadToNoPoints() {
    String file = "docs/example.deck";
    SanguineModel model = new BasicSanguineModel(3, 5, 1,
        file, file, false);

    model.move(0, 0, 0);
    model.move(0, 0, 4);
    assertEquals(0, model.getCurrentScore(Team.RED));
    assertEquals(0, model.getCurrentScore(Team.BLUE));
  }
}
