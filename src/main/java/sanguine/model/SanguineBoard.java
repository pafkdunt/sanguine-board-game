package sanguine.model;


/**
 * Representation of the game board.
 */
public class SanguineBoard implements Board {
  private final Cell[][] board;
  private final int rows;
  private final int cols;

  /**
   * Constructor for a Board object. Takes in the number of rows and columns for the board.
   *
   * @param rows the integer number of rows
   * @param cols the integer number of columns
   */
  public SanguineBoard(int rows, int cols) {
    this.board = new SanguineCell[rows][cols];
    this.rows = rows;
    this.cols = cols;


    // initializes board fields to empty cells
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        board[row][col] = new SanguineCell(0, null);
      }
    }


    // adds 1 pawn to the left column and right colum of the board
    // left side is RED, right side is BLUE
    for (int row = 0; row < rows; row++) {
      board[row][0].addPawn();
      board[row][0].convertTeam(Team.RED);
      board[row][cols - 1].addPawn();
      board[row][cols - 1].convertTeam(Team.BLUE);
    }
  }


  @Override
  public void performMove(Card card, int row, int col) throws IllegalArgumentException {
    if (card == null) {
      throw new IllegalArgumentException("Card cannot be null.");
    }

    if (row < 0 || row >= rows || col < 0 || col >= cols) {
      throw new IllegalArgumentException("Out of bounds. Choose a valid cell to place a card.");
    }

    // places the actual card
    board[row][col].placeCard(card);

    // affects the cells based on influence gird of the card
    for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
      for (int colIndex = 0; colIndex < 5; colIndex++) {
        if (card.grid()[rowIndex][colIndex]) {
          affectCell(row + (rowIndex - 2), col + (colIndex - 2), card.team());
        }
      }
    }
  }


  @Override
  public void affectCell(int row, int col, Team affectingTeam) throws IllegalArgumentException {
    if (affectingTeam == null) {
      throw new IllegalArgumentException("The affecting team cannot be null.");
    }

    if (row < 0 || row >= rows || col < 0 || col >= cols) {
      return;
    }

    Cell cell = board[row][col];

    // if cell not a card, exit
    if (cell.getValue() > 0) {
      return;
    }

    // same team so add pawn
    if (cell.getTeam() == affectingTeam) {
      cell.addPawn();
    } else if (cell.getTeam() == null) { // empty/null cell so make cell
      // part of the affectingTeam and add a pawn
      cell.convertTeam(affectingTeam);
      cell.addPawn();
    } else { // different team so make cell part of the affectingTeam
      cell.convertTeam(affectingTeam);
    }
  }

  @Override
  public Cell getCell(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row > this.rows - 1 || col < 0 || col > this.cols - 1) {
      throw new IllegalArgumentException("Given an invalid row or column. Cannot get this cell.");
    }

    return this.board[row][col];
  }
}