package cs3500.pa01;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.StringWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ViewTest {
  private View view;
  private Appendable writer;

  @BeforeEach
  public void setup() {
    writer = new StringWriter();
    view = new  View(writer);
  }

  /***Tests that the view class properly outputs the data it is given
   */
  @Test
  public void testOutput() {
    view.output("Testing the View Class");
    assertEquals(writer.toString(), "Testing the View Class\n");
  }

  /***Mimics an error in the output using a mock, and catches an IOException
   */
  @Test
  public void testError() {
    View badView = new View(new MockAppendable());
    Exception exc = assertThrows(RuntimeException.class, () -> badView.output("Test"),
        "Mock throwing an error");
    assertEquals("java.io.IOException: Mock throwing an error", exc.getMessage());

  }
}

