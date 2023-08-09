package cs3500.pa01;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReadSrTest {
  private Path path;
  private ArrayList<Question> questions = new ArrayList<>();

  ReadSr srReader;

  @BeforeEach
  public void setup() {
    path = Paths.get("srFiles/exampleQandA.sr");
    try {
      srReader = new ReadSr(new FileReader(path.toString()));
    } catch (IOException e) {
      fail();
    }
    Question one = new Question("What is the Capital of France?", "Paris", "Easy");
    Question two = new Question("Where is the Charles River?", "Boston", "Hard");
    questions.add(one);
    questions.add(two);
  }

  /***Tests that the information read from a sr file matches the expected output
   */
  @Test
  public void testRead() {
    try {
      assertEquals(questions, GenerationQuestions.generateData(srReader.readSr()));
    } catch (IOException e) {
      fail();
    }
  }
}