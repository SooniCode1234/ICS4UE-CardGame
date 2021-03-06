package ics4ue.sooni;

import java.util.HashMap;

public class Player {
  private Hand hand;
  private boolean isShowingGuessCard;
  private int score;

  /**
   * Constructor for Player.
   */
  public Player() {
    // Initialize fields to default values
    hand = new Hand();
    isShowingGuessCard = false;
    score = 0;
  }

  /**
   * Discard the card at the given index.
   *
   * @param index the index of the card to discard
   */
  public void discard(int index) {
    hand.removeCard(index);
  }

  /**
   * Add the given card to the player's hand.
   *
   * @param index the index of the card to add
   * @param deck  the deck of cards
   */
  public void addCard(int index, Deck deck) {
    Card topCard = deck.deal();

    hand.addCard(index, topCard);
  }

  /**
   * Returns the cards of the player.
   *
   * @return Card[]
   */
  public Card[] getCards() {
    return hand.getCards();
  }

  /**
   * Returns the toString of the player's hand.
   *
   * @return String
   */
  public String getHandString() {
    return hand.toString();
  }

  /**
   * Get a list of possible pairs in the player's hand that add to the
   * guessCardValue.
   *
   * @return ArrayList<HashMap<Integer, Integer>>
   */
  public HashMap<Integer, Integer> getPossiblePairs(int guessCardValue) {
    return hand.getPossiblePairs(guessCardValue);
  }

  /**
   * Returns whether the player is showing the guess card.
   * 
   * @return boolean
   */
  public boolean getShowingGuessCard() {
    return isShowingGuessCard;
  }

  /**
   * Sets the boolean value of isShowingGuessCard.
   * 
   * @param isShowingGuessCard The boolean value to set isShowingGuessCard to.
   */
  public void setShowingGuessCard(boolean isShowingGuessCard) {
    this.isShowingGuessCard = isShowingGuessCard;
  }

  /**
   * Returns the score of the player.
   * 
   * @return int
   */
  public int getScore() {
    return score;
  }

  /**
   * Add a point to the player's score.
   */
  public void addPoint() {
    score++;
  }
}
