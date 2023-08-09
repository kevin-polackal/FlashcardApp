package cs3500.pa01;



import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class ChoiceTest {

  /**Tests a properly created Choice enum
   */

  @Test
  public void testChoice() {
    assertNotEquals(Choice.EASY, Choice.EXIT);
    assertNotEquals(Choice.HARD, Choice.SHOW_ANS);
  }
}