package ics4ue.sooni;

import java.util.Scanner;
import java.util.HashMap;

public class CardGame {

  // A boolean to determine if the game is over
  public static boolean exitGame = false;

  public static void main(String[] args) {
    // Create a new scanner object
    Scanner input = new Scanner(System.in);
    // Create a deck of cards
    Deck deck = new Deck();

    // Create playerOne and playerTwo
    Player playerOne = new Player();
    Player playerTwo = new Player();

    System.out.println("Welcome to the Card Game!");

    // Shuffle the deck
    System.out.println("Shuffling the deck...");
    deck.shuffle();

    // Deal the cards
    System.out.println("Dealing the cards...");
    deck.deal(new Player[] { playerOne, playerTwo });

    // Choose which player goes first
    System.out.print("Who will go first? playerOne or playerTwo? ");
    String firstPlayer = input.nextLine();

    // Validate the firstPlayer input
    while (!firstPlayer.toLowerCase().equals("playerOne".toLowerCase())
        && !firstPlayer.toLowerCase().equals("playerTwo".toLowerCase())) {
      System.out.print("Who will go first? playerOne or playerTwo? Please explicitly enter playerOne or playerTwo: ");
      firstPlayer = input.nextLine();
    }

    // Properties to keep track of who's turn it is
    Player activePlayer = playerOne;
    Player inactivePlayer = playerTwo;
    String activePlayerName = "PlayerOne";
    String inactivePlayerName = "PlayerTwo";

    // Check if the first player is playerOne
    if (firstPlayer.toLowerCase().equals("playerOne".toLowerCase())) {
      // Set the active player to playerOne
      activePlayer = playerOne;
      inactivePlayer = playerTwo;
      activePlayerName = "PlayerOne";
      inactivePlayerName = "PlayerTwo";
      playerOne.setShowingGuessCard(true);
    } else {
      // Set the active player to playerTwo
      activePlayer = playerTwo;
      inactivePlayer = playerOne;
      activePlayerName = "PlayerTwo";
      inactivePlayerName = "PlayerOne";
      playerOne.setShowingGuessCard(false);
    }

    // Indefinitely loop until the game is over
    while (true) {
      // Check which player goes first by checking if playerOne is showing the guess
      // card
      if (playerOne.getShowingGuessCard()) {
        // Play a round
        playRound(activePlayer, inactivePlayer, activePlayerName, inactivePlayerName, playerOne, playerTwo, deck,
            input);

        // Switch the players
        activePlayer = playerTwo;
        inactivePlayer = playerOne;
        activePlayerName = "PlayerTwo";
        inactivePlayerName = "PlayerOne";
        playerOne.setShowingGuessCard(false);
      } else {
        // Play a round
        playRound(activePlayer, inactivePlayer, activePlayerName, inactivePlayerName, playerOne, playerTwo, deck,
            input);

        // Switch the players
        activePlayer = playerOne;
        inactivePlayer = playerTwo;
        activePlayerName = "PlayerOne";
        inactivePlayerName = "PlayerTwo";
        playerOne.setShowingGuessCard(true);
      }

      // Check if the game is over
      if (exitGame) {
        break;
      }
    }
  }

  /**
   * Checks if the game is over
   * 
   * @param activePlayer
   *                       The player whose turn it is
   * @param inactivePlayer
   *                       The player whose turn it is not
   * @return boolean
   */
  public static boolean gameIsOver(Deck deck, Card cardToPlace, Player activePlayer, Player inactivePlayer) {
    // Card pairs
    HashMap<Integer, Integer> cardPairs = inactivePlayer.getPossiblePairs(cardToPlace.getValue());

    // Check if the deck is empty, if so, the game is over
    // If the deck has 3 or less cards and the inactive player has a match, the game
    // is over
    // If the deck is empty, the game is over
    if (deck.getSize() <= 3 && cardPairs.size() > 0) {
      return true;
    } else if (deck.getSize() == 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Print the winner
   *
   * @param activePlayer       The player whose turn it is
   * @param inactivePlayer     The player whose turn it is not
   * @param deck               The deck
   * @param cardToPlace        The card to place
   * @param activePlayerName   The name of the active Player
   * @param inactivePlayerName The name of the inactive Player
   * @return boolean
   */
  public static boolean printWinner(Player activePlayer, Player inactivePlayer, Deck deck, Card cardToPlace,
      String activePlayerName, String inactivePlayerName) {
    // Check if the game is over
    if (gameIsOver(deck, cardToPlace, activePlayer, inactivePlayer)) {
      System.out.println("The game is over!");

      // Print the winner
      if (activePlayer.getScore() > inactivePlayer.getScore()) {
        System.out.println("The winner is " + activePlayerName + "!");
      } else if (activePlayer.getScore() < inactivePlayer.getScore()) {
        System.out.println("The winner is " + inactivePlayerName + "!");
      } else {
        System.out.println("The game is a tie!");
      }

      // Print the scores
      System.out.println("The scores are: ");
      System.out.println(activePlayerName + ": " + activePlayer.getScore());
      System.out.println(inactivePlayerName + ": " + inactivePlayer.getScore());

      return true;
    }

    return false;
  }

  /*
   * Method to play a round of the game
   *
   * @param activePlayer the player who is currently active
   * 
   * @param inactivePlayer the player who is currently inactive
   * 
   * @param activePlayerName the name of the player who is currently active
   * 
   * @param inactivePlayerName the name of the player who is currently inactive
   * 
   * @param playerOne the player one object
   * 
   * @param playerTwo the player two object
   * 
   * @param deck the deck object
   * 
   * @param input the scanner object
   */
  public static void playRound(Player activePlayer, Player inactivePlayer, String activePlayerName,
      String inactivePlayerName, Player playerOne, Player playerTwo, Deck deck, Scanner input) {
    // Choose the card to place
    activePlayer.setShowingGuessCard(true);
    System.out.println(activePlayerName + ", choose a card to place: ");
    System.out.println("Your cards are:");
    System.out.println(activePlayer.getHandString());

    // Regular expression to encompass the card numbers
    String indexValidator = "[1-5]";
    System.out.print("Enter the index of the card you want to place: ");
    String cardToPlaceIndex = input.nextLine();

    // Validate the cardIndex input
    while (!cardToPlaceIndex.matches(indexValidator)) {
      System.out
          .print("Your index needs to be from the numbers 1-5. Enter the index of the card you want to place: ");
      cardToPlaceIndex = input.nextLine();
    }

    // Convert the cardIndex to an integer
    int cardToPlaceInt = Integer.parseInt(cardToPlaceIndex);

    // Get the index of the card to place
    int cardToPlaceIndexInt = cardToPlaceInt - 1;

    // Get the card based on the cardIndex
    Card cardToPlace = activePlayer.getCards()[cardToPlaceIndexInt];

    activePlayer.setGuessCardValue(cardToPlace.getValue());

    // Get the pair of cards in the inactivePlayer's hand that sum to the value of
    // the
    // cardToPlace
    HashMap<Integer, Integer> cardPairs = inactivePlayer.getPossiblePairs(cardToPlace.getValue());

    // If there are pairs, show them
    if (cardPairs.size() > 0) {
      System.out.println(inactivePlayerName + ", you have the following pairs:");

      // Iterate through the cardPairs
      for (int i = 0; i < cardPairs.size(); i++) {
        // Get the key and value
        int key = (int) cardPairs.keySet().toArray()[i];
        int value = cardPairs.get(key);

        // Print the key and value
        System.out.println((i + 1) + ": " + key + " and " + value);
      }

      // Ask the player to choose a pair
      System.out.print("Which pair do you want to choose? ");
      String pairIndex = input.nextLine();

      // Validate the pairIndex input
      while (!pairIndex.matches(indexValidator)) {
        System.out
            .print("Your index needs to be from the numbers 1-5. Enter the index of the pair you want to choose: ");
        pairIndex = input.nextLine();
      }

      // Convert the pairIndex to an integer
      int pairIndexInt = Integer.parseInt(pairIndex);

      // Get the key and value of the pair
      int key = (int) cardPairs.keySet().toArray()[pairIndexInt - 1];
      int value = cardPairs.get(key);

      // Add a point to the activePlayer
      System.out.println(activePlayerName + " gets a point!");
      activePlayer.addPoint();

      // Check if the game is over
      exitGame = printWinner(activePlayer, inactivePlayer, deck, cardToPlace, activePlayerName, inactivePlayerName);

      // If the game is over, return
      if (exitGame) {
        return;
      }

      // Get the index of the card in inactive player's hand that matches the key
      // and value
      int inactivePlayerFirstCardIndex = 0;
      int inactivePlayerSecondCardIndex = 0;

      // Iterate through the inactivePlayer's hand
      for (int i = 0; i < inactivePlayer.getCards().length; i++) {
        int inactivePlayerCardValue = inactivePlayer.getCards()[i].getValue();

        // Check if the card value matches the key
        if (inactivePlayerCardValue == key) {
          inactivePlayerFirstCardIndex = i;
        } else if (inactivePlayerCardValue == value) {
          inactivePlayerSecondCardIndex = i;
        }
      }

      // Discard inactivePlayer's cards
      inactivePlayer.discard(inactivePlayerFirstCardIndex);
      inactivePlayer.discard(inactivePlayerSecondCardIndex);

      // Discard activePlayer's card
      activePlayer.discard(cardToPlaceIndexInt);

      // Draw a new card for activePlayer
      activePlayer.addCard(cardToPlaceIndexInt, deck);

      // Draw two new card for inactivePlayer
      inactivePlayer.addCard(inactivePlayerFirstCardIndex, deck);
      inactivePlayer.addCard(inactivePlayerSecondCardIndex, deck);
    } else {
      System.out.println("PlayerTwo, you don't have any pairs that sum to " + cardToPlace.getValue());

      // Add a point to the inactivePlayer
      System.out.println(inactivePlayerName + " gets a point!");
      inactivePlayer.addPoint();

      // Check if the game is over
      exitGame = printWinner(activePlayer, inactivePlayer, deck, cardToPlace, activePlayerName, inactivePlayerName);

      // If the game is over, return
      if (exitGame) {
        return;
      }

      // Discard activePlayer's card
      activePlayer.discard(cardToPlaceIndexInt);

      // Draw a new card for activePlayer
      activePlayer.addCard(cardToPlaceIndexInt, deck);
    }

    // Print the number of cards left in the deck
    System.out.println("The deck has " + deck.getCards().size() + " cards left.");

    // Print the number of points each player has
    System.out.println(activePlayerName + " has " + activePlayer.getScore() + " points.");
    System.out.println(inactivePlayerName + " has " + inactivePlayer.getScore() + " points.");
  }
}
