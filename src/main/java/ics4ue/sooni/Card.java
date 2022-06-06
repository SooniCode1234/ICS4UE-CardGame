package ics4ue.sooni;

public class Card implements Comparable<Card> {
  private final int value;
  private final String suit;

  /**
   * Constructor for Card
   * 
   * @param value the value of the card
   * @param suit  the suit of the card
   */
  public Card(int value, String suit) {
    // Initialize the value and suit of the card
    this.value = value;
    this.suit = suit;
  }

  /**
   * Order the cards by their value ascending order
   *
   * @param other the card to compare to
   * @return int
   */
  public int compareTo(Card other) {
    return this.value - other.value;
  }

  /**
   * Getter for the value of the card
   * 
   * @return int
   */
  public int getValue() {
    return value;
  }

  /**
   * String representation of the card
   * 
   * @return String
   */
  public String toString() {
    return value + " of " + suit;
  }
}
