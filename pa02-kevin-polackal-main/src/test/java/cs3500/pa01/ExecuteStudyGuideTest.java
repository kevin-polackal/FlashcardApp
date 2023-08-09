package cs3500.pa01;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExecuteStudyGuideTest {

  private String[] args;

  @BeforeEach
  public void setup() {
    args = new String[3];
  }
  //The following will assume that valid arguments are given for the sake of testing the rest of
  //the class and the fact that ValidateArguments has been properly tested

  @Test
  public void testProperRunWithFileName() {
    ReaderImpl reader = null;
    args[0] = "src/test/resources/StudyMaterials";
    args[1] = "filename";
    args[2] = "src/studyGuide.md";
    try {
      ExecuteStudyGuide.createFiles(args);
      reader = new ReaderImpl(new FileReader("src/studyGuide.md"));
    } catch (IOException e) {
      fail();
    }
    reader.read();
    String secondLine = reader.read();
    assertEquals(secondLine, "- An **array** is a collection of variables of the same type");

    reader = null;
    args[0] = "src/test/resources/StudyMaterials";
    args[1] = "created";
    args[2] = "src/studyGuide.md";
    try {
      ExecuteStudyGuide.createFiles(args);
      reader = new ReaderImpl(new FileReader("src/studyGuide.md"));
    } catch (IOException e) {
      fail();
    }

    reader = null;
    args[0] = "src/test/resources/StudyMaterials";
    args[1] = "modified";
    args[2] = "src/studyGuide.md";
    try {
      ExecuteStudyGuide.createFiles(args);
      reader = new ReaderImpl(new FileReader("src/studyGuide.md"));
    } catch (IOException e) {
      fail();
    }
  }

  private void fail() {
  }
}