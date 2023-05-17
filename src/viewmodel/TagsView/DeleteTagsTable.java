package viewmodel.TagsView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
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
        styleTags(this.tag, tag);
//        this.tag.setId("tag-table");
//        this.tag.setMinWidth(100);
//        this.tag.setStyle("-fx-background-color: "+tag.getColor());
    }


    private void styleTags(Label label, Tag tag){
        label.setId("newTags");
        String colorString = tag.getColor();
        Color color = Color.web(colorString);
        String borderColor= color.darker().toString().replace("0x", "#");
        if(color.getBrightness()<0.7){
            label.setStyle("-fx-background-color: " + colorString + ";"
                +"-fx-border-color: " + borderColor + ";"
                + "-fx-text-fill: white;");
        }
        else label.setStyle("-fx-background-color: " + colorString + ";"
            +"-fx-border-color: " + borderColor + ";");
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

