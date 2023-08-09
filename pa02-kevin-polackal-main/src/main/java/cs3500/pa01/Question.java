package cs3500.pa01;

/***To represent a question
 */
public class Question {
  private final String question;
  private final String answer;
  private String difficulty;

  Question(String q, String a, String d) {
    this.question = q;
    this.answer = a;
    this.difficulty = d;
  }

  /***Gets the Questions's question portion
   *
   * @return the question part of the Q and A
   */
  public String getQuestion() {
    return this.question;
  }

  /***Gets the Question's answer portion
   *
   * @return the answer part of the Q and A
   */
  public String getAnswer() {
    return this.answer;
  }

  /***Gets the Question's difficulty portion
   *
   * @return the difficulty part of the Question
   */
  public String getDifficulty() {
    return this.difficulty;
  }

  /***Sets the difficulty to the given string
   *
   * @param d "Easy" or "Hard"
   */
  public void setDifficulty(String d) {
    this.difficulty = d;
  }

  /***Creates a string representation of the question object
   *
   * @return the string representation of the question object
   */
  public String toString() {
    return this.question + "|" + this.answer + "|" + this.difficulty;
  }

  /***Determines whether this Question and the given Object are equal
   *
   * @param o a java object
   *
   * @return if this Question and the given object are equal
   */
  public boolean equals(Object o) {
    if (!(o instanceof Question)) {
      return false;
    }
    Question other = (Question) o;
    return this.toString().equals(other.toString());
  }

  /***Creates a custom hashcode for a Question to minimize collisions
   *
   * @return the hashcode of the Question
   */
  public int hashCode() {
    return (int) this.question.charAt(0) + (int) this.answer.charAt(0)
        + (int) this.difficulty.charAt(0) + 10000;
  }
}
