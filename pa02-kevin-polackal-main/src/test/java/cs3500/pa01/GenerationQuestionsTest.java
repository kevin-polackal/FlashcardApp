package cs3500.pa01;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class GenerationQuestionsTest {

  /***Tests that a list of questions in string format is properly formatted into its correspomding
   * list of Questions
   */
  @Test
  public void testGenerateData() {
    GenerationQuestions coverTitle = new GenerationQuestions();
    ArrayList<String> stringQuestion = new ArrayList<>();
    stringQuestion.add("Am i under arrest?|Yes you are, what a shame!|Hard");
    ArrayList<Question> questions = new ArrayList<>();
    Question q = new Question("Am i under arrest?", "Yes you are, what a shame!", "Hard");
    questions.add(q);
    assertEquals(questions, GenerationQuestions.generateData(stringQuestion));
  }

  /***Tests that an empty list of questions is returned if an empty list of strings is provided
   */
  @Test
  public void testGenerateDataEmptyInfo() {
    ArrayList<String> stringQuestion = new ArrayList<>();
    ArrayList<Question> questions = new ArrayList<>();
    assertEquals(questions, GenerationQuestions.generateData(stringQuestion));
  }
}
