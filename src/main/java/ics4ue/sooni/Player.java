package ics4ue.sooni;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
  private Hand hand;
  private boolean isShowingGuessCard;
  private int guessCardValue;
  private int score;

  /**
   * Constructor for Player.
   */
  public Player() {
    // Initialize fields to default values
    hand = new Hand();
    isShowingGuessCard = false;
    guessCardValue = 0;
    score = 0;
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
   * Sets a card in the hand
   *
   * @param cardIndex The index of the card
   * @param card      The new card
   */
  public void setCardValue(int cardIndex, Card card) {
    hand.getCards()[cardIndex] = card;
  }

  /**
   * Returns whether the player is showing the guess card.
   * 
   * @return boolean
   */
  public boolean isShowingGuessCard() {
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
   * Returns the value of the guess card.
   * 
   * @return int
   */
  public int getGuessCardValue() {
    return guessCardValue;
  }

  /**
   * Sets the value of the guess card.
   * 
   * @param guessCardValue The value to set the guess card to.
   */
  public void setGuessCardValue(int guessCardValue) {
    this.guessCardValue = guessCardValue;
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

  /**
   * Sets the score of the player.
   * 
   * @param score The score to set the player to.
   */
  public void setScore(int score) {
    this.score = score;
  }
}
