package model;

import java.util.ArrayList;

public class NoteList
{
  private ArrayList<Note> notesList;

  public NoteList(ArrayList<Note> notesList)
  {
    this.notesList = notesList;
  }
  public NoteList()
  {
    this.notesList = new ArrayList<>();
  }
  public void addNote(Note note)
  {
    notesList.add(note);
  }
  public void removeNote(Note note)
  {
    notesList.remove(note);
  }
  public int size()
  {
    return notesList.size();
  }
  @Override
  public String toString() {
    return "NotesList{" +
        "notesList=" + notesList +
        '}';
  }
}
