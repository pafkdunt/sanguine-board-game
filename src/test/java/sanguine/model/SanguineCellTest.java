package sanguine.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Testing class to test the cells in a game of Sanguine.
 */
public class SanguineCellTest {

  // also tests getTeam()
  @Test
  public void testConvertTeamRedToBlue() {
    SanguineCell cell = new SanguineCell(1, Team.RED);
    cell.convertTeam(Team.BLUE);

    assertEquals(Team.BLUE, cell.getTeam());
  }

  @Test
  public void testConvertTeamBlueToRed() {
    SanguineCell cell = new SanguineCell(1, Team.BLUE);
    cell.convertTeam(Team.RED);

    assertEquals(Team.RED, cell.getTeam());
  }

  @Test
  public void testConvertTeamEmptyToBlue() {
    SanguineCell cell = new SanguineCell(1, null);
    cell.convertTeam(Team.BLUE);

    assertEquals(Team.BLUE, cell.getTeam());
  }

  @Test
  public void testConvertTeamEmptyToRed() {
    SanguineCell cell = new SanguineCell(1, null);
    cell.convertTeam(Team.RED);

    assertEquals(Team.RED, cell.getTeam());
  }

  // also tests getPawns()
  @Test
  public void testAddPawnNoPawns() {
    SanguineCell cell = new SanguineCell(0, Team.BLUE);
    cell.addPawn();

    assertEquals(1, cell.getPawns());
  }

  @Test
  public void testAddPawnSomePawns() {
    SanguineCell cell = new SanguineCell(1, Team.BLUE);
    cell.addPawn();

    assertEquals(2, cell.getPawns());
  }

  @Test
  public void testAddPawnToGetToMaxPawns() {
    SanguineCell cell = new SanguineCell(2, Team.BLUE);
    cell.addPawn();

    assertEquals(3, cell.getPawns());
  }

  @Test
  public void testAddPawnAtMaxPawns() {
    SanguineCell cell = new SanguineCell(3, Team.BLUE);
    cell.addPawn();

    assertEquals(3, cell.getPawns());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testPlaceCardWrongTeam() {
    SanguineCell cell = new SanguineCell(1, Team.RED);
    SanguineCard card = new SanguineCard("", 1, 1, new boolean[5][5], Team.BLUE);

    cell.placeCard(card);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testPlaceCardInsufficientPawns() {
    SanguineCell cell = new SanguineCell(1, Team.RED);
    SanguineCard card = new SanguineCard("", 3, 1, new boolean[5][5], Team.RED);

    cell.placeCard(card);
  }

  @Test
  public void testPlaceCardSuccessfully() {
    SanguineCell cell = new SanguineCell(1, Team.RED);
    SanguineCard card = new SanguineCard("", 1, 7, new boolean[5][5], Team.RED);

    cell.placeCard(card);
    assertEquals(7, cell.getValue());
  }
}
