package cs3500.pa01;

/***To represent a choice in the study session menu
 */
public enum Choice {
  EASY("1"),
  HARD("2"),
  SHOW_ANS("3"),
  EXIT("4");
  final String menuNum;

  Choice(String value) {
    this.menuNum = value;
  }
}
