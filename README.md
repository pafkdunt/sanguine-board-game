# Overview:

### General Purpose
This codebase creates the model for the game Sanguine, which is a variation of the card game Queen's Blood. This is a two player game, where there is Red and Blue player trying to score as many points as possible. The game is played by placing cards on a board, filling that boards' cells up with cards and pawns of the two teams. These components of the game come together to create the entire game.

### Extensibility
The code base is relatively extensible. All basic functionality can be overridden and changed, but it is good to be mindful of what changes are made. A key point of focus we would like look at is the `move()` method in BasicSanguineModel. This method calls a method of the SanguineBoard class called `performMove()` and so when extending this  code and modifying things it is good to keep in mind things like these. Similarly, SanguineBoard uses methods from the SanguineCell class. Overall the components of the game are extensible, but require caution. The game model itself is quite extensible.

## Disclaimer
This repository contains my personal work from a Northeastern University course. It is published for portfolio and learning purposes only.

# Quick start

### A sample Test:
```aiignore
@Test
public void testBlueMoves() {
  String file = "docs/example.deck";
  SanguineModel model = new BasicSanguineModel(3, 5, 1,
      file, file, false);

  UserPlayer redPlayer = new RedPlayer(model, false);
  UserPlayer bluePlayer = new BluePlayer(model, false);

  redPlayer.passTurn();
  bluePlayer.move(0, 0, 4);
  
  assertEquals(1, model.getBlueScore()[0]);
  
  redPlayer.move(0, 0, 0);
  
  assertEquals(1, model.getRedScore()[0]);
  
  bluePlayer.passTurn();
  redPlayer.passTurn();
  bluePlayer.passTurn();

  assertTrue(model.isGameOver());
}
```

This test shows how one would create a game model of Sanguine and how they would play the game with two player (Red and Blue). This is an example of a game being played out to completion.

# Key components

### 1. SanguineModel
This component is a driver in our codebase performing actions, changing data, and representing our overall game. It makes moves, passes turns, checks when the game is over, and calculates who won. All these things are the data that actually represent our game.  

### 2. SanguineView
This component is driven in our code base and visualizes the actual game through textual output. It is given information/data from the model that it uses to show what the game looks like. It is currently reactionary, based on actions performed by players and the model.

Here you can see it displaying the data of row scores, as well as the board which has cells containing cards and pawns.
```aiignore
1 R1__1 0
0 2___2 0
0 1__1B 1
```

# Key subcomponents

### 1. Cells
Cells represent each individual component on the board of the game Sanguine. They can either be empty, have pawns on them (pawns belong to one team or the other), or have a card on them (therefore having no pawns). 
You can add pawns, with a max of 3 pawns, or place a card given that there are enough pawns to pay for the cost of that card. The last important functionality of this class is if it has pawns, the cell and those pawns can be converted to the opposite team.

#### Pawns
The purpose of pawns on a cell is to allow for the player to place cards. Each card has an associated cost and requires that amount of pawns (or more) to be able to played on that cell. If there are not enough pawns for the card then the player cannot play that card on that cell.

### 2. Cards
Cards represent the playing cards of Sanguine. They have a name, cost to play, value they earn (to the score), and an associated influence grid showing how they affect the board of the game. Their purpose is for the player to strategically use them to make moves to earn higher scores and win the game.

#### 2 Influence Grids 
The influence grid affects the cells around where the card is placed (if it is can be placed).

Here are the different cases for an influence grid:
1. If the grid influences an empty cell, a pawn is added for the player that played that card.
2. If the grid influences a cell with pawns of the player who played card, a new pawn is added (cannot exceed 3 pawns on a cell).
3. If the grid influences a cell with pawns of the other player, then those pawns are converted to the team of the player that placed the card. No new pawns are added.
4. If there is a card on that cell, and the grid influences that cell, nothing will happen regardless of who's card is on that cell trying to be influenced. 

### 3. Board
The board is the actual place where the action of the game occurs. The board is made up of cells with a specified amount of rows and columns (columns must be odd and greater than 1). It is where the players more directly interact with the game and where the actual game takes place.

If you look at the example of the textual view above, this section of it is what represents the board:
```aiignore
R1__1
2___2
1__1B
```

# Source organization

All implementation is in `src/main/java/sanguine`

### Key Components

1. SanguineModel - this is in the model package
2. SanguineView - this is in the view package

### Key Subcomponents

