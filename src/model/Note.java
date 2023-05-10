package model;

import java.time.LocalDate;

public class Note
{
  private Long id;
  private String title;
  private String noteText;
  private LocalDate creationDate;

  public Note(Long id, String title, String noteText, LocalDate creationDate)
  {
    this.id = id;
    this.title = title;
    this.noteText = noteText;
    this.creationDate = creationDate;
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getNoteText()
  {
    return noteText;
  }

  public void setNoteText(String noteText)
  {
    this.noteText = noteText;
  }

  public LocalDate getCreationDate()
  {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate)
  {
    this.creationDate = creationDate;
  }

  @Override
  public String toString() {
    return "Note{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", noteText='" + noteText + '\'' +
        ", creationDate='" + creationDate +
        '}';
  }
}
