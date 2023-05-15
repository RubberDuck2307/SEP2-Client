package viewmodel.NotesView;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.Note;

public class NoteCellFactory implements Callback<ListView<Note>, ListCell<Note>> {
  private NotesViewModel viewModel;

  public NoteCellFactory(NotesViewModel viewModel) {
    this.viewModel = viewModel;
  }

  //@Override
 // public ListCell<Note> call(ListView<Note> param) {
  ///  return new NoteCell();
  //}
 // public static NoteCellFactory getInstance(NotesViewModel viewModel) {
   // return new NoteCellFactory(viewModel);
  //}

  @Override
  public ListCell<Note> call(ListView<Note> param) {
    return new ListCell<>() {
      @Override
      protected void updateItem(Note note, boolean empty) {
        super.updateItem(note, empty);
        if (empty || note == null) {
          setText(null);
          setGraphic(null);
        } else {
          setGraphic(new NoteCell(note).getNoteVBox());
        }
      }
    };
  }

}

