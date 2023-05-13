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

  @Override
  public ListCell<Note> call(ListView<Note> param) {
    return viewModel.getNoteCell();
  }
  public static NoteCellFactory getInstance(NotesViewModel viewModel) {
    return new NoteCellFactory(viewModel);
  }


}

