package cs3500.pa01;

import java.util.ArrayList;

/***To generate a collection of questions*/
public class GenerationQuestions {

  /***Takes in a list of questions in string representation and converts them into a list of
   * Question objects
   *
   * @param strings the list of questions in string representation
   *
   * @return the list of question objects
   */
  public static ArrayList<Question> generateData(ArrayList<String> strings) {
    ArrayList<Question> questions = new ArrayList<>();
    for (String s : strings) {
      String [] parts = s.split("\\|", 3);
      Question q = new Question(parts[0], parts[1], parts[2]);
      questions.add(q);
    }
    return questions;
  }
}

