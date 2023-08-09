package cs3500.pa01;

import java.io.IOException;
import java.util.Objects;

/***User facing View to display information sent from the controller
 **/
public class View {

  private final Appendable output;

  public View(Appendable appendable) {
    this.output = Objects.requireNonNull(appendable);
  }

  /***Outputs the given information from the controller to the user
   *
   * @param toUser the information from the controller in string format
   */
  public void output(String toUser) {
    //System.out.println(toUser);
    try {
      output.append(toUser + "\n"); // this may fail, hence the try-catch
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
