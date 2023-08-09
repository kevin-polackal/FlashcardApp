package cs3500.pa01;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/***Model class of the MVC structure, to represent a study session
 */
public class StudySession {
  private final ArrayList<Question> allQuestions;
  private final ArrayList<Question> sessionQuestions;
  TrackStatistics statistics = new TrackStatistics();
  Question question = null;

  StudySession(ArrayList<Question> questions, int questionsToStudy) {
    RandomQuestionGenerator getQuestions = new RandomQuestionGenerator();
    this.sessionQuestions = getQuestions.generateRandomQuestions(questions, questionsToStudy);
    this.allQuestions = questions;
  }

  /***Gets the next question in a study session
   *
   * @return the next question in the study session
   */
  public Question getNextQuestion() {
    if (!hasNextQuestion()) {
      throw new NoSuchElementException("No more Questions!");
    }
    this.question = sessionQuestions.remove(0);
    return this.question;
  }

  /***Adds one to the count of total questions answered
   */
  public void incrementTotalAnswered() {
    statistics.addOneTotalAnswered();
  }

  /***Adjusts the difficulty of the current question if the supplied difficulty is different
   * from the current difficulty
   *
   * @param diff "Hard" or "Easy"
   */
  public void adjustDifficulty(String diff) {
    if (question.getDifficulty().equals("Hard") && diff.equals("Easy")) {
      statistics.addOneHardToEasy();
    } else if (question.getDifficulty().equals("Easy") && diff.equals("Hard")) {
      statistics.addOneEasyToHard();
    }

    int idx = 0;
    for (int i = 0; i < allQuestions.size(); i++) {
      if (allQuestions.get(i).equals(this.question)) {
        idx = i;
      }
    }
    question.setDifficulty(diff);
    allQuestions.set(idx, question);
  }

  /***Determines whether there is one or more questions remaining in the session
   *
   * @return if there is more than 1 session questions remaining
   */
  public boolean hasNextQuestion() {
    return sessionQuestions.size() >= 1;
  }

  /***Gets all the questions in the question bank
   *
   * @return the updated question bank
   */
  public ArrayList<Question> getAllQuestions() {
    return this.allQuestions;
  }

  /***Creates a string representation of the statistics of this study session
   *
   *  @return the string representation of this session's statistics
   */
  public String getStatistics() {
    statistics.setHardQuestions(allQuestions);
    statistics.setEasyQuestions(allQuestions);
    return statistics.toString();
  }
}