1. Cells - this is in the model package
2. Cards - this is in the model package
3. Board - this is in the model package



# Changes for Part 2:

- Removed `getRedDeck()` and `getBlueDeck()`, as they were unnecessary observers
- Modified `move()` and `passTurn()` so they no longer take `Team` as an input, but instead use the `currentPlayer` field of the object since we used to have a `Player` object with its own associated team. All `Player` implementation was removed from feedback in class for the model
- Added `getCurrentScore(Team team)` to determine the current score of the input team, called in `findWinner()` to simplify code
- Added interface `ReadonlySanguineModel`, made `SanguineModel` extend it. The purpose of this was to separate observers and operations of the model
- Added class `ViewModel` that creates a model for the view with only access to observable methods and cannot be type cast to a `BasicSanguineModel` for mutations
- Made it so that a card is drawn at the start of the turn, regardless of whether they make a move or pass. This was missed in the last assignment
- Added `getCurrentHand()` that returns that hand of the current player, to replace `getRedHand()` and `getBlueHand()` for simplicity

# View

### Keyboard Input

- The user must press `ENTER` to make a move
- The user must press `P` to pass their turn

### Frame

`SanguineFrameView` implements interface `SanguineView` which has the ability to display the view and subscribe features to the view

### Panels

- `MainPanel` interface for larger panels
  - `SanguineBoardPanel` draws the board of the game as a grid and the scores associated with it
  - `SanguineCardSectionPanel` draws the current player's hand
- `SubPanel` interface for panels inside main panels
  - `SanguineCellPanel` draws the individual cell of the game that can either be empty, have pawns, or have a card
  - `SanguineCardPanel` draws the individual card of the current player's hand that has a name, cost, value, and influence grid
- Extraneous Panels (without interfaces)
  - `SanguineScorePanel` draws the score of the row on the grid. The score is dependent on its position relative to the grid
  - `SanguineInfluenceGridPanel` draws the influence grid for each card panel

### FeaturesListener

`FeaturesListener` handles user input from the view so that the `FeaturesListener` can do something with it (passed to the controller in our case, which prints out the input relative to the model)

# Strategic Computer Player (AI)

Interface `StrategicComputerPlayers`, which is implemented by classes described below, only has one method: `makeMove()`

### Fill First
Class that implements the interface `StrategicComputerPlayers`, based on the strategy to choose the first card and location that can be played on and play there

### Maximize Row-Score
Class that implements the interface `StrategicComputerPlayers`, based on the strategy to choose a card and location that will allow the current player to win a row, starting from the top. If no moves win a row, turn is passed


# Changes for Part 3:

### Pop-up Windows
When certain messages need to be displayed like, when it turns have changed and a player needs to know it's their turn, or if a player makes an illegal move, a small pop-up will show with a message relaying that bit of information so that they can understand what it is that happened and what their options are.

Specific cases where these pop-ups occur: 
- it's the players turn now
- the game is over (shows who won and their score)
- they have made an improper move (cell selected was not theirs or did not have enough pawns)

One part to note about our pop-ups is that if you have both views on the same screen the pop-ups will show up above both of them. This occurs as we wanted to get around the fact that a pop-up prevents anything else from occurring until it has been dismissed by either closing the window or hitting 'OK'. This is something that we found acceptable as we visualized this game occurring on two screens, kind of like the online very of Queen's Blood. In that scenario the pop-ups for one player would have no affect on the other, and so we treated them this way.

### Game Over
When the game is over two pop-up windows show up, one for each view telling both players what the result of the game is (who won and that players score if there was not a tie). Both players are then stopped from making any more moves.

### Players
The player class represents a player of the game Sanguine. This player can be a human or a strategic computer player and has the primary abilities to make a move or pass their turn. They can also ask whether it is their turn yet, see what team they are on, and subscribe to listeners to publish notifications when something relating to a change in model status occurs. 

### The Two Listener Types
There are two listener types a `PlayerActionListener` and a `ModelStatusListener`. The player action listener methods are called in the view and related to actual actions the player takes interacting with the view, and sends those to the listener (controller) to use the input from those actions towards playing the game. The model status listener methods are called by the player class and related to the status of the model and how that affects how the game looks (primarily relating to showing the relevant pop-up windows).

The player action listener can be viewed as the view communicating to the model. The model status listener can be viewed as the model/player communicating to the view through the controller.

The controller implements both of these interface types and is the sole listener of the game Sanguine.
