package sanguine.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Testing class to test cards in a game of Sanguine.
 */
public class SanguineCardTest {

  @Test
  public void testOppositeCard() {
    boolean[][] influenceGrid = {
        {false, false, false, false, false},
        {false, false, true, false, false},
        {false, false, false, true, false},
        {false, false, false, false, false},
        {false, false, false, false, false}};
    SanguineCard redCard = new SanguineCard("", 1, 1, influenceGrid, Team.RED);
    SanguineCard blueCard = redCard.oppositeCard();

    boolean[][] influenceGridFlipped = {
        {false, false, false, false, false},
        {false, false, true, false, false},
        {false, true, false, false, false},
        {false, false, false, false, false},
        {false, false, false, false, false}};
    SanguineCard blueCardCheck = new SanguineCard("", 1, 1, influenceGridFlipped, Team.BLUE);

    assertEquals(blueCardCheck, blueCard);
  }

  @Test
  public void testOppositeCardAllFalse() {
    boolean[][] influenceGrid = {
        {false, false, false, false, false},
        {false, false, false, false, false},
        {false, false, false, false, false},
        {false, false, false, false, false},
        {false, false, false, false, false}};
    SanguineCard redCard = new SanguineCard("", 1, 1, influenceGrid, Team.RED);
    SanguineCard blueCard = redCard.oppositeCard();

    boolean[][] influenceGridFlipped = {
        {false, false, false, false, false},
        {false, false, false, false, false},
        {false, false, false, false, false},
        {false, false, false, false, false},
        {false, false, false, false, false}};
    SanguineCard blueCardCheck = new SanguineCard("", 1, 1, influenceGridFlipped, Team.BLUE);

    assertEquals(blueCardCheck, blueCard);
  }

  @Test
  public void testOppositeCardAllTrue() {
    boolean[][] influenceGrid = {
        {true, true, true, true, true},
        {true, true, true, true, true},
        {true, true, true, true, true},
        {true, true, true, true, true},
        {true, true, true, true, true}};

    SanguineCard redCard = new SanguineCard("", 1, 1, influenceGrid, Team.RED);
    SanguineCard blueCard = redCard.oppositeCard();

    boolean[][] influenceGridFlipped = {
        {true, true, true, true, true},
        {true, true, true, true, true},
        {true, true, true, true, true},
        {true, true, true, true, true},
        {true, true, true, true, true}};
    SanguineCard blueCardCheck = new SanguineCard("", 1, 1, influenceGridFlipped, Team.BLUE);

    assertEquals(blueCardCheck, blueCard);
  }

  @Test
  public void testOppositeCardComplexInfluenceGrid() {
    boolean[][] influenceGrid = {
        {false, true, false, false, false},
        {false, false, true, false, true},
        {false, false, false, true, false},
        {false, false, false, true, false},
        {true, false, true, false, false}};
    SanguineCard redCard = new SanguineCard("", 1, 1, influenceGrid, Team.RED);
    SanguineCard blueCard = redCard.oppositeCard();

    boolean[][] influenceGridFlipped = {
        {false, false, false, true, false},
        {true, false, true, false, false},
        {false, true, false, false, false},
        {false, true, false, false, false},
        {false, false, true, false, true}};
    SanguineCard blueCardCheck = new SanguineCard("", 1, 1, influenceGridFlipped, Team.BLUE);

    assertEquals(blueCardCheck, blueCard);
  }
}
