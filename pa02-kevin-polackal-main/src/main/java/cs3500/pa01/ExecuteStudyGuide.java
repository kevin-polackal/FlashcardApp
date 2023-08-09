package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/***To process the user wanting to create a study guide and .sr file
 */
public class ExecuteStudyGuide {

  /***After validity is checked, drives the .md study guide and .sr creation
   *
   * @param args - the command line arguments
   *
   * @throws IOException - if the data is invalid
   */
  public static void createFiles(String[] args) throws IOException {
    ValidateArguments.validate(args);
    OrderingFlag flag;
    if (args[1].equalsIgnoreCase(OrderingFlag.FILENAME.name)) {
      flag = OrderingFlag.FILENAME;
    } else if (args[1].equalsIgnoreCase(OrderingFlag.CREATED.name)) {
      flag = OrderingFlag.CREATED;
    } else {
      flag = OrderingFlag.MODIFIED;
    }
    MarkdownFileWalker visitor = new MarkdownFileWalker();
    Files.walkFileTree(Paths.get(args[0]), visitor);
    ArrayList<MarkdownFile> sortedFiles = visitor.getMdFiles();
    sortedFiles.sort(flag.comp);

    ReadMd fileReader = new ReadMd(sortedFiles);
    ArrayList<String> studyGuideNotes = fileReader.read();
    ArrayList<Question> questions = GenerationQuestions.generateData(fileReader.getSrInfo());

    Write.writeToFile(studyGuideNotes, args[2]);
    String pathString = args[2].substring(0, args[2].indexOf(".md")) + ".sr";
    Write.writeToFile(questions, pathString);
  }
}
