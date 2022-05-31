package ics4ue.sooni;

import java.util.ArrayList;
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
  public ArrayList<HashMap<Integer, Integer>> getPossiblePairs(int guessCardValue) {
    ArrayList<HashMap<Integer, Integer>> possiblePairs = new ArrayList<>();

    // Order the cards by value
    Arrays.sort(cards);

    System.out.println("Sorted cards: " + Arrays.toString(cards));

    // Put all the card value in a array
    int[] nums = new int[5];

    for (int i = 0; i < 5; i++) {
      nums[i] = cards[i].getValue();
    }

    // maintain two indices pointing to endpoints of the array
    int low = 0;
    int high = nums.length - 1;

    // reduce the search space `nums[low…high]` at each iteration of the loop

    // loop till the search space is exhausted
    while (low < high) {
      // sum found
      if (nums[low] + nums[high] == guessCardValue) {
        System.out.println("Pair found (" + nums[low] + "," +
            nums[high] + ")");
        break;
      }

      // increment `low` index if the total is less than the desired sum;
      // decrement `high` index if the total is more than the desired sum
      if (nums[low] + nums[high] < guessCardValue) {
        low++;
      } else {
        high--;
      }
    }

    // Print the list of possible pairs
    System.out.println("Possible pairs: " + possiblePairs.size());

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
