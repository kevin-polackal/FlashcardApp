package cs3500.pa01;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilenameComparatorTest {
  private MarkdownFile file;
  private MarkdownFile file2;
  private MarkdownFile file3;
  static final String FILE_DIR = "src/test/resources/StudyMaterials/lecture-005-vectors.md";
  static final String FILE_DIR_2 = "src/test/resources/StudyMaterials/lecture-001-arrays.md";

  @BeforeEach
  public void setup() {
    FileTime creationTime = FileTime.from(Instant.parse("2010-11-20T11:59:00Z"));
    FileTime lastModifiedTime = FileTime.from(Instant.parse("2019-04-20T11:59:00Z"));
    file = new MarkdownFile(Paths.get(FILE_DIR), "lecture-005-vectors.md", creationTime,
        lastModifiedTime);
    FileTime creationTime2 = FileTime.from(Instant.parse("2020-11-20T11:59:00Z"));
    FileTime lastModifiedTime2 = FileTime.from(Instant.parse("2019-04-20T11:59:00Z"));
    file2 = new MarkdownFile(Paths.get(FILE_DIR_2), "lecture-001-arrays.md", creationTime2,
        lastModifiedTime2);

    FileTime creationTime3 = FileTime.from(Instant.parse("2020-11-20T11:59:00Z"));
    FileTime lastModifiedTime3 = FileTime.from(Instant.parse("2019-04-20T11:59:00Z"));
    file3 = new MarkdownFile(Paths.get(FILE_DIR_2), "lecture-001-arrays.md", creationTime3,
        lastModifiedTime3);
  }

  /**
   * Tests the 3 possible sets of return values of the filename comparator
   */
  @Test
  public void testNameComp() {
    FilenameComparator comp = new FilenameComparator();
    assertTrue(comp.compare(file, file2) > 0);
    assertTrue(comp.compare(file2, file) < 0);
    assertEquals(comp.compare(file2, file3), 0);
  }
}