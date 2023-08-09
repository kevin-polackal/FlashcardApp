package cs3500.pa01;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuestionTest {
  Question one;
  Question two;

  @BeforeEach
  public void setup() {
    one = new Question("What is 1 + 1?", "2", "Easy");
    two = new Question("What is the meaning of life?", "...", "Hard");
  }

  /***Tests that the getters return the desired values
   */
  @Test
  public void testGetters() {
    assertEquals(one.getQuestion(), "What is 1 + 1?");
    assertEquals(two.getAnswer(), "...");
    assertEquals(two.getDifficulty(), "Hard");
  }

  /***Tests that the setters accurately update the data's value
   */
  @Test
  public void testSetter() {
    assertEquals(one.getDifficulty(), "Easy");
    one.setDifficulty("Hard");
    assertEquals(one.getDifficulty(), "Hard");
  }

  /***Tests that the toString method produces the desired string representation
   */
  @Test
  public void testToString() {
    assertEquals(one.toString(), "What is 1 + 1?|2|Easy");
    assertEquals(two.toString(), "What is the meaning of life?|...|Hard");
  }

  /***Tests the override of equals properly functions
   */
  @Test
  public void testEquals() {
    assertFalse(one.equals(two));
    assertFalse(one.equals(5));
  }

  /***Tests the override of hashcode properly functions
   */
  @Test
  public void testHashCode() {
    assertEquals(one.hashCode(), 10206);
  }
}