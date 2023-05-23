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
import view.ViewControllers.NoteVBOXController;
import viewmodel.ViewModelWithNavigationMenu;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotesViewModel extends ViewModelWithNavigationMenu
{
  private ObjectProperty<Image> avatarPic;
  private ObjectProperty<Employee> employee;
  private StringProperty userName;
  private StringProperty userNumber;
  private ObjectProperty<Employee> employeeProperty;
  private StringProperty titleProperty;
  private StringProperty noteTextProperty;
  private SimpleObjectProperty<String> creationDate;
  private NoteList noteList;
  private ViewState viewState;
  private VBox notesListVBox;

  public NotesViewModel(Model model, ViewState viewState)
  {
    super(model);
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
    notesListVBox = new VBox();
  }
  public void reset() {
   super.reset();
   load();
  }
  public void load() {
    super.load();
    Employee user = model.getUser();
    if (user != null) {
      employee.setValue(user);
      setAvatarPicture();
      employeeProperty.set(user);
      userName.set(user.getName());
      userNumber.set(String.valueOf(user.getWorkingNumber()));
      notesListVBox.getChildren().clear();
      notesListVBox = loadNotesInVBOX();
      }
    else {
      System.out.println("User is not available. Unable to retrieve notes.");
      }
    }

  public VBox loadNotesInVBOX() {
    try {
      notesListVBox.getChildren().clear();
      NoteList savedNotes = model.getAllNotesSavedByEmployee(employee.get().getWorkingNumber());

      System.out.println("Notes list loaded from database");

      if (savedNotes != null) {
        ArrayList<Note> noteArrayList = new ArrayList<>(savedNotes.getAllNotes());
        noteList = new NoteList(noteArrayList);

        System.out.println("Existing notes list gotten from database");

        // Add the existing notes to the VBox
        List<Node> noteCells = notesListVBox.getChildren();
        for (int i = 0; i < noteList.size(); i++) {
          Note savedNote = noteList.get(i);
          VBox savedNoteVBox = createNoteVBOX(savedNote);
          noteCells.add(savedNoteVBox);

          System.out.println("Existing notes inserted into cells");
        }
      }
      System.out.println("NotesListVBOX was returned");
      return notesListVBox;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private VBox createNoteVBOX(Note note)
  {
    try
    {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(
          "/view/FXML/NoteVBOX.fxml"));
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

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    super.propertyChange(evt);
  }
}
