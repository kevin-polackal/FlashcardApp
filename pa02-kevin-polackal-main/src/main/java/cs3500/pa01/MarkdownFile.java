package cs3500.pa01;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

/**
 * To represent a MarkdownFile and its certain atrributes, specifically: its file-path, the name
 * of the file, its creation time, and its last modified time
 */
public class MarkdownFile {
  private final Path path;
  private final String fileName;
  private final FileTime creationTime;
  private final FileTime lastModified;

  MarkdownFile(Path path, String fileName, FileTime creationTime, FileTime lastModified) {
    this.path = path;
    this.fileName = fileName;
    this.creationTime = creationTime;
    this.lastModified = lastModified;
  }

  /**
   * To get the path of this MarkdownFile
   *
   * @return file-path of this file
   */
  public Path getPath() {
    return this.path;
  }

  /**
   * To get the name of this MarkdownFile
   *
   * @return name of this file
   */
  public String getName() {
    return this.fileName;
  }

  /**
   * To get the creation time of this MarkdownFile
   *
   * @return creation time of this file
   */
  public FileTime getCreationTime() {
    return this.creationTime;
  }

  /**
   * To get the last-modified time of this MarkdownFile
   *
   * @return last-modified time of this file
   */
  public FileTime getLastModified() {
    return this.lastModified;
  }




}
