package sanguine;

import sanguine.ai.FillFirst;
import sanguine.ai.MaximizeRowScore;
import sanguine.controller.BasicSanguineController;
import sanguine.controller.SanguineController;
import sanguine.model.BasicSanguineModel;
import sanguine.model.Team;
import sanguine.model.ViewModel;
import sanguine.view.SanguineFrameView;
import sanguine.view.SanguineView;

/**
 * Sanguine class that is the actual game. Cannot be played with what is currently implemented.
 */
public class Sanguine {

  /**
   * Main method that runs the game.
   *
   * @param args is the arguments taken into to this method (currently takes in the file name only)
   */
  public static void main(String[] args) {
    String file = "docs/exampleScreenshot.deck";
    BasicSanguineModel model =
        new BasicSanguineModel(5, 7, 5, file, file, false);
    // FillFirst fillFirst1 = new FillFirst(model, Team.RED);
    // FillFirst fillFirst2 = new FillFirst(model, Team.BLUE);
    MaximizeRowScore max1 = new MaximizeRowScore(model, Team.RED);
    MaximizeRowScore max2 = new MaximizeRowScore(model, Team.BLUE);

    while (true) {
      try {
        // fillFirst1.makeMove();
        // fillFirst2.makeMove();
        max1.makeMove();
        max2.makeMove();
      } catch (IllegalStateException e) {
        break;
      }
    }

    SanguineView view = new SanguineFrameView(new ViewModel(model));
    view.display(true);
    SanguineController controller = new BasicSanguineController(view);
  }
}
