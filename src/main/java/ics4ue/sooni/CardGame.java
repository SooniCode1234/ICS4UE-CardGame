package ics4ue.sooni;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class CardGame {
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

    // Check if playerOne or playerTwo goes first
    if (firstPlayer.toLowerCase().equals("playerOne".toLowerCase())) {
      System.out.println("PlayerOne will go first!");

      // Choose the card to place
      playerOne.setShowingGuessCard(true);
      System.out.println("PlayerOne, choose a card to place: ");
      System.out.println("Your cards are:");
      System.out.println(playerOne.getHandString());

      // Test printing out playertwo's hand
      System.out.println("PlayerTwo's cards are:");
      System.out.println(playerTwo.getHandString());

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
      Card cardToPlace = playerOne.getCards()[cardToPlaceIndexInt];

      playerOne.setGuessCardValue(cardToPlace.getValue());

      // Get the pair of cards in playerTwo's hand that sum to the value of the
      // cardToPlace
      HashMap<Integer, Integer> cardPairs = playerTwo.getPossiblePairs(cardToPlace.getValue());

      // If there are pairs, show them
      if (cardPairs.size() > 0) {
        System.out.println("PlayerTwo, you have the following pairs:");

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

        // Add a point to playerOne
        System.out.println("PlayerOne gets a point!");
        playerOne.addPoint();

        // Get the index of the card in playerTwo's hand that matches the key
        // and value
        int playerTwoFirstCardIndex = 0;
        int playerTwoSecondCardIndex = 0;

        for (int i = 0; i < playerTwo.getCards().length; i++) {
          int playerTwoCardValue = playerTwo.getCards()[i].getValue();

          // Check if the card value matches the key
          if (playerTwoCardValue == key) {
            playerTwoFirstCardIndex = i;
          } else if (playerTwoCardValue == value) {
            // Check if the card value matches the value
            playerTwoSecondCardIndex = i;
          }
        }

        // Discard playerTwo's cards
        playerTwo.discard(playerTwoFirstCardIndex);
        playerTwo.discard(playerTwoSecondCardIndex);

        // Discard playerOne's card
        playerOne.discard(cardToPlaceIndexInt);

        // Print out the playerOne's hand and playerTwo's hand for testing
        System.out.println("PlayerOne's cards are:");
        System.out.println(playerOne.getHandString());

        System.out.println("PlayerTwo's cards are:");
        System.out.println(playerTwo.getHandString());
      } else {
        System.out.println("PlayerTwo, you don't have any pairs that sum to " + cardToPlace.getValue());

        // Add a point to playerTwo
        System.out.println("PlayerTwo gets a point!");
        playerTwo.addPoint();

        // Discard playerOne's card
        playerOne.discard(cardToPlaceIndexInt);

        // Print out the playerOne's hand and playerTwo's hand for testing
        System.out.println("PlayerOne's cards are:");
        System.out.println(playerOne.getHandString());

        System.out.println("PlayerTwo's cards are:");
        System.out.println(playerTwo.getHandString());
      }
    } else {
      System.out.println("PlayerTwo will go first!");
    }
  }
}
