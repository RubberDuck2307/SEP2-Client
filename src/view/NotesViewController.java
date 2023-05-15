package view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.Employee;
import model.Note;
import model.UserProfile;
import viewmodel.NotesView.NotesViewModel;
import viewmodel.ViewModel;
import viewmodel.NotesView.NoteCell;
import viewmodel.NotesView.NoteCellFactory;
public class NotesViewController implements ViewController
{
  @FXML public ImageView avatarPic;
  @FXML
  private Label nameLabel;
  @FXML
  private Label numberLabel;
  @FXML
  private VBox notesVBox;
  @FXML
  private Button backButton;
  private ViewHandler viewHandler;
  private ObservableList<Note> notes;
  private Region root;
  private NotesViewModel viewModel;

  public void init(ViewHandler viewHandler, ViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = (NotesViewModel)viewModel;
    this.root = root;
    avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());
    nameLabel.textProperty().bindBidirectional(this.viewModel.userNameProperty());
    numberLabel.textProperty().bindBidirectional(this.viewModel.userNumberProperty());

    notesVBox = (VBox) root.lookup("#notesVBox");

    //notesList.setCellFactory(((NotesViewModel) viewModel).getNoteCellFactory());
    //((NotesViewModel) viewModel).load();

   // notes = ((NotesViewModel) viewModel).getNoteList().getAllNotes();
    //notesList.setItems(notes);

    this.viewModel.load();

    for (NoteCell noteCell : this.viewModel.getNoteCells()) {
      notesVBox.getChildren().add(noteCell.getNoteVBox());
    }
  }

  @Override public Region getRoot()
  {
    return root;
  }
  public void backButtonClick(){
    viewHandler.openLastWindow();
  }

  @Override public void reset()
  {
    viewModel.reset();
  }
  public void openHome()
  {
    viewHandler.openView("home");
  }
  public void openWorkersView() {
    viewHandler.openView("workers");
  }

  public void openProjects() {
    viewHandler.openView("projects");
  }
}
