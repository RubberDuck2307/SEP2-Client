package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class NoteList
{
  private ObservableList<Note> notesList;

  public NoteList(ArrayList<Note> notesList)
  {
    this.notesList = FXCollections.observableArrayList(notesList);
  }
  public NoteList()
  {
    this.notesList = FXCollections.observableArrayList();
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

  public ObservableList<Note> getAllNotes() {
    return notesList;
  }

  public NoteList addAll(NoteList otherList) {
    notesList.addAll(otherList.getAllNotes());
    return this;
  }
  public void clear() {
    notesList.clear();
  }
  @Override
  public String toString() {
    return "NotesList{" +
        "notesList=" + notesList +
        '}';
  }
}
