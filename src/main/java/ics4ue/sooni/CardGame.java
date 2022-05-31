package ics4ue.sooni;

import java.util.Scanner;;

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

      // Regular expression to encompass the card numbers
      String cardIndexValidator = "[1-5]";
      System.out.print("Enter the index of the card you want to place: ");
      String cardIndex = input.nextLine();

      // Validate the cardIndex input
      while (!cardIndex.matches(cardIndexValidator)) {
        System.out
            .print("Your index needs to be from the numbers 1-5. Enter the index of the card you want to place: ");
        cardIndex = input.nextLine();
      }

      // Convert the cardIndex to an integer
      int cardIndexInt = Integer.parseInt(cardIndex);

      // Get the card based on the cardIndex
      Card cardToPlace = playerOne.getCards()[cardIndexInt - 1];

      playerOne.setGuessCardValue(cardToPlace.getValue());

      // Print out data
      System.out.println(playerOne.getGuessCardValue());
    } else {
      System.out.println("PlayerTwo will go first!");
    }
  }
}
