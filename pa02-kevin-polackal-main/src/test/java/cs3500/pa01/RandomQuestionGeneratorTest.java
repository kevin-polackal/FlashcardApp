package cs3500.pa01;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RandomQuestionGeneratorTest {
  ArrayList<Question> questions;
  ArrayList<Question> questionsHard;

  @BeforeEach
  public void setup() {

    try {
      ReadSr srReader = new ReadSr(new FileReader("srFiles/questions.sr"));
      questions = GenerationQuestions.generateData(srReader.readSr());
      srReader = new ReadSr(new FileReader("srFiles/questionsAllHard.sr"));
      questionsHard = GenerationQuestions.generateData(srReader.readSr());
    } catch (IOException e) {
      fail();
    }
  }


  /***Integration test for generating random questions, compares to see if when the method is called
   * twice with the same amount of desired questions it will generate two different lists
   */
  @Test
  public void testRandomness() {
    RandomQuestionGenerator select = new RandomQuestionGenerator();

    //All the questions
    final ArrayList<Question> random1 = select.generateRandomQuestions(questions, 50);
    select = new RandomQuestionGenerator();
    final ArrayList<Question> random2 = select.generateRandomQuestions(questions, 50);
    select = new RandomQuestionGenerator();

    //A mix of hard and easy questions
    final ArrayList<Question> random3 = select.generateRandomQuestions(questions, 23);
    select = new RandomQuestionGenerator();
    final ArrayList<Question> random4 = select.generateRandomQuestions(questions, 23);
    select = new RandomQuestionGenerator();

    //All hard questions
    final ArrayList<Question> random1Hard = select.generateRandomQuestions(questionsHard, 15);
    select = new RandomQuestionGenerator();
    final ArrayList<Question> random2Hard = select.generateRandomQuestions(questionsHard, 15);
    //All the questions should equal each other
    assertEquals(random1, random2);
    //Should not equal
    assertNotEquals(random3, random4);
    //Should not equal
    assertNotEquals(random1Hard, random2Hard);
  }
}