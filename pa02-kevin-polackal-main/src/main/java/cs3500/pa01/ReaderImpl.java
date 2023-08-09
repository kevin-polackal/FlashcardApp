package cs3500.pa01;

import java.util.Objects;
import java.util.Scanner;

/**
 * Custom implementation of the Reader interface
 */
public class ReaderImpl implements Reader {
  private final Scanner scanner;

  public ReaderImpl(Readable readable) {
    Readable readable1 = Objects.requireNonNull(readable);
    scanner = new Scanner(readable1);
  }

  /**Handles reading in data from a Readable
   *
   * @return the string representation of the next line from the readable
   */
  @Override
  public String read() {
    StringBuilder output = new StringBuilder();
    output.append(scanner.nextLine());
    return output.toString();
  }

  /***Returns whether the readable has a next line
   *
   * @return true if the readable has next
   */
  public boolean hasNext() {
    return scanner.hasNext();
  }
}
