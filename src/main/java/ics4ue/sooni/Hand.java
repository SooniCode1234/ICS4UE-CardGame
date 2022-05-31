package ics4ue.sooni;

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
