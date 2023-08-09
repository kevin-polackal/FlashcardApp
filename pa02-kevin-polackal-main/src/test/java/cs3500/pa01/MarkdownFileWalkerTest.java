package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;


/**
 * Tests for the MarkdownFinder Class
 */
class MarkdownFileWalkerTest {
  static final String SAMPLE_INPUTS_DIR = "src/test/resources/StudyMaterials";
  static final String POOR_INPUTS_DIR = " ";

  /**
   * Tests that the MarkdownFinder properly creates a list of markdown files sorted properly
   */
  @Test
  public void testMarkdownList() {
    MarkdownFileWalker finder = new MarkdownFileWalker();
    try {
      Files.walkFileTree(Paths.get(SAMPLE_INPUTS_DIR), finder);
    } catch (IOException e) {
      fail();
    }
    ArrayList<String> files = new ArrayList<>();
    files.add("geographyQuestions.md");
    files.add("lecture-001-arrays.md");
    files.add("lecture-002-reading.md");
    files.add("lecture-003-writing.md");
    files.add("lecture-004-standardOut.md");
    files.add("lecture-005-vectors.md");
    files.add("lecture-006-test.md");
    finder.getMdFiles().sort(new FilenameComparator());
    for (int i = 0; i < finder.getMdFiles().size(); i++) {
      assertEquals(files.get(i), finder.getMdFiles().get(i).getName());
    }
  }

  /**
   * Tests that a bad directory throws an error
   */
  @Test
  public void testFileFailed() {
    MarkdownFileWalker finder = new MarkdownFileWalker();
    try {
      Files.walkFileTree(Paths.get(POOR_INPUTS_DIR), finder);
    } catch (IOException e) {
      assertThrows(IOException.class, () -> finder.handleFileFailed());

    }
  }
}