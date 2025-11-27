package sanguine.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class representing a basic model of the game Sanguine.
 */
public class BasicSanguineModel implements SanguineModel {
  private final Board board;
  private final List<Card> redHand;
  private final List<Card> blueHand;
  private final List<Card> redDeck;
  private final List<Card> blueDeck;
  private final int[] redScore;
  private final int[] blueScore;
  private Team currentPlayer;
  private int passes;
  // INVARIANT: passes is that greater than or equal to 0 and less than or equal to 2
  private final int rows;
  private final int cols;

  /**
   * Constructor for the Game object. Takes in the number of rows & columns,
   * and the size of the hand of each player, then initializes the game.
   *
   * @param rows the integer number of rows in the game (board)
   * @param cols the integer number of columns in the game (board)
   * @param handSize the integer number of cards each player can hold
   * @param redFile the file that is used to create the Red player's deck
   * @param blueFile the file that is used to create the Blue player's deck
   * @param redFlip whether we are flipping the Cards so the Red player has the flipped Cards
   *                and the Blue player doesn't (in a default/regular game this would be false)
   * @throws IllegalArgumentException if the hand size is greater than the third of the deck size
   * @throws IllegalArgumentException if deck contains more than two duplicates of any card
   */
  public BasicSanguineModel(int rows, int cols, int handSize, String redFile,
                            String blueFile, boolean redFlip) throws IllegalArgumentException {

    if (rows <= 0 || cols <= 1 || (cols % 2) == 0) {
      throw new IllegalArgumentException("Invalid rows or columns amount.");
    }

    this.rows = rows;
    this.cols = cols;
    this.board = new SanguineBoard(this.rows, this.cols);

    this.redDeck = new ArrayList<>();
    this.blueDeck = new ArrayList<>();

    DeckCreator.createDeck(redDeck, redFlip, redFile);
    DeckCreator.createDeck(blueDeck, !redFlip, blueFile);

    if (this.redDeck.size() < this.rows * this.cols
        || this.blueDeck.size() < this.rows * this.cols) {
      throw new IllegalArgumentException("Given an invalid deck. "
          + "Must be enough cards in each deck to possibly fill every cell on the board");
    }

    if (handSize > (this.redDeck.size() / 3)) {
      throw new IllegalArgumentException("Invalid hand size.");
    }

    this.redHand = new ArrayList<>();
    this.blueHand = new ArrayList<>();

    for (int i = 0; i < handSize; i++) {
      this.redHand.add(this.redDeck.removeFirst());
      this.blueHand.add(this.blueDeck.removeFirst());
    }

    this.redScore = new int[rows];
    this.blueScore = new int[rows];

    this.currentPlayer = Team.RED;
    this.passes = 0;
    this.drawCard();
  }

  @Override
  public void move(int card, int row, int col) throws IllegalStateException {
    if (this.isGameOver()) {
      throw new IllegalStateException("Game is over. Cannot make a move.");
    }

    if (row < 0 || row > this.rows - 1 || col < 0 || col > this.cols - 1) {
      throw new IllegalArgumentException("Invalid row or column. Cannot make this move.");
    }

    // aliases to make code simpler
    List<Card> hand;
    int[] rowScoreList;

    if (currentPlayer == Team.RED) {
      hand = this.redHand;
      rowScoreList = this.redScore;
    } else {
      hand = this.blueHand;
      rowScoreList = this.blueScore;
    }

    if (card < 0 || card > hand.size() - 1) {
      throw new IllegalArgumentException("Invalid card. Must choose a card in the hand.");
    }

    this.board.performMove(hand.get(card), row, col);
    rowScoreList[row] += hand.get(card).value();

    hand.remove(card);

    this.changePlayer();
    this.passes = 0;
  }

  @Override
  public void passTurn() throws IllegalStateException {
    if (this.isGameOver()) {
      throw new IllegalStateException("Game is over. Cannot pass your turn.");
    }

    this.passes++;
    if (!this.isGameOver()) {
      this.changePlayer();
    }
  }

  @Override
  public boolean isGameOver() {
    return passes >= 2;
  }

  @Override
  public Team findWinner() throws IllegalStateException {
    if (!(this.isGameOver())) {
      throw new IllegalStateException("Game is not over. There is no winner yet.");
    }

    int redTotalScore;
    int blueTotalScore;

    redTotalScore = getCurrentScore(Team.RED);
    blueTotalScore = getCurrentScore(Team.BLUE);

    if (redTotalScore > blueTotalScore) {
      return Team.RED;
    } else if (blueTotalScore > redTotalScore) {
      return Team.BLUE;
    } else {
      return null;
    }
  }

  @Override
  public int[] getRedScore() {
    return Arrays.copyOf(this.redScore, this.redScore.length);
  }

  @Override
  public int[] getBlueScore() {
    return Arrays.copyOf(this.blueScore, this.blueScore.length);
  }

  @Override
  public int getRows() {
    return this.rows;
  }

  @Override
  public int getCols() {
    return this.cols;
  }

  @Override
  public Team getCurrentPlayer() {
    return this.currentPlayer;
  }

  @Override
  public Cell getCell(int row, int col) throws IllegalArgumentException {
    return new SanguineCell(board.getCell(row, col).getPawns(),
        board.getCell(row, col).getTeam(), board.getCell(row, col).getValue());
  }

  @Override
  public int getCurrentScore(Team team) {
    int redTotalScore = 0;
    int blueTotalScore = 0;

    for (int row = 0; row < rows; row++) {
      if (this.redScore[row] < this.blueScore[row]) {
        blueTotalScore += this.blueScore[row];
      } else if (this.blueScore[row] < this.redScore[row]) {
        redTotalScore += this.redScore[row];
      }
    }

    if (team.equals(Team.RED)) {
      return redTotalScore;
    } else {
      return blueTotalScore;
    }
  }

  @Override
  public List<Card> getCurrentHand() {
    if (this.currentPlayer == Team.RED) {
      return new ArrayList<>(redHand);
    } else {
      return new ArrayList<>(blueHand);
    }
  }

  /**
   * Passes the turn to the next player. If the game is already over, throws an error.
   *
   * @throws IllegalStateException if the game is over
   */
  private void changePlayer() throws IllegalStateException {
    if (this.isGameOver()) {
      throw new IllegalStateException("Game is over. Cannot change players.");
    }

    if (this.currentPlayer == Team.RED) {
      this.currentPlayer = Team.BLUE;
      this.drawCard();
    } else {
      this.currentPlayer = Team.RED;
      this.drawCard();
    }
  }

  private void drawCard() throws IllegalStateException {
    if (this.isGameOver()) {
      throw new IllegalStateException("Game is over. Cannot draw a card.");
    }

    List<Card> deck;
    List<Card> hand;

    if (currentPlayer == Team.RED) {
      deck = this.redDeck;
      hand = this.redHand;
    } else {
      deck = this.blueDeck;
      hand = this.blueHand;
    }

    if (!deck.isEmpty()) {
      hand.add(deck.removeFirst());
    }
  }
}
