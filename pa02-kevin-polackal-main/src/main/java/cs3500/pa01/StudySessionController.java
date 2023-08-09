package cs3500.pa01;



import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;


/**The controller for a study session
 */
public class StudySessionController {
  private StudySession session;
  private final View view;
  private Path path = null;
  private final Reader reader;



  StudySessionController(Readable input, Appendable appendable) {
    Readable input1 = Objects.requireNonNull(input);
    reader = new ReaderImpl(input1);
    view = new View(appendable);
  }

  /**Organizes the structure for a study session by calling the appropriate methods
   */
  public void runSession() throws IOException {
    initSession();
    runRounds();
    endSession();
  }

  /**Initializes the user's study session by prompting for a path for an sr file and how much
   * they would like to study
   *
   *  @return the list of study guide content
   */
  private void initSession() throws IOException {
    view.output("Welcome to your Study Session");

    while (true) {
      view.output("Enter a valid path to your .sr file: ");
      String fileName = input();

      File file = new File(fileName);
      if (file.exists() && !file.isDirectory()) {
        path = Paths.get(fileName);
        break;
      } else {
        view.output("Invalid path. Please try again.");
      }
    }
    ArrayList<Question> questions = getProblems();
    view.output("How many practice questions would you like to practice?: ");
    int questionNumber = Integer.parseInt(input());
    session = new StudySession(questions, questionNumber);
  }

  /***Gets the problem bank from a user inputted sr file
   *
   * @return the list of Questions from the inputted sr file
   *
   * @throws IOException the file was failed to be read
   */
  private ArrayList<Question> getProblems() throws IOException {
    ReadSr srReader = new ReadSr(new FileReader(path.toString()));
    return GenerationQuestions.generateData(srReader.readSr());
  }

  /***Cycles through the session questions quizzing the user
   *
   * @throws IOException throws if there is an error getting the next question
   */
  private void runRounds() throws IOException {
    while (session.hasNextQuestion()) {
      Question q = session.getNextQuestion();
      view.output("Question: " + q.getQuestion()
          + "\n1.Mark Easy 2.Mark Hard 3.Show Answer 4.Exit\n"
          + "Enter your choice's corresponding integer value: ");
      if (acceptAnswer(false).equals(Choice.SHOW_ANS.menuNum)) {
        view.output("Answer: " + q.getAnswer()
            + "\n1.Mark Easy 2.Mark Hard 4.Exit\n"
            + "Enter your choice's corresponding integer value: ");
        acceptAnswer(true);
      }
    }
  }

  /***Deals with accepting user info during a question session and deciding what do with it. Keeps
   * asking until a valid input is given
   *
   * @param answerShown has "Show Answer" already been selected?
   *
   * @return the integer corresponding to a valid user input
   *
   * @throws IOException error with user input
   */

  private String acceptAnswer(boolean answerShown) throws IOException {
    String choice = input();
    while ((choice.equals(Choice.SHOW_ANS.menuNum) && answerShown)
         || (!choice.equals(Choice.EASY.menuNum)
         && !choice.equals(Choice.HARD.menuNum)
         && !choice.equals(Choice.SHOW_ANS.menuNum)
         && !choice.equals(Choice.EXIT.menuNum))) {
      view.output("Invalid, please choose again: ");
      choice = input();
    }
    if (choice.equals(Choice.EASY.menuNum)) {
      session.adjustDifficulty("Easy");
      session.incrementTotalAnswered();
    } else if (choice.equals(Choice.HARD.menuNum)) {
      session.adjustDifficulty("Hard");
      session.incrementTotalAnswered();
    } else if (choice.equals(Choice.EXIT.menuNum)) {
      endSession();
    }
    return choice;
  }

  /***Displays final user session statistics and writes back to the
   *  sr file the updated question bank
   *
   *  @throws IOException the file was failed to be written to
   */
  private void endSession() throws IOException {
    view.output(session.getStatistics());
    Write.writeToFile(session.getAllQuestions(), path.toString());
  }

  /***Accepts user input
   *
   * @return a string representation of the user input
   */
  String input() {
    return reader.read();
  }
}
