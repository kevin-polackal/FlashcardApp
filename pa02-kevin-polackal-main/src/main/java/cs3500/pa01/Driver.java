package cs3500.pa01;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


/**
 * This is the main driver of this project.
 */

public class Driver {
  /**
   * Project entry point
   *
   * @param args Three Command Line Arguments: The path (relative or absolute) to be explored,
   *             the type of orderingFlag(filename,created,modified) to sort by, and the path
   *             (relative or absolute) to where the study guide file should live. If no arguments
   *             are entered, a study session commences
   * @throws IOException if an I/O error occurs
   * @throws RuntimeException if there is an incorrect number of command line arguments
   */
  public static <T> void main(String[] args) throws IOException {
    if (args.length == 0) {
      StudySessionController controller =
          new StudySessionController(new InputStreamReader(System.in), new PrintStream(System.out));
      controller.runSession();
    } else {
      ExecuteStudyGuide.createFiles(args);
    }
  }
}