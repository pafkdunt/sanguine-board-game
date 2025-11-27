package sanguine.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Class the creates decks for the main Game.
 */
public class DeckCreator {

  /**
   * Creates a deck of cards given an empty list of cards, whether the
   * cards' girds should be flipped or not (as inputted cards are by default set up
   * for the red player), and the file containing the cards in a text format.
   *
   * @param deck the deck that is being created
   * @param flipGrid whether the cards need their influence grids flipped
   * @param file is the file containing the cards in a text format
   * @throws IllegalArgumentException file is a null String
   */
  public static void createDeck(List<Card> deck, boolean flipGrid, String file)
      throws IllegalArgumentException {
    if (file == null) {
      throw new IllegalArgumentException("file cannot be null");
    }

    file = formatFile(file);
    String name;
    int cost;
    int value;
    boolean[][] grid = new boolean[5][5];

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;

      while ((line = reader.readLine()) != null) {
        name = line.split("\\s+")[0];
        cost = Integer.parseInt(line.split("\\s+")[1]);
        value = Integer.parseInt(line.split("\\s+")[2]);

        for (int i = 0; i < 5; i++) {
          line = reader.readLine();
          char[] gridRow = line.toCharArray();
          for (int j = 0; j < 5; j++) {
            grid[i][j] = (gridRow[j] == 'I');
          }
        }

        Card card = new SanguineCard(name, cost, value, grid, Team.RED);
        if (flipGrid) {
          deck.add(card.oppositeCard());
        } else {
          deck.add(card);
        }

        grid = new boolean[5][5];
      }

    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
    }

    validateDeckDuplicates(deck);
  }

  /**
   * Formats a give string that is a file name so that it
   * can be interpreted by any OS.
   *
   * @param file is the file path
   * @return the file path as String formatted to be readable for any OS
   */
  private static String formatFile(String file) {
    StringBuilder retFile = new StringBuilder();

    for (char c : file.toCharArray()) {
      if (c == '\\' || c == '/') {
        retFile.append(File.separator);
      }

      retFile.append(c);
    }

    return retFile.toString();
  }

  /**
   * Checks if the given deck is a valid deck, meaning
   * it does not contain more than two duplicates of a card.
   *
   * @param deck is the deck we are checking
   * @throws IllegalStateException if more than two duplicates of a card were found
   */
  private static void validateDeckDuplicates(List<Card> deck) throws IllegalArgumentException {
    int count = 0;
    for (Card card : deck) {
      for (Card compare : deck) {
        if (card.equals(compare)) {
          count++;
        }
      }

      if (count > 2) {
        throw new IllegalArgumentException("More than two duplicates of a card were found.");
      }

      count = 0;
    }
  }

}
