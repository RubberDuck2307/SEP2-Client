package viewmodel.NotesView;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Note;

public class NoteCell {
  private final VBox noteVBox;
  private final Label titleLabel;
  private final Label dateLabel;
  private final TextArea contentArea;

  public NoteCell(Note note) {
    noteVBox = new VBox();
    noteVBox.getStyleClass().add("note-container");

    HBox titleBox = new HBox();
    titleBox.getStyleClass().add("note-title-box");
    titleLabel = new Label(note.getTitle());
    titleLabel.getStyleClass().add("note-title-label");
    dateLabel = new Label(note.getCreationDate().toString());
    dateLabel.getStyleClass().add("note-date-label");
    titleBox.getChildren().addAll(titleLabel, dateLabel);

    contentArea = new TextArea(note.getNoteText());
    contentArea.getStyleClass().add("note-content-area");
    contentArea.setEditable(false);
    contentArea.setWrapText(true);

    noteVBox.getChildren().addAll(titleBox, contentArea);
  }

  public VBox getNoteVBox() {
    return noteVBox;
  }
}
