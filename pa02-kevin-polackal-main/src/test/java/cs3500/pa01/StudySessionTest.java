package cs3500.pa01;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudySessionTest {
  private ArrayList<Question> questions = new ArrayList<>();
  private ArrayList<Question> questionsUpdated = new ArrayList<>();
  private Question one;
  private Question two;

  @BeforeEach
  public void setup() {
    one = new Question("What is the Capital of France?", "Paris", "Easy");
    two = new Question("Where is the Charles River?", "Boston", "Hard");

    final Question oneUpdated = new Question("What is the Capital of France?", "Paris", "Easy");
    final Question twoUpdated = new Question("Where is the Charles River?", "Boston", "Easy");
    questions.add(one);
    questions.add(two);
    questionsUpdated.add(oneUpdated);
    questionsUpdated.add(twoUpdated);
  }

  /**Tests a sample session run
   */
  @Test
  public void testSession() {
    StudySession session = new StudySession(questions, 1);
    //The session has another question (the only one)
    assertTrue(session.hasNextQuestion());
    //the question is the only hard one in the bank
    assertEquals(session.getNextQuestion(), two);
    session.adjustDifficulty("Easy");
    assertEquals(session.getAllQuestions(), questionsUpdated);
    //Should throw an error as there are no other questions in the session
    assertThrows(NoSuchElementException.class, () -> session.getNextQuestion());
  }

  /***Tests that the statistics properly display accurate data
   */
  @Test
  public void testStats() {
    StudySession session = new StudySession(questions, 1);
    session.getNextQuestion();
    session.adjustDifficulty("Easy");
    session.incrementTotalAnswered();
    String s = "Session statistics\n"
        + "==================\n"
        + "Total questions answered: 1\n"
        + "Questions changed from easy to hard: 0\n"
        + "Questions changed from hard to easy: 1\n"
        + "Total hard questions in bank: 0\n"
        + "Total easy questions in bank: 2\n"
        + "Thanks for studying!\n"
        + "==================\n";
    assertEquals(session.getStatistics(), s);
  }

  /***Tests that a question is properly adjusted and that is reflected in the list of all questions
   */
  @Test
  public void testAdjust() {
    StudySession session = new StudySession(questions, 1);
    session.getNextQuestion();
    session.adjustDifficulty("Easy");
    session.incrementTotalAnswered();
    assertEquals(session.getAllQuestions(), questionsUpdated);
    String s = "Session statistics\n"
        + "==================\n"
        + "Total questions answered: 1\n"
        + "Questions changed from easy to hard: 0\n"
        + "Questions changed from hard to easy: 1\n"
        + "Total hard questions in bank: 0\n"
        + "Total easy questions in bank: 2\n"
        + "Thanks for studying!\n"
        + "==================\n";
    assertEquals(s, session.getStatistics());
  }
}