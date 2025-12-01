package sanguine;

import sanguine.controller.BasicSanguineController;
import sanguine.controller.SanguineController;
import sanguine.model.BasicSanguineModel;
import sanguine.model.Team;
import sanguine.model.ViewModel;
import sanguine.player.BluePlayer;
import sanguine.player.RedPlayer;
import sanguine.player.UserPlayer;
import sanguine.view.ModelStatusListener;
import sanguine.view.SanguineFrameView;
import sanguine.view.SanguineView;

/**
 * Sanguine class that is the actual game. Cannot be played with what is currently implemented.
 */
public class SanguineGame {

  /**
   * Main method that runs the game.
   *
   * @param args is the arguments taken into to this method (currently takes in the file name only)
   */
  public static void main(String[] args) {
    // String file = "docs/exampleScreenshot.deck";
    BasicSanguineModel model =
        new BasicSanguineModel(Integer.parseInt(args[0]), Integer.parseInt(args[1]), 7,
            args[2], args[3], false);


    SanguineView viewRed = new SanguineFrameView(new ViewModel(model), Team.RED);
    SanguineView viewBlue = new SanguineFrameView(new ViewModel(model), Team.BLUE);

    UserPlayer redPlayer = new RedPlayer(model, args[4]);
    UserPlayer bluePlayer = new BluePlayer(model, args[5]);

    SanguineController controllerRed = new BasicSanguineController(viewRed, redPlayer);
    SanguineController controllerBlue = new BasicSanguineController(viewBlue, bluePlayer);

    controllerRed.playGame();
    controllerBlue.playGame();

    redPlayer.subscribe((ModelStatusListener) controllerBlue);
    redPlayer.subscribe((ModelStatusListener) controllerRed);
    bluePlayer.subscribe((ModelStatusListener) controllerRed);
    bluePlayer.subscribe((ModelStatusListener) controllerBlue);
  }
}
