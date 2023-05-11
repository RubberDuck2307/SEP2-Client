package viewmodel.NotesView;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import model.Note;

public class NoteCell implements Callback<ListView<Note>, ListCell<Note>> {
  @Override
  public ListCell<Note> call(ListView<Note> listView) {
    return new ListCell<>() {
      private final VBox content;
      private final Label title;
      private final ScrollPane noteScrollPane;
      private final TextArea noteText;
      private final Label date;

      {
        // Init UI components
        content = new VBox();
        title = new Label();
        noteScrollPane = new ScrollPane();
        noteText = new TextArea();
        date = new Label();

        // Set up the layout of the cell
        noteScrollPane.setContent(noteText);
        noteScrollPane.setPrefHeight(100);
        noteScrollPane.setFitToWidth(true);
        content.getChildren().addAll(title, noteScrollPane, date);
        content.setSpacing(5);
        noteText.setEditable(false);
        noteText.setWrapText(true);
      }

      @Override
      protected void updateItem(Note note, boolean empty) {
        super.updateItem(note, empty);

        if (empty || note == null) {
          setText(null);
          setGraphic(null);
        } else {
          // Set the text of the title and note text fields based on the Note object
          title.setText(note.getTitle());
          date.setText(note.getCreationDate().toString());
          noteText.setText(note.getNoteText());
          setGraphic(content);
        }
      }
    };
  }
}
