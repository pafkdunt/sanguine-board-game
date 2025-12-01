package sanguine.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import sanguine.model.BasicSanguineModel;
import sanguine.model.SanguineModel;
import sanguine.model.Team;

/**
 * Test class for the player object in the game Sanguine.
 */
public class PlayerTest {
  private UserPlayer redPlayer;
  private UserPlayer bluePlayer;

  /**
   * Setup for tests. Initializes player objects, one red and one blue.
   */
  @Before
  public void setUp() {
    SanguineModel model =
        new BasicSanguineModel(3, 5, 7, "docs/exampleScreenshot.deck",
            "docs/exampleScreenshot.deck", false);

    this.redPlayer = new RedPlayer(model, "human");
    this.bluePlayer = new BluePlayer(model, "human");

    redPlayer.subscribe(new MockModelStatusListener());
    redPlayer.subscribe(new MockModelStatusListener());
    bluePlayer.subscribe(new MockModelStatusListener());
    bluePlayer.subscribe(new MockModelStatusListener());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstruction() {
    UserPlayer testRedPlayer = new RedPlayer(null, "human");
    UserPlayer testBluePlayer = new BluePlayer(null, "human");
  }

  @Test(expected = IllegalStateException.class)
  public void testMoveAfterGameOver() {
    setUp();
    redPlayer.passTurn();
    bluePlayer.passTurn();
    bluePlayer.move(0, 0, 4);
  }

  @Test(expected = IllegalStateException.class)
  public void testPassAfterGameOver() {
    setUp();
    redPlayer.passTurn();
    bluePlayer.passTurn();
    bluePlayer.passTurn();
  }

  @Test
  public void testRedMove() {
    setUp();
    redPlayer.move(0, 0, 0);

    assertTrue(bluePlayer.isItMyTurn());
    assertFalse(redPlayer.isItMyTurn());
  }

  @Test
  public void testBlueMove() {
    setUp();
    redPlayer.passTurn();
    bluePlayer.move(0, 0, 4);

    assertTrue(redPlayer.isItMyTurn());
    assertFalse(bluePlayer.isItMyTurn());
  }

  @Test
  public void testRedPassTurn() {
    setUp();
    redPlayer.passTurn();

    assertTrue(bluePlayer.isItMyTurn());
    assertFalse(redPlayer.isItMyTurn());
  }

  @Test
  public void testBluePassTurn() {
    setUp();
    redPlayer.move(0, 0, 0);
    bluePlayer.passTurn();

    assertTrue(redPlayer.isItMyTurn());
    assertFalse(bluePlayer.isItMyTurn());
  }

  @Test
  public void testGetTeam() {
    setUp();

    assertEquals(Team.RED, redPlayer.getTeam());
    assertEquals(Team.BLUE, bluePlayer.getTeam());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSubscribeWithNull() {
    setUp();

    redPlayer.subscribe(null);
    bluePlayer.subscribe(null);
  }

  // no errors == valid construction
  @Test
  public void testValidSubscribe() {
    setUp();

    redPlayer.subscribe(new MockModelStatusListener());
    bluePlayer.subscribe(new MockModelStatusListener());
  }
}