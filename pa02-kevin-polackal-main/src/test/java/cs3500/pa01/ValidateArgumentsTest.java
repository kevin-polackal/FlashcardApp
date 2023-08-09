package cs3500.pa01;



import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ValidateArgumentsTest {

  private String[] args;


  /**
   * Tests that an error is thrown if an improper amount of command line input
   * is given
   */
  @Test
  public void testProperNumOfArgs() {
    args = new String[1];
    assertThrows(RuntimeException.class, () -> ValidateArguments.validate(args));
  }

  /**
   * Tests that an error is thrown if an improper input file is given
   */
  @Test
  public void testValidInputPath() {
    args = new String[3];
    args[0] = "fakeDirectory";
    args[1] = "created";
    args[2] = "src/studyGuide.md";
    assertThrows(IllegalArgumentException.class, () -> ValidateArguments.validate(args));
  }

  /**
   * Tests that an error is thrown if an improper ordering flag is given
   */
  @Test
  public void testValidFlag() {
    args = new String[3];
    args[0] = "src";
    args[1] = "fakeFlag";
    args[2] = "src/studyGuide.md";
    assertThrows(IllegalArgumentException.class, () -> ValidateArguments.validate(args));
  }

  /**
   * Tests that an error is thrown if an improper output is given
   */
  @Test
  public void testValidOutputPath() {
    args = new String[3];
    args[0] = "src";
    args[1] = "filename";
    args[2] = "src";
    assertThrows(IllegalArgumentException.class, () -> ValidateArguments.validate(args));
  }

  /**
   * Tests that a valid command line input doesn't throw an error
   */
  @Test
  public void testValidCommandLine() {
    args = new String[3];
    args[0] = "src";
    args[1] = "modified";
    args[2] = "src/studyGuide.md";
    assertDoesNotThrow(() -> ValidateArguments.validate(args));
  }

}