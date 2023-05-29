package view.ViewControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.Note;
import model.NoteList;
import view.ViewHandler;
import viewmodel.NotesView.NotesViewModel;
import viewmodel.ViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
  private ImageView bellImage;
  @FXML
  private HBox projectsHBox;
  private ViewHandler viewHandler;
  private Region root;
  private NotesViewModel viewModel;

  @Override
  public void init(ViewHandler viewHandler, ViewModel viewModel, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (NotesViewModel)viewModel;
    this.viewModel.load();
    notesListVBox = loadNotesInVBOX();

    super.init(this.viewModel, viewHandler, bellImage, this.avatarPic, this.nameLabel, this.numberLabel, this.projectsHBox);
    super.setWindow(this.viewModel.getEmployee().getRole());
    setNotes();
  }

  public VBox loadNotesInVBOX() {
    try {
      notesListVBox.getChildren().clear();
      //NoteList savedNotes = model.getAllNotesSavedByEmployee(model.getUser().getWorkingNumber());
      NoteList savedNotes = viewModel.getNoteList();


      if (savedNotes != null) {
        ArrayList<Note> noteArrayList = new ArrayList<>(savedNotes.getAllNotes());
        NoteList noteList = new NoteList(noteArrayList);


        // Add the existing notes to the VBox
        List<Node> noteCells = notesListVBox.getChildren();
        for (int i = 0; i < noteList.size(); i++) {
          Note savedNote = noteList.get(i);
          VBox savedNoteVBox = createNoteVBOX(savedNote);
          noteCells.add(savedNoteVBox);

        }
      }
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


      // Get the FXML controller and set the necessary data in the note cell VBox
      NoteVBOXController controller = loader.getController();
      controller.setNoteData(note);

      return noteCellVBox;

    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  public void setNotes()
  {
    notesListVBox.getChildren().clear();
    loadNotesInVBOX();
    notesListVBox.getChildren().addAll();
  }

  @Override public Region getRoot()
  {
    return root;
  }
  @Override public void reset()
  {
    viewModel.reset();
    setNotes();
    super.setWindow(viewModel.getEmployee().getRole());
  }
  public void backButtonClick(){
    viewHandler.openLastWindow();
  }

}
