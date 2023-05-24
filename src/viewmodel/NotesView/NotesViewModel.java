package viewmodel.NotesView;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.*;
import viewmodel.ViewModelWithNavigationMenu;
import viewmodel.ViewState;
import java.beans.PropertyChangeEvent;
import java.time.LocalDate;

public class NotesViewModel extends ViewModelWithNavigationMenu
{
  private StringProperty titleProperty;
  private StringProperty noteTextProperty;
  private SimpleObjectProperty<String> creationDate;
  private NoteList noteList;
  private ViewState viewState;

  public NotesViewModel(Model model, ViewState viewState)
  {
    super(model);
    this.viewState = viewState;
    titleProperty = new SimpleStringProperty();
    noteTextProperty = new SimpleStringProperty();
    creationDate = new SimpleObjectProperty<>();
    this.noteList = new NoteList();}
  public void reset() {
   super.reset();
   load();
  }
  public void load() {
    super.load();
    this.noteList = model.getAllNotesSavedByEmployee(model.getUser().getWorkingNumber());
    }

  public NoteList getNoteList()
  {
    return noteList;
  }

  public StringProperty getTitleProperty()
  {
    return titleProperty;
  }

  public StringProperty getNoteTextProperty()
  {
    return noteTextProperty;
  }

  public SimpleObjectProperty<String> getCreationDate()
  {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate)
  {
    if(creationDate == null)
    {
      this.creationDate.set("");
    }
    else
    {
      this.creationDate.set(creationDate.toString());
    }
  }

  public ViewState getViewState()
  {
    return viewState;
  }

  public Model getModel()
  {
    return model;
  }
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    super.propertyChange(evt);
  }
}
