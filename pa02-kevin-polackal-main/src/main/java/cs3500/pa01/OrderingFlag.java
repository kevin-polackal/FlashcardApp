package cs3500.pa01;

import java.util.Comparator;

/***To represent the 3 ordering flags, which consist of their string representation and comparator
 */
public enum OrderingFlag {
  FILENAME(new FilenameComparator(), "filename"),
  MODIFIED(new ModifiedComparator(), "modified"),
  CREATED(new CreationComparator(), "created");
  final Comparator<MarkdownFile> comp;
  final String name;

  OrderingFlag(Comparator<MarkdownFile> comp, String name) {
    this.comp = comp;
    this.name = name;
  }
}
