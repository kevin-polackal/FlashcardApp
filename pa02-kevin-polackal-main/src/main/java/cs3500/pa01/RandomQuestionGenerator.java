package cs3500.pa01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * To generate a certain number of questions from a question bank randomly
 * choosing from hard questions first
 */
public class RandomQuestionGenerator {
  private final ArrayList<Question> easyQuestions = new ArrayList<>();
  private final ArrayList<Question> hardQuestions = new ArrayList<>();

  private final ArrayList<Question> randomQuestions = new ArrayList<>();

  /**
   * Given a question bank and a number of desired questions to study creates a randomizes
   * collection of questions
   *
   *  @param questions the question bank
   *  @param questionsToStudy the desired number of questions to study (the size of the whole bank
   *                          if number is greater than total questions)
   *
   * @return the random questions
   */

  public ArrayList<Question> generateRandomQuestions(ArrayList<Question> questions,
                                                     int questionsToStudy) {
    splitByDifficulty(questions);
    if (questionsToStudy >= questions.size()) {
      randomQuestions.addAll(questions);
    } else if (questionsToStudy <= hardQuestions.size()) {
      getQuestions(hardQuestions, questionsToStudy);
    } else {
      randomQuestions.addAll(hardQuestions);
      getQuestions(easyQuestions, questionsToStudy - hardQuestions.size());
    }
    return randomQuestions;
  }

  /**
   * Given the easy questions or hard questions creates a randomized
   * collection of questions based on the number of questions desired
   *
   *  @param questions easy or hard questions
   *  @param numOfQuestions the desired number of questions to be added to the random list of
   *                        questions
   */
  private void getQuestions(ArrayList<Question> questions, int numOfQuestions) {
    for (int i = 0; i < numOfQuestions; i++) {
      Collections.shuffle(questions, new Random());
      randomQuestions.add(questions.get(0));
      questions.remove(0);
    }
  }

  /**
   *  Splits the supplied question bank into easy and hard questions
   *
   *  @param questions the question bank
   */
  private void splitByDifficulty(ArrayList<Question> questions) {
    for (Question q : questions) {
      if (q.toString().endsWith("Hard")) {
        hardQuestions.add(q);
      } else {
        easyQuestions.add(q);
      }
    }
  }
}
