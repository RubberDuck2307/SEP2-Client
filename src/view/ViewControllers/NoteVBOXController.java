package view.ViewControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.Note;
import view.ViewHandler;
import viewmodel.NotesView.NotesViewModel;
import viewmodel.ViewModel;

public class NoteVBOXController extends ViewControllerWithNavigationMenu
{
  @FXML
  private HBox titleHBox;
  @FXML
  private Label creationDate;
  @FXML
  private Label title;
  @FXML
  private VBox noteVBox;
  @FXML
  private TextArea noteText;
  private ViewHandler viewHandler;
  private Region root;
  private NotesViewModel viewModel;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = (NotesViewModel) viewModel;
    this.root = root;
    bind();

    noteText.setEditable(false);   // when set to true text will be editable
  }

  private void bind(){
    title.textProperty().bindBidirectional(this.viewModel.getTitleProperty());
    creationDate.textProperty().bindBidirectional(this.viewModel.getCreationDate());
    noteText.textProperty().bindBidirectional(this.viewModel.getNoteTextProperty());
  }


  public void setNoteData(Note note) {
    this.title.setText(note.getTitle());
    this.creationDate.setText(note.getCreationDate().toString());
    this.noteText.setText(note.getNoteText());
  }
  public VBox getNoteVBox()
  {
    return noteVBox;
  }

  public VBox getNotesListVBox() {
    return (VBox) noteVBox.getParent();
  }
  public Label getCreationDate()
  {
    return creationDate;
  }

  public Label getTitle()
  {
    return title;
  }

  public TextArea getNoteText()
  {
    return noteText;
  }
  public HBox getTitleHBox()
  {
    return titleHBox;
  }
  @Override public Region getRoot()
  {
    return root;
  }

  @Override public void reset()
  {
    viewModel.reset();
  }
}
