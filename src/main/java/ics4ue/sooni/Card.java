package ics4ue.sooni;

public class Card {
  private int value;
  private String suit;

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
   * Getter for the value of the card
   * 
   * @return int
   */
  public int getValue() {
    return value;
  }

  /**
   * Getter for the suit of the card
   * 
   * @return String
   */
  public String getSuit() {
    return suit;
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
