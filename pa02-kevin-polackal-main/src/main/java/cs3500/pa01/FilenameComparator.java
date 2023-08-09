package cs3500.pa01;

import java.util.Comparator;

/***To compare two markdown files by filename
 */
public class FilenameComparator implements Comparator<MarkdownFile> {

  /***To compare two markdown files by filename
   *
   * @param o1 the first MarkdownFile to be compared.
   *
   * @param o2 the second MarkdownFile to be compared
   *           .
   * @return a negative integer if the first is "less than" the second, a positive integer if the
   *     second is "less than" the first, or 0 if they are equal
   */
  public int compare(MarkdownFile o1, MarkdownFile o2) {
    return o1.getName().compareTo(o2.getName().toLowerCase());
  }
}
