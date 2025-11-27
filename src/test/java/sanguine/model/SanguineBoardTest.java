package sanguine.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Testing class to test the board of the game Sanguime.
 */
public class SanguineBoardTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPerformMoveOutOfBounds() {
    Board board = new SanguineBoard(3, 5);

    boolean[][] influenceGrid = {
        {false, false, false, false, false},
        {false, false, true, false, false},
        {false, false, false, true, false},
        {false, false, false, false, false},
        {false, false, false, false, false}};
    Card card = new SanguineCard("", 1, 1, influenceGrid, Team.RED);

    board.performMove(card, 10, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPerformMoveNotYourTeam() {
    Board board = new SanguineBoard(3, 5);

    boolean[][] influenceGrid = {
        {false, false, false, false, false},
        {false, false, true, false, false},
        {false, false, false, true, false},
        {false, false, false, false, false},
        {false, false, false, false, false}};
    Card card = new SanguineCard("", 1, 1, influenceGrid, Team.BLUE);

    board.performMove(card, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPerformMoveInsufficientCost() {
    Board board = new SanguineBoard(3, 5);

    boolean[][] influenceGrid = {
        {false, false, false, false, false},
        {false, false, true, false, false},
        {false, false, false, true, false},
        {false, false, false, false, false},
        {false, false, false, false, false}};
    Card card = new SanguineCard("", 10, 1, influenceGrid, Team.RED);

    board.performMove(card, 1, 0);
  }

  @Test
  public void testValidPerformMove() {
    Board board = new SanguineBoard(3, 5);

    boolean[][] influenceGrid = {
        {false, false, false, false, false},
        {false, false, true, false, false},
        {false, false, false, true, false},
        {false, false, false, false, false},
        {false, false, false, false, false}};
    Card card = new SanguineCard("", 1, 1, influenceGrid, Team.RED);

    board.performMove(card, 1, 0);
  }

  @Test
  public void testAffectCell() {
    Board board = new SanguineBoard(3, 5);
    Cell cell = board.getCell(0, 0);

    assertEquals(1, cell.getPawns());
    assertEquals(Team.RED, cell.getTeam());
    assertEquals(0, cell.getValue());

    board.affectCell(0, 0, Team.RED);

    assertEquals(2, cell.getPawns());
    assertEquals(Team.RED, cell.getTeam());
    assertEquals(0, cell.getValue());

    board.affectCell(0, 0, Team.BLUE);

    assertEquals(2, cell.getPawns());
    assertEquals(Team.BLUE, cell.getTeam());
    assertEquals(0, cell.getValue());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetCellNegativeRow() {
    Board board = new SanguineBoard(3, 5);
    Cell cell = board.getCell(-1, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetCellExceedRow() {
    Board board = new SanguineBoard(3, 5);
    Cell cell = board.getCell(100, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetCellNegativeCol() {
    Board board = new SanguineBoard(3, 5);
    Cell cell = board.getCell(0, -1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetCellExceedCol() {
    Board board = new SanguineBoard(3, 5);
    Cell cell = board.getCell(0, 100);
  }

  @Test
  public void testGetCell() {
    Board board = new SanguineBoard(3, 5);
    Cell cell = board.getCell(0, 0);

    assertEquals(1, cell.getPawns());
    assertEquals(Team.RED, cell.getTeam());
    assertEquals(0, cell.getValue());

    boolean[][] influenceGrid1 = {
        {false, false, false, false, false},
        {false, false, true, false, false},
        {false, false, false, true, false},
        {false, false, false, false, false},
        {false, false, false, false, false}};
    Card card1 = new SanguineCard("", 1, 1, influenceGrid1, Team.RED);

    board.performMove(card1, 1, 0);

    cell = board.getCell(0, 0);
    assertEquals(2, cell.getPawns());
    assertEquals(Team.RED, cell.getTeam());
    assertEquals(0, cell.getValue());

    cell = board.getCell(1, 0);
    assertEquals(0, cell.getPawns());
    assertEquals(Team.RED, cell.getTeam());
    assertEquals(1, cell.getValue());
  }
}