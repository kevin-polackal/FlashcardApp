package cs3500.pa01;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the MarkdownFile class
 */
class MarkdownFileTest {
  private MarkdownFile file;
  private FileTime creationTime;
  private FileTime lastModifiedTime;
  static final String FILE_DIR = "src/test/resources/StudyMaterials/lecture-005-vectors.md";

  /**
   * Initializes FileTimes and creates a file for tests
   */
  @BeforeEach
  public void setup() {
    creationTime = FileTime.from(Instant.parse("2010-11-20T11:59:00Z"));
    lastModifiedTime = FileTime.from(Instant.parse("2019-04-20T11:59:00Z"));
    file = new MarkdownFile(Paths.get(FILE_DIR),
        "lecture-005-vectors.md", creationTime, lastModifiedTime);
  }

  /**
   * Tests that all getters work properly
   */
  @Test
  public void testGetters() {
    assertEquals(file.getPath(),
        Paths.get("src/test/resources/StudyMaterials/lecture-005-vectors.md"));
    assertEquals(file.getName(), "lecture-005-vectors.md");
    assertEquals(file.getCreationTime(), creationTime);
    assertEquals(file.getLastModified(), lastModifiedTime);
  }

}