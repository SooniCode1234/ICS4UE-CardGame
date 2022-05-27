package ics4ue.sooni;

public class Player {
  private boolean isShowingGuessCard;
  private int guessCardValue;
  private int score;

  /**
   * Constructor for Player.
   */
  public Player() {
    // Initialize fields to default values
    isShowingGuessCard = false;
    guessCardValue = 0;
    score = 0;
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
   * Sets the score of the player.
   * 
   * @param score The score to set the player to.
   */
  public void setScore(int score) {
    this.score = score;
  }
}
