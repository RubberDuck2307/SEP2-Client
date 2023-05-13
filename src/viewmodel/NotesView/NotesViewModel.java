package viewmodel.NotesView;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.*;
import viewmodel.ViewModel;
import viewmodel.ViewState;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class NotesViewModel implements ViewModel
{
  private StringProperty titleProperty;
  private StringProperty noteTextProperty;
  private SimpleObjectProperty<LocalDate> creationDate;
  private ViewState viewState;
  private NoteList noteList;
  private Model model;
  private ListCell<Note> noteCell;

  public NotesViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    titleProperty = new SimpleStringProperty();
    noteTextProperty = new SimpleStringProperty();
    creationDate = new SimpleObjectProperty<>();
    noteList = new NoteList();
    ListCell<Note> noteCell = new ListCell<>();
  }

  public void load()
  {
    int workingNumber = viewState.getEmployee().getWorkingNumber();

    try {
      NoteList savedNoteList = model.getAllNotesSavedByEmployee(workingNumber);
      if (savedNoteList != null) {
        ArrayList<Note> noteArrayList = new ArrayList<>(savedNoteList.getAllNotes());
        noteList = new NoteList(noteArrayList);
      } else {
        noteList = new NoteList();
      }
    } catch (Exception e) {
      // message
    }
  }

  public StringProperty getTitleProperty()
  {
    return titleProperty;
  }

  public StringProperty getNoteTextProperty()
  {
    return noteTextProperty;
  }

  public SimpleObjectProperty<LocalDate> getCreationDate()
  {
    return creationDate;
  }

  public ViewState getViewState()
  {
    return viewState;
  }

  public Model getModel()
  {
    return model;
  }

  public NoteList getNoteList()
  {
    return noteList;
  }

  public ListCell<Note> getNoteCell() {
    return noteCell;
  }

  public Callback<ListView<Note>, ListCell<Note>> getNoteCellFactory() {
    return new Callback<>()
    {
      @Override
      public ListCell<Note> call(ListView<Note> listview)
      {
        return getNoteCell();
      }
    };
  }
}
