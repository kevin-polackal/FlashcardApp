package cs3500.pa01;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrackStatisticsTest {
  private TrackStatistics statistics;
  private final ArrayList<Question> questions = new ArrayList<>();

  @BeforeEach
  public void setup() {
    statistics = new TrackStatistics();
    Question one = new Question("What is the Capital of France?", "Paris", "Easy");
    Question two = new Question("Where is the Charles River?", "Boston", "Hard");
    questions.add(one);
    questions.add(two);
  }

  /***Tests that the setters properly mutate the statistics and the getters properly return
   * updated values
   */
  @Test
  public void testGettersAndAdders() {
    final String defaultString = statistics.toString();
    statistics.addOneTotalAnswered();
    statistics.addOneHardToEasy();
    statistics.addOneEasyToHard();
    statistics.setEasyQuestions(questions);
    statistics.setHardQuestions(questions);
    assertNotEquals(defaultString, statistics.toString());
  }

  /***Tests that a proper view of the statistics is given
   */
  @Test
  public void testToString() {
    statistics.addOneTotalAnswered();
    statistics.addOneHardToEasy();
    statistics.addOneEasyToHard();
    statistics.setEasyQuestions(questions);
    statistics.setHardQuestions(questions);
    String sessionStats = "Session statistics\n"
        + "==================\n"
        + "Total questions answered: 1\n"
        + "Questions changed from easy to hard: 1\n"
        + "Questions changed from hard to easy: 1\n"
        + "Total hard questions in bank: 1\n"
        + "Total easy questions in bank: 1\n"
        + "Thanks for studying!\n"
        + "==================\n";
    assertEquals(statistics.toString(), sessionStats);


  }

}