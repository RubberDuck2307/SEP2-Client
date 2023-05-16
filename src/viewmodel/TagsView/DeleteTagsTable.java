package viewmodel.TagsView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BackgroundFill;
import model.Tag;

public class DeleteTagsTable {

    private Label tag;
    private Long id;

    private Button delete;

    public DeleteTagsTable(Tag tag) {
        setTag(tag);
        this.id = tag.getId();

    }

    public void setTag(Tag tag){
        this.tag = new Label(tag.getName());
        this.tag.setId("tag-table");
        this.tag.setMinWidth(100);
        this.tag.setStyle("-fx-background-color: "+tag.getColor());
    }

    public void setDeleteButton(Button button){
        this.delete = button;
    }

    public Button getDelete(){
        return delete;
    }


    public Label getTag() {
        return tag;
    }
}

