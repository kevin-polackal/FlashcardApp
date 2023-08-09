package cs3500.pa01;

import java.util.ArrayList;

/***Tracks the statistics for a Question bank
 */
public class TrackStatistics {
  private int totalAnswered = 0;
  private int easyToHard = 0;
  private int hardToEasy = 0;
  private int hardQuestions = 0;
  private int easyQuestions = 0;

  /***Increments the total questions answered by 1
   */
  public void addOneTotalAnswered() {
    this.totalAnswered += 1;
  }

  /***Increments the count of questions changed from easy to hard by 1
   */
  public void addOneEasyToHard() {
    this.easyToHard += 1;
  }

  /***Increments the count of questions changed from easy to hard by 1
   */
  public void addOneHardToEasy() {
    this.hardToEasy += 1;
  }

  /***Sets the count of hard questions in the question bank
   *
   * @param questions the list of questions being updated every question asked
   */
  public void setHardQuestions(ArrayList<Question> questions) {
    this.hardQuestions = 0;
    for (Question q : questions) {
      if (q.toString().endsWith("Hard")) {
        this.hardQuestions++;
      }
    }
  }

  /***Sets the count of easy questions in the question bank
   *
   * @param questions the list of questions being updated every question asked
   */
  public void setEasyQuestions(ArrayList<Question> questions) {
    this.easyQuestions = 0;
    for (Question q : questions) {
      if (q.toString().endsWith("Easy")) {
        this.easyQuestions++;
      }
    }
  }

  /***Creates a string representation of the statistics
   *
   * @return a string representation of the statistics
   */
  public String toString() {
    return "Session statistics\n"
        + "==================\n"
        + "Total questions answered: " + this.totalAnswered + "\n"
        + "Questions changed from easy to hard: " + this.easyToHard + "\n"
        + "Questions changed from hard to easy: " + this.hardToEasy + "\n"
        + "Total hard questions in bank: " + this.hardQuestions + "\n"
        + "Total easy questions in bank: " + this.easyQuestions + "\n"
        + "Thanks for studying!\n"
        + "==================\n";
  }
}
