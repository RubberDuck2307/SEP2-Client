package view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import model.Employee;
import model.Note;
import model.UserProfile;
import viewmodel.NotesView.NotesViewModel;
import viewmodel.ViewModel;
import viewmodel.NotesView.NoteCell;
import viewmodel.NotesView.NoteCellFactory;




public class NotesViewController implements ViewController
{
  @FXML private ListView<Note> notesList;
  private ViewHandler viewHandler;
  private ObservableList<Note> notes;
  private Region root;
  private NotesViewModel viewModel;

  public void init(ViewHandler viewHandler, ViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = (NotesViewModel)viewModel;
    this.root = root;

    notesList.setCellFactory(((NotesViewModel) viewModel).getNoteCellFactory());
    ((NotesViewModel) viewModel).load();

    notes = ((NotesViewModel) viewModel).getNoteList().getAllNotes();
    notesList.setItems(notes);
  }

  @Override public Region getRoot()
  {
    return root;
  }

}
