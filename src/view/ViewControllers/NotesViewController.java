package view.ViewControllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.EmployeeRole;
import model.Note;
import view.ViewHandler;
import viewmodel.NotesView.NotesViewModel;
import viewmodel.ViewModel;

public class NotesViewController extends ViewControllerWithNavigationMenu
{
  @FXML
  public ImageView avatarPic;
  @FXML
  private Label nameLabel;
  @FXML
  private Label numberLabel;
  @FXML
  private VBox notesListVBox;
  @FXML
  private Button backButton;
  @FXML
  private Button addNoteButton;
  @FXML
  private ImageView bellImage;
  @FXML
  private HBox projectsHbox;

  private ViewHandler viewHandler;
  private ObservableList<Note> notes;
  private Region root;
  private NotesViewModel viewModel;
  @Override
  public void init(ViewHandler viewHandler, ViewModel viewModel, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (NotesViewModel)viewModel;
    this.viewModel.load();

    super.init(this.viewModel, viewHandler, bellImage, avatarPic, nameLabel, numberLabel, projectsHbox);
    //avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());
    //nameLabel.textProperty().bindBidirectional(this.viewModel.userNameProperty());
    //numberLabel.textProperty().bindBidirectional(this.viewModel.userNumberProperty());

    setNotes();
  }

  public void setNotes()
  {
    notesListVBox.getChildren().clear();
    VBox notesInVBOX = this.viewModel.loadNotesInVBOX();
    notesListVBox.getChildren().addAll(notesInVBOX.getChildren());
  }

  @Override public Region getRoot()
  {
    return root;
  }
  @Override public void reset()
  {
    viewModel.reset();
    setNotes();
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
