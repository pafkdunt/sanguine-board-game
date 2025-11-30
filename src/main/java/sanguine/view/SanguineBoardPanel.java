package sanguine.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import sanguine.model.Cell;
import sanguine.model.ViewModel;

/**
 * Represents the upper half of the frame, showing the board of the game and
 * the associated row scores for each player.
 */
public class SanguineBoardPanel extends JPanel implements MainPanel {
  private final ViewModel model;
  private int previousSelection;

  /**
   * Constructs the drawing of the board panel which has the board and row scores of the game.
   *
   * @param model a read-only model to use the observers available to construct the panel
   * @throws IllegalArgumentException if the model is null
   */
  public SanguineBoardPanel(ViewModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }

    this.model = model;
    this.previousSelection = -1;
    this.setLayout(new GridLayout(model.getRows(), model.getCols() + 2));

    this.setup();
  }

  /**
   * Setup method that is called in the constructor to create the panel.
   */
  private void setup() {
    for (int row = 0; row < model.getRows(); row++) {
      boolean redWinning = false;
      boolean blueWinning = false;

      if (model.getRedScore()[row] > model.getBlueScore()[row]) {
        redWinning = true;
      } else if (model.getBlueScore()[row] > model.getRedScore()[row]) {
        blueWinning = true;
      }

      this.add(new SanguineScorePanel(model.getRedScore()[row],
          redWinning, new Color(200, 120, 120)));

      for (int col = 0; col < model.getCols(); col++) {
        Cell cell = model.getCell(row, col);
        this.add(new SanguineCellPanel(cell));
      }

      this.add(new SanguineScorePanel(model.getBlueScore()[row],
          blueWinning, new Color(90, 150, 210)));
    }
  }

  @Override
  public void subscribe(PlayerActionListener listener) throws IllegalArgumentException {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null.");
    }

    this.highlightCell(listener);
  }

  @Override
  public void refresh() {
    this.removeAll();
    this.repaint();
    this.revalidate();
    this.setup();
  }

  /**
   * Highlights a cell that has been clicked on with the mouse, and
   * calls the listener to handle the cell click.
   *
   * @param listener is the FeaturesListener that calls it's method to handle a cell click
   * @throws IllegalArgumentException if the listener is null
   */
  private void highlightCell(PlayerActionListener listener) throws IllegalArgumentException {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null.");
    }

    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int cols = model.getCols();
        double colWidth = (double) getWidth() / (cols + 2);
        int colIndex = (int) Math.ceil(e.getX() / colWidth);

        if (colIndex < 2 || colIndex > cols + 1) {
          return;
        }

        int rows = model.getRows();
        double rowHeight = (double) getHeight() / rows;
        int rowIndex = (int) Math.ceil(e.getY() / rowHeight);

        listener.handleCellClick(rowIndex - 1, colIndex - 2);

        int panelCols = model.getCols() + 2;
        double colWidthHighlight = (double) getWidth() / panelCols;
        int colIndexHighlight = (int) Math.ceil(e.getX() / colWidthHighlight);
        SanguineBoardPanel.this.highlighting(colIndexHighlight, rowIndex, panelCols);
      }
    });
  }

  /**
   * Highlights the selected cell based on the column and row inputs.
   * Deselects previously highlighted cell if the new selected cell is different.
   * If the previously highlighted cell is the same as the new selected, the highlight is removed.
   *
   * @param colIndexHighlighting is the column index, relative to the GridLayout of the Board
   *                             panel and not the actual board dimensions of the game
   * @param rowIndexHighlighting is the row index, relative to the GridLayout of the Board
   *                             panel and not the actual board dimensions of the game
   * @param panelCols            the number of columns in the panel based on the GridLayout
   */
  private void highlighting(int colIndexHighlighting, int rowIndexHighlighting, int panelCols) {
    int cmpntIndex = panelCols * (rowIndexHighlighting - 1) + colIndexHighlighting - 1;

    if (cmpntIndex % panelCols == 0 || cmpntIndex % panelCols == panelCols - 1) {
      return;
    }

    SanguineCellPanel selectedCell =
        (SanguineCellPanel) SanguineBoardPanel.this.getComponent(cmpntIndex);

    // no previously selected cell
    if (previousSelection == -1) {
      selectedCell.highlight();
      previousSelection = cmpntIndex;
      return;
    }

    // current selected cell is the same as previous selected (remove the highlight)
    if (previousSelection == cmpntIndex) {
      selectedCell.removeHighlight();
      previousSelection = -1;
      return;
    }

    // deselect previous cell and selected new one
    SanguineCellPanel previousCell =
        (SanguineCellPanel) SanguineBoardPanel.this.getComponent(previousSelection);

    previousCell.removeHighlight();
    selectedCell.highlight();
    previousSelection = cmpntIndex;
  }
}
