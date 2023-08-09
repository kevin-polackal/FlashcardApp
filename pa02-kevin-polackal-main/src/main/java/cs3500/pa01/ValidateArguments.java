package cs3500.pa01;

import java.io.File;

/***Validates the user's command line inputs
 */
public class ValidateArguments {

  /***Validates that there is:
   * - three command line arguments given
   * - the flag input is one of the three valid flags
   * - the input and output paths are valid
   *
   * @param args the command line inputs
   */
  public static void validate(String[] args) {
    if (args.length != 3) {
      throw new RuntimeException("Incorrect number of Command Line Arguments!");
    }
    File file = new File(args[0]);
    if (!file.exists()) {
      throw new IllegalArgumentException("Invalid input path argument!");
    }
    boolean validFlag = args[1].equalsIgnoreCase(OrderingFlag.MODIFIED.name)
        || args[1].equalsIgnoreCase(OrderingFlag.CREATED.name)
        || args[1].equalsIgnoreCase(OrderingFlag.FILENAME.name);
    if (!validFlag) {
      throw new IllegalArgumentException("Invalid orderingFlag argument!");
    }
    File fileOut = new File(args[2]);
    if (fileOut.isDirectory() || !fileOut.toString().endsWith(".md")) {
      throw new IllegalArgumentException("Invalid output path argument!");
    }
  }
}
