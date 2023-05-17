package view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.EmployeeRole;
import model.Note;
import viewmodel.NotesView.NotesViewModel;
import viewmodel.ViewModel;

public class NotesViewController implements ViewController
{
  @FXML
  public ImageView avatarPic;
  @FXML
  private Label nameLabel;
  @FXML
  private Label numberLabel;
  @FXML
  private VBox notesListVBox;
//  @FXML
  //private TextArea noteText;
//  @FXML
 // private Label creationDate;
//  @FXML
 // private Label title;
  @FXML
  private Button backButton;

  @FXML private Button addNoteButton;
  private ViewHandler viewHandler;
  private ObservableList<Note> notes;
  private Region root;
  private NotesViewModel viewModel;
  //private NoteVBOXController noteVBOXController;

  public void init(ViewHandler viewHandler, ViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = (NotesViewModel)viewModel;
    this.root = root;

    avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());
    nameLabel.textProperty().bindBidirectional(this.viewModel.userNameProperty());
    numberLabel.textProperty().bindBidirectional(this.viewModel.userNumberProperty());

    //FXMLLoader loader = new FXMLLoader();
    //loader.setLocation(getClass().getResource("/view/NoteVBOX.fxml"));

    this.viewModel.load();

    notesListVBox.getChildren().clear(); // Clear previous note containers

    System.out.println("NotesListVBOX cleared");

    VBox noteCellVBox = this.viewModel.loadNoteVBox();

    if (noteCellVBox != null)
    {
      notesListVBox.getChildren().addAll(noteCellVBox.getChildren());
    }

    //notesListVBox = noteVBOXController.getNotesListVBox();
    //notesListVBox.getChildren().add(((NotesViewModel) viewModel).loadNoteVBox());

   // NoteVBOXController noteVBOXController = new NoteVBOXController();

    //Label title = noteVBOXController.getTitle();
    //Label creationDate = noteVBOXController.getCreationDate();
    //TextArea noteText = noteVBOXController.getNoteText();

    //title.textProperty().bindBidirectional(((NotesViewModel) viewModel).getTitleProperty());
    //creationDate.textProperty().bindBidirectional(((NotesViewModel) viewModel).getCreationDate());
    //noteText.textProperty().bindBidirectional(((NotesViewModel) viewModel).getNoteTextProperty());
    }

    //notesList.setCellFactory(((NotesViewModel) viewModel).getNoteCellFactory());
    //((NotesViewModel) viewModel).load();

   // notes = ((NotesViewModel) viewModel).getNoteList().getAllNotes();
    //notesList.setItems(notes);
    //fillNoteCells();

  //private void fillNoteCells() {
   // notesListVBox.getChildren().clear();
    //VBox noteCellVBox = viewModel.getNoteCellVBox();
   // if (noteCellVBox != null) {
  //    notesListVBox.getChildren().addAll(noteCellVBox.getChildren());
  //  }
 // }

  @Override public Region getRoot()
  {
    return root;
  }
  @Override public void reset()
  {
    viewModel.reset();
  }

  //public void addNoteButtonClick(){viewHandler.openView("addNote");}

  public void backButtonClick(){
    viewHandler.openLastWindow();
  }
  public void openWorkersView() {
    viewHandler.openView("workers");
  }

  public void openProjects() {
    viewHandler.openView("projects");
  }
  public void openHome()
  {
    EmployeeRole role = this.viewModel.getEmployeeProperty().getRole();
    switch (role) {
      case WORKER -> {
        viewHandler.openView("workerHomePage");
      }
      case HR -> {
        viewHandler.openView("home");
      }
      case PROJECT_MANAGER -> {
        viewHandler.openView("home");
      }
      case MAIN_MANAGER -> {
        viewHandler.openView("home");
      }
    }
  }
}
