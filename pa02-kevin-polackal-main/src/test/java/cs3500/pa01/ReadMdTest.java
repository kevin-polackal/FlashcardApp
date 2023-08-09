package cs3500.pa01;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the ReadImportantLines Class
 */
class ReadMdTest {
  private Scanner fileScanner;
  private ArrayList<MarkdownFile> files;
  private final ArrayList<MarkdownFile> badFiles = new ArrayList<>();
  static final String SAMPLE_INPUTS_DIR =
      "src/test/resources/StudyMaterials/Java_IO/lecture-002-reading.md";
  static final String POOR_INPUTS_DIR =
      "src/test/resources/StudyMaterials/Java_IO/lture-002-reading.md";

  /**
   * Sets up a list of markdown files for testing
   */

  @BeforeEach
  public void setup() {
    FileTime creationTime = FileTime.from(Instant.parse("2010-11-20T11:59:00Z"));
    FileTime lastModifiedTime = FileTime.from(Instant.parse("2019-04-20T11:59:00Z"));
    MarkdownFileWalker finder = new MarkdownFileWalker();
    try {
      Files.walkFileTree(Paths.get(SAMPLE_INPUTS_DIR), finder);
    } catch (IOException e) {
      fail();
    }
    files = finder.getMdFiles();
    MarkdownFile badFile = new MarkdownFile(Paths.get(POOR_INPUTS_DIR),
        "lture-002-reading.md", creationTime, lastModifiedTime);
    badFiles.add(badFile);
  }

  /**
   * Tests that the findImportantLines properly works with a good list of files
   */
  @Test
  public void testReadImportant() {
    ReadMd reader = new ReadMd(files);
    try {
      reader.read();
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
    final ArrayList<String> studyGuideInfo = reader.getIncludeInStudyGuide();
    final ArrayList<String> studyGuideInfoCopy = new ArrayList<>();

    Path filePath = Paths.get("condensed_lecture-002.md");
    try {
      fileScanner = new Scanner(filePath);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
    while (fileScanner.hasNext()) {
      String s = fileScanner.nextLine();
      studyGuideInfoCopy.add(s);
    }
    fileScanner.close();
    assertEquals(studyGuideInfoCopy, studyGuideInfo);

  }

  /**
   * Tests that given a bath path an error is thrown
   */
  @Test
  public void testInvalidPath() {
    ReadMd reader = new ReadMd(badFiles);
    try {
      reader.read();
    } catch (Exception e) {
      assertThrows(FileNotFoundException.class, () -> reader.read());
    }
  }

  /***Test that the questions from a md file are properly seperated into their own collection
   */
  @Test
  public void testGetSrInfo() {
    ReadMd reader = new ReadMd(files);
    try {
      reader.read();
    } catch (Exception e) {
      fail();
    }
    ArrayList<String> questions = new ArrayList<>();
    String s = "Am i under arrest?|Yes you are, what a shame!|Hard";
    questions.add(s);
    assertEquals(reader.getSrInfo(), questions);
  }
}