package viewmodel;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.time.LocalDate;

public class NotesViewModel implements ViewModel
{
  private StringProperty titleProperty;
  private StringProperty noteTextProperty;
  private SimpleObjectProperty<LocalDate> creationDate;
  private ViewState viewState;
  private Model model;
  public NotesViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.titleProperty = new SimpleStringProperty();
    this.noteTextProperty = new SimpleStringProperty();
    this.creationDate = new SimpleObjectProperty<>();
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
}
