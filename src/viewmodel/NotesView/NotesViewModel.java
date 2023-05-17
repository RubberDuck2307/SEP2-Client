package viewmodel.NotesView;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import model.*;
import view.NoteVBOXController;
import viewmodel.ViewModel;
import viewmodel.ViewState;
import java.io.IOException;
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
  private SimpleObjectProperty<String> creationDate;
  private ViewState viewState;
  private NoteList noteList;
  private Model model;
  private VBox noteCellVBox;
  private VBox notesListVbox;

  public NotesViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.employee = new SimpleObjectProperty<>();
    this.avatarPic = new SimpleObjectProperty<>();
    employeeProperty = new SimpleObjectProperty<>();
    userName = new SimpleStringProperty();
    userNumber = new SimpleStringProperty();

    this.titleProperty = new SimpleStringProperty();
    this.noteTextProperty = new SimpleStringProperty();
    this.creationDate = new SimpleObjectProperty<>();
    noteList = new NoteList();
    //noteCellVBox = new VBox();
    noteCellVBox = loadNoteVBox();
    notesListVbox = new VBox();
    refreshNoteListVBox();
  }
  public void load() {
    Employee user = model.getUser();
    if (user != null) {
      employee.setValue(user);
      setAvatarPicture();
      //noteCellVBox = loadNoteVBox();
      //notesListVbox.getChildren().add(loadNoteVBox());
      notesListVbox = loadNoteVBox();

      employeeProperty.set(user);
      userName.set(user.getName());
      userNumber.set(String.valueOf(user.getWorkingNumber()));
      refreshNoteListVBox();
      } else {
        System.out.println("User is not available. Unable to retrieve notes.");
      }
    }


  public VBox loadNoteVBox() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NoteVBOX.fxml"));
      noteCellVBox = loader.load();

      // Get the FXML controller and set the necessary data in the note VBox
      NoteVBOXController controller = loader.getController();

      LocalDate dateValue = LocalDate.of(2023, 5, 15);
      Note note = new Note(14L, "", "", dateValue);
      controller.setNoteData(note);

      // Load the list of notes from the database
      Employee user = model.getUser();
      if (user != null) {
        int workingNumber = user.getWorkingNumber();
        NoteList savedNoteList = model.getAllNotesSavedByEmployee(workingNumber);

        System.out.println("Notes list loaded from database");

        if (savedNoteList != null) {
          ArrayList<Note> noteArrayList = new ArrayList<>(savedNoteList.getAllNotes());
          noteList = new NoteList(noteArrayList);

          System.out.println("Existing notes list gotten from database");

          // Add the existing notes to the VBox
          List<Node> noteCells = noteCellVBox.getChildren();
          for (int i = 0; i < noteList.size(); i++) {
            Note savedNote = noteList.get(i);
            VBox savedNoteVBox = createNoteVBOX(savedNote);
            noteCells.add(savedNoteVBox);

            System.out.println("Existing notes were inserted into VBOXES");
          }
        } else {
          noteList = new NoteList();

          System.out.println("A new notes list created");
        }

        System.out.println("NotesListVBOX was returned");
        return notesListVbox;
      } else {
        System.out.println("User is not available. Unable to retrieve notes.");
        return null;
      }
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private VBox createNoteVBOX(Note note)
  {
    try
    {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NoteVBOX.fxml"));
      VBox noteCellVBox = loader.load();

      System.out.println("NotesVBOX.fxml was loaded");

      // Get the FXML controller and set the necessary data in the note cell VBox
      NoteVBOXController controller = loader.getController();
      controller.setNoteData(note);

      System.out.println("NoteCellVBox(NotesVBOX.fxml) created");
      return noteCellVBox;

    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  public void reset()
  {
    notesListVbox.getChildren().clear(); // Clear the notesListVbox
    //model.setUser(model.getUser()); // Reset the current user in the model
   // notesListVbox.getChildren().clear();
    loadNoteVBox();
    refreshNoteListVBox();
    load();
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

  public NoteList getNoteList()
  {
    return noteList;
  }

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

  private void refreshNoteListVBox() {
    notesListVbox.getChildren().clear();
    for (Note note : noteList.getAllNotes()) {
      NoteCell cell = new NoteCell(note);
      notesListVbox.getChildren().add(cell.getNoteVBox());
    }
  }
}
