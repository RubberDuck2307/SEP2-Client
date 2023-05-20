package view.ViewControllers;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import model.Tag;
import view.ViewController;
import view.ViewHandler;
import viewmodel.TagsView.DeleteTagsTable;
import viewmodel.TagsView.DeleteTagsViewModel;
import viewmodel.ViewModel;
import viewmodel.WorkersWithCheckboxTable;

public class DeleteTagsViewController extends ViewControllerWithNavigationMenu
{

    private ViewHandler viewHandler;
    private DeleteTagsViewModel viewModel;
    private Region root;
    private ObservableList<DeleteTagsTable> tagsTables;
    @FXML
    private TableView<DeleteTagsTable> tagsTable;
    @FXML
    private TableColumn<DeleteTagsTable, Label> tagColumn;
    @FXML
    private TableColumn<DeleteTagsTable, Button> deleteColumn;
    @FXML
    private Label nameL;
    @FXML Label workingNumberL;

    @FXML
    private ImageView avatarPic;

    @FXML
    private HBox projectHbox;
    @FXML
    private ImageView bellImage;

    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = (DeleteTagsViewModel) viewModel;
        this.root = root;
        this.viewModel.load();

        super.init(this.viewModel, viewHandler, bellImage, avatarPic, nameL, workingNumberL, projectHbox);
        PropertyValueFactory<DeleteTagsTable, Label> tagLabel = new PropertyValueFactory<>("tag");
        tagColumn.setCellValueFactory(tagLabel);

        PropertyValueFactory<DeleteTagsTable, Button> deleteButton = new PropertyValueFactory<>("delete");
        deleteColumn.setCellValueFactory(deleteButton);
        tagsTable.getSelectionModel().setCellSelectionEnabled(false);


        tagsTables = FXCollections.observableArrayList();
        fillInTable();

        tagsTable.setItems(tagsTables);
        super.setWindow(this.viewModel.getEmployee().getRole());
    }

    private void fillInTable() {

        tagsTables.clear();
        for (int i = 0; i < viewModel.getAllTags().size(); i++) {
            tagsTables.add(new DeleteTagsTable(viewModel.getAllTags().get(i)));
            Button button = new Button();
            button.setId("delete-button");
            button.setStyle("-fx-alignment: CENTER;");

            int finalI = i;
            button.setOnAction(event -> delete(viewModel.getAllTags().get(finalI)));
            tagsTables.get(i).setDeleteButton(button);
        }
    }



    @FXML
    private void backButtonClick() {
        viewHandler.openView("tasks");
    }


    @Override
    public Region getRoot() {
        return root;
    }

    @Override
    public void reset() {
        viewModel.reset();
        fillInTable();
        super.setWindow(this.viewModel.getEmployee().getRole());
    }

    private void delete(Tag tag){
    viewModel.deleteTag(tag);
    reset();
    }
}
