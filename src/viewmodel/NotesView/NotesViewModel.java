package viewmodel.NotesView;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import model.*;
import viewmodel.ViewModel;
import viewmodel.ViewState;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotesViewModel implements ViewModel
{
  private ObjectProperty<Image> avatarPic;
  private ObjectProperty<Employee> employee;
  private StringProperty userName;
  private StringProperty userNumber;
  private ObjectProperty<Employee> employeeProperty;
  private StringProperty titleProperty;
  private StringProperty noteTextProperty;
  private SimpleObjectProperty<LocalDate> creationDate;
  private ViewState viewState;
  private NoteList noteList;
  private Model model;
  private VBox noteCellVBox;

  public NotesViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.employee = new SimpleObjectProperty<>();
    this.avatarPic = new SimpleObjectProperty<>();
    employeeProperty = new SimpleObjectProperty<>();
    userName = new SimpleStringProperty();
    userNumber = new SimpleStringProperty();

    titleProperty = new SimpleStringProperty();
    noteTextProperty = new SimpleStringProperty();
    creationDate = new SimpleObjectProperty<>();
    noteList = new NoteList();
    noteCellVBox = new VBox();
    //noteList.addListener(getNoteListChangeListener());
    //refreshNoteCellVBox();
  }

  public void load()
  {
    employee.setValue(model.getUser());
    setAvatarPicture();

    //int workingNumber = viewState.getEmployee().getWorkingNumber();  // supposed to be used to get the notes of the employee with that working number

    try {
      int workingNumber = model.getUser().getWorkingNumber();

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
    employeeProperty.set(model.getUser());
    userName.set(model.getUser().getName());
    userNumber.set(model.getUser().getWorkingNumber().toString());

  }
  public StringProperty userNumberProperty() {
    return userNumber;
  }
  public String getUserNumber() {
    return userNumber.get();
  }
  public StringProperty userNameProperty() {
    return userName;
  }
  public String getUserName() {
    return userName.get();
  }
  public ObjectProperty<Image> avatarPicProperty()
  {
    return avatarPic;
  }

  public boolean isWoman(){
    return Objects.equals(employee.getValue().getGender(), "F");
  }
  public void setAvatarPicture(){
    if(isWoman()){
      avatarPic.setValue(new Image("/icons/woman-avatar.png"));
    }
    else{
      avatarPic.setValue(new Image("/icons/man-avatar.png"));
    }
  }
  public Employee getEmployeeProperty() {
    return employeeProperty.get();
  }

  public ObjectProperty<Employee> employeePropertyProperty() {
    return employeeProperty;
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

  //public Callback<ListView<Note>, ListCell<Note>> getNoteCellFactory() {
   // return new NoteCellFactory(this);
//  }

  public List<NoteCell> getNoteCells() {
    List<NoteCell> noteCells = new ArrayList<>();
    for (Note note : noteList.getAllNotes()) {
      noteCells.add(new NoteCell(note));
    }
    return noteCells;
  }
  public VBox getNoteCellVBox() {
    return noteCellVBox;
  }

  private ListChangeListener<Note> getNoteListChangeListener() {
    return change -> refreshNoteCellVBox();
  }

  private void refreshNoteCellVBox() {
    noteCellVBox.getChildren().clear();
    for (Note note : noteList.getAllNotes()) {
      NoteCell cell = new NoteCell(note);
      noteCellVBox.getChildren().add(cell.getNoteVBox());
    }
  }

  public void reset()
  {
    refreshNoteCellVBox();
    load();
  }
}
