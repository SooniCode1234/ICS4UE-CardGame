package ics4ue.sooni;

import java.util.Arrays;
import java.util.HashMap;

public class Hand {
  private Card[] cards;

  /**
   * Constructs a hand with 5 cards.
   */
  public Hand() {
    cards = new Card[5];
  }

  /**
   * Removes the card at the specified index.
   *
   * @param index the index of the card to remove
   */
  public void removeCard(int index) {
    cards[index] = null;
  }

  /**
   * The cards holding
   *
   * @return Card[]
   */
  public Card[] getCards() {
    return cards;
  }

  /**
   * Get a list of possible pairs in the player's hand that add to the
   * guessCardValue.
   *
   * @return ArrayList<HashMap<Integer, Integer>>
   */
  public HashMap<Integer, Integer> getPossiblePairs(int guessCardValue) {
    // Got the idea for this from https://www.youtube.com/watch?v=s1xA_K1JReo
    HashMap<Integer, Integer> possiblePairs = new HashMap<Integer, Integer>();

    // Order the cards by value
    Arrays.sort(cards);

    // First and last indecies
    int leftCardIndex = 0;
    int rightCardIndex = cards.length - 1;

    while (leftCardIndex < rightCardIndex) {
      int leftCardValue = cards[leftCardIndex].getValue();
      int rightCardValue = cards[rightCardIndex].getValue();

      // If the left-most card and right-most card add up to the guessCardValue
      if (leftCardValue + rightCardValue == guessCardValue) {
        // Add the pair to the list of possible pairs
        possiblePairs.put(leftCardValue, rightCardValue);

        // Increment the leftCardIndex
        leftCardIndex++;
      } else if (leftCardValue + rightCardValue < guessCardValue) {
        // If the left-most card and right-most card add up to less than the
        // guessCardValue, increment the leftCardIndex
        leftCardIndex++;
      } else {
        // If the left-most card and right-most card add up to more than the
        // guessCardValue, decrement the rightCardIndex
        rightCardIndex--;
      }
    }

    return possiblePairs;
  }

  /**
   * String representation of the hand.
   *
   * @return String.
   */
  public String toString() {
    String result = "";

    // Loop through the cards in the hand.
    for (int i = 0; i < cards.length; i++) {
      Card card = cards[i];

      // If the card is not null, add it to the result.
      if (card != null) {
        result += (i + 1) + ": ";
        result += card;
        result += "\n";
      }
    }

    return result;
  }
}
