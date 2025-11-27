package sanguine;

import java.awt.Component;
import javax.swing.SwingUtilities;
import sanguine.Player.BluePlayer;
import sanguine.Player.RedPlayer;
import sanguine.Player.UserPlayer;
import sanguine.ai.FillFirst;
import sanguine.ai.MaximizeRowScore;
import sanguine.controller.BasicSanguineController;
import sanguine.controller.SanguineController;
import sanguine.model.BasicSanguineModel;
import sanguine.model.Team;
import sanguine.model.ViewModel;
import sanguine.view.FeaturesListener;
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
        new BasicSanguineModel(2, 5, 7, file, file, false);


    SanguineView viewRed = new SanguineFrameView(new ViewModel(model), Team.RED);
    SanguineView viewBlue = new SanguineFrameView(new ViewModel(model), Team.BLUE);

    UserPlayer redPlayer = new RedPlayer(model, false);
    UserPlayer bluePlayer = new BluePlayer(model, true);

    SanguineController controllerRed = new BasicSanguineController(viewRed, redPlayer);
    SanguineController controllerBlue = new BasicSanguineController(viewBlue, bluePlayer);

    controllerRed.playGame();
    controllerBlue.playGame();

    redPlayer.subscribe((FeaturesListener) controllerBlue);
    bluePlayer.subscribe((FeaturesListener) controllerRed);
  }
}
