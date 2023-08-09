package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the WriteStudyGuide Class
 */
public class WriteTest {
  Write writer;
  private MarkdownFileWalker finder;
  private ArrayList<MarkdownFile> files;
  static final String SAMPLE_INPUTS_DIR =
      "src/test/resources/StudyMaterials/Java_IO/lecture-006-test.md";

  static final String location = "src/test/tempfiles";

  /**
   * Sets up a list of markdown files for both tests
   */
  @BeforeEach
  public void setup() {
    finder = new MarkdownFileWalker();
    try {
      Files.walkFileTree(Paths.get(SAMPLE_INPUTS_DIR), finder);
    } catch (IOException e) {
      fail();
    }
    files = finder.getMdFiles();
  }

  /**
   * Tests that the write method properly write to a temporary md file
   */
  @Test
  public void testWrite() throws IOException {
    ReadMd reader = new ReadMd(finder.getMdFiles());
    reader.read();
    assertEquals(reader.getIncludeInStudyGuide().get(0), "# This is a test file");
    Path tempMd = Files.createTempFile(Paths.get(location), "test", ".md");
    Write.writeToFile(reader.getIncludeInStudyGuide(), tempMd.toString());
    String fileString = Files.readString(tempMd).strip();
    assertEquals(fileString, "# This is a test file");
    Files.delete(tempMd);
  }

  /**
   * Tests that the write method properly write to a temporary sr file
   */
  @Test
  public void testWriteSr() throws IOException {
    ReadMd reader = new ReadMd(finder.getMdFiles());
    reader.read();
    assertEquals(reader.getSrInfo().get(0), "Is this an important question?|im not sure!|Hard");
    Path tempSr = Files.createTempFile(Paths.get(location), "test", ".md");
    Write.writeToFile(reader.getSrInfo(), tempSr.toString());
    String fileString = Files.readString(tempSr).strip();
    assertEquals(fileString, "Is this an important question?|im not sure!|Hard");
    Files.delete(tempSr);
  }

  /**
   * Tests that an error properly throws when a bad path is given
   */
  @Test
  public void testInvalidPath() {
    Path p = Paths.get("/Desktop/Bad");
    ReadMd reader = new ReadMd(finder.getMdFiles());
    assertThrows(IOException.class,
        () -> Write.writeToFile(reader.getIncludeInStudyGuide(), p.toString()));
  }
}
