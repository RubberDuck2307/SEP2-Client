package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.Note;
import viewmodel.NotesView.NotesViewModel;
import viewmodel.ViewModel;

public class NoteVBOXController implements ViewController
{
  @FXML
  private Label creationDate;
  @FXML
  private Label title;
  @FXML
  private VBox noteContainer;
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
    title.textProperty().bindBidirectional(((NotesViewModel) viewModel).getTitleProperty());
    creationDate.textProperty().bindBidirectional(((NotesViewModel) viewModel).getCreationDate());
    noteText.textProperty().bindBidirectional(((NotesViewModel) viewModel).getNoteTextProperty());
  }

  public void setNoteData(Note note)
  {
    this.title.setText(note.getTitle());
    this.creationDate.setText(note.getCreationDate().toString());
    this.noteText.setText(note.getNoteText());
  }
  public VBox getNoteContainer()
  {
    return noteContainer;
  }

  public VBox getNotesListVBox() {
    return (VBox) noteContainer.getParent();
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

  @Override public Region getRoot()
  {
    return root;
  }

  @Override public void reset()
  {
    viewModel.reset();
  }


}
