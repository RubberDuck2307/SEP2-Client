package viewmodel.NotesView;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Note;

public class NoteCell {
  private final VBox noteVBox;
  private final Label title;
  private final Label creationDate;
  private final TextArea noteText;

  public NoteCell(Note note) {
    noteVBox = new VBox();
    noteVBox.getStyleClass().add("note-container");

    HBox titleBox = new HBox();
    titleBox.getStyleClass().add("note-title-box");
    title = new Label(note.getTitle());
    title.getStyleClass().add("note-title-label");
    creationDate = new Label(note.getCreationDate().toString());
    creationDate.getStyleClass().add("note-date-label");
    titleBox.getChildren().addAll(title, creationDate);

    noteText = new TextArea(note.getNoteText());
    noteText.getStyleClass().add("note-text-area");
    noteText.setEditable(false);
    noteText.setWrapText(true);

    noteVBox.getChildren().addAll(titleBox, noteText);
  }

  public VBox getNoteVBox() {
    return noteVBox;
  }
  public void setNoteData()
  {
    title.setText("");
    creationDate.setText("");
    noteText.setText("");
  }
}
