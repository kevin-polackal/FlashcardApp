package cs3500.pa01;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * To write the complied study-guide notes into a single file
 */
public class Write {

  /**
   * To write the complied study-guide notes into a single file.
   * If the given file does not exist, one is created
   *
   * @param out the file-path to an already existing file or a file to be created
   * @throws IOException if an I/O error occurs
   */
  public static <T> void writeToFile(ArrayList<T> toWrite, String out) throws IOException {
    FileWriter fileWriter = new FileWriter(out);
    for (T t : toWrite) {
      fileWriter.write(t.toString() + "\n");
    }
    fileWriter.close();
  }
}
