package cs3500.pa01;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 *  To read headings and important lines from a list of markdown files
 *  to generate a list of study guide text
 */
public class ReadMd {

  private final ArrayList<String> includeInStudyGuide = new ArrayList<>();
  private final ArrayList<MarkdownFile> markdownFiles;
  private final ArrayList<String> questionsfromMarkdown = new ArrayList<>();

  private ReaderImpl reader;

  ReadMd(ArrayList<MarkdownFile> mdFiles) {

    this.markdownFiles = mdFiles;
  }

  /**
   *  To read headings and important lines from markdown files and
   *  accumulate them in a list of text to be used to create a study guide
   *
   * @return A String arraylist of important headings and text
   * @throws  IOException if an I/O error occurs
   */
  public ArrayList<String> read() throws IOException {
    for (MarkdownFile md : markdownFiles) {
      Path filePath = md.getPath();
      reader = new ReaderImpl(new FileReader(filePath.toString()));
      while (reader.hasNext()) {
        String next = reader.read();
        if (!(addIfHeading(next))) {
          addIfImportantLine(next);
        }
      }
    }
    return this.includeInStudyGuide;
  }

  /**
   *  To determine if a certain line is a heading and if so add it to the list
   *  of study guide text
   *
   *  @param next the current line of text from a markdown file being examined
   */
  private boolean addIfHeading(String next) {
    if (next.startsWith("#")) {
      if (next.contains("# ")) {
        if (next.substring(0, next.indexOf("# ")).equals("#".repeat(next.indexOf("# ")))) {
          includeInStudyGuide.add(next);
          return true;
        }
      }
    }
    return false;
  }

  /**
   *  To determine if a certain line is an important line and if so add it to the list
   *  of study guide text. If it is a question it is seperated into the list of questions
   *
   *  @param next the current line of text from a markdown file being examined
   */
  private void addIfImportantLine(String next) {
    while (next.contains("[[")) {
      //if both brackets are on the same line
      if (next.contains("]]")) {
        StringBuilder toAdd = new StringBuilder();
        toAdd.append(next.substring(next.indexOf("[[") + 2, next.indexOf("]]")));
        if (toAdd.toString().contains(":::")) {
          questionsfromMarkdown.add(splitUp(toAdd.toString()));
        } else {
          includeInStudyGuide.add("- " + toAdd.toString());
        }
        next = next.substring(next.indexOf("]]") + 1);
      } else {  //if the opening bracket is on this line but the closing is not
        StringBuilder toAdd = new StringBuilder();
        toAdd.append(next.substring(next.indexOf("[[") + 2) + " ");
        boolean spansMultipleLines = true;
        while (spansMultipleLines) {
          next = reader.read();
          if (next.contains("]]")) {
            toAdd.append(next.substring(0, next.indexOf("]]")));
            next = next.substring(next.indexOf("]]") + 1);
            spansMultipleLines = false;
          } else { //if this is a line that has neither brackets but is part of an important line
            toAdd.append(next  + " ");
          }
        }
        if (toAdd.toString().contains(":::")) {
          questionsfromMarkdown.add(splitUp(toAdd.toString()));
        } else {
          includeInStudyGuide.add("- " + toAdd.toString());
        }
      }
    }
  }

  /**
   *  Takes a question as represented in a markdown file and converts it to the desired
   *  sr file format;
   *
   *  @param s the MarkDown format of a question
   *
   *  @return the sr format of a question
   */
  private String splitUp(String s) {
    String[] parts = s.split(":::");
    return parts[0].strip() + "|" + parts[1].strip() + "|Hard";
  }

  /**
   * Gets the list of content to be included in a study guide
   *
   *  @return the list of study guide content
   */
  public ArrayList<String> getIncludeInStudyGuide() {
    return this.includeInStudyGuide;
  }

  /**
   * Gets the list of content to be included in the sr file
   *
   *  @return the list of questions from the md file, in sr format
   */
  public ArrayList<String> getSrInfo() {
    return this.questionsfromMarkdown;
  }
}
