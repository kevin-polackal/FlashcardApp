package cs3500.pa01;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudySessionControllerTest {

  private ArrayList<Question> questions = new ArrayList<>();
  private ArrayList<Question> hardQuestions = new ArrayList<>();

  @BeforeEach
  public void setup() {
    final Question one = new Question("What is 1 + 1?", "2", "Easy");
    final Question two = new Question("What is the meaning of life?", "...", "Easy");
    final Question three = new Question("What is 1 + 1?", "2", "Hard");
    final Question four = new Question("What is the meaning of life?", "...", "Hard");
    questions.add(one);
    questions.add(two);
    hardQuestions.add(three);
    hardQuestions.add(four);
  }

  /**
   * Integration test using the controller's public run session method. Give input data through
   * files (see ControllerTest and secondControllerTest)  and tests all the possible branches
   *
   * @throws IOException error with running the session
   */
  @Test
  public void testStudyController() throws IOException {
    StudySessionController test =
        new StudySessionController(new FileReader("srFiles/ControllerTest"),
            new PrintStream(System.out));
    test.runSession();
    ReadSr read = new ReadSr(new FileReader("srFiles/writeTester.sr"));
    ArrayList<String> questionsAsString = read.readSr();
    assertEquals(GenerationQuestions.generateData(questionsAsString), questions);

    test =
        new StudySessionController(new FileReader("srFiles/secondControllerTest"),
            new PrintStream(System.out));
    test.runSession();
    read = new ReadSr(new FileReader("srFiles/secondWriteTester.sr"));
    questionsAsString = read.readSr();
    assertEquals(GenerationQuestions.generateData(questionsAsString), hardQuestions);
  }

}