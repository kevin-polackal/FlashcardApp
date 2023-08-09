package cs3500.pa01;



import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class OrderingFlagTest {
  /***Tests that a OrderingFlag enum is properly created
   */
  @Test
  public void testOrderingFlag() {
    assertNotEquals(OrderingFlag.FILENAME, OrderingFlag.MODIFIED);
    assertNotEquals(OrderingFlag.MODIFIED, OrderingFlag.CREATED);
  }

}