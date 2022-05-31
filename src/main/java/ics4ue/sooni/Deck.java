package ics4ue.sooni;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
  private ArrayList<Card> cards;

  /**
   * Constructor for the Deck class.
   */
  public Deck() {
    cards = new ArrayList<Card>();

    // Hearts
    for (int i = 1; i <= 10; i++) {
      cards.add(new Card(i, "H"));
    }

    // Clubs
    for (int i = 5; i <= 15; i++) {
      cards.add(new Card(i, "C"));
    }

    // Diamonds
    for (int i = 10; i <= 20; i++) {
      cards.add(new Card(i, "D"));
    }
  }

  /**
   * Shuffles the deck.
   */
  public void shuffle() {
    Collections.shuffle(cards);
  }

  /**
   * Deals the cards to the players.
   *
   * @param players the players to deal to
   */
  public void deal(Player[] players) {
    for (int i = 0; i < players.length; i++) {
      Player player = players[i];

      for (int j = 0; j < player.getCards().length; j++) {
        // Get a random index from the deck
        int index = new Random().nextInt(cards.size() - 1);

        // Set the cards in the player's hand to be the card that was removed
        player.getCards()[j] = cards.remove(index);
      }
    }
  }

  /**
   * Gets the cards
   *
   * @return the cards
   */
  public ArrayList<Card> getCards() {
    return cards;
  }
}
