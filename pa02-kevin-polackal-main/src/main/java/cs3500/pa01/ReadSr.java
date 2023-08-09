package cs3500.pa01;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


/**
 * To read a sr file
 */
public class ReadSr {
  private final ArrayList<String> stringQuestions = new ArrayList<>();
  private final ReaderImpl reader;

  ReadSr(Readable reader) {
    Readable input1 = Objects.requireNonNull(reader);
    this.reader = new ReaderImpl(reader);
  }

  /**
   * Reads a sr file from the given path and aggregates the lines into a list of questions
   *
   * @return the list of questions from the sr file in sting format
   *
   */
  public  ArrayList<String> readSr() throws IOException {
    while (reader.hasNext()) {
      String next = reader.read();
      stringQuestions.add(next);
    }
    return stringQuestions;
  }
}
