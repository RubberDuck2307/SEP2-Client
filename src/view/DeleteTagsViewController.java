package view;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Tag;
import viewmodel.TagsView.DeleteTagsTable;
import viewmodel.TagsView.DeleteTagsViewModel;
import viewmodel.ViewModel;
import viewmodel.WorkersWithCheckboxTable;

public class DeleteTagsViewController implements ViewController {

    private ViewHandler viewHandler;
    private DeleteTagsViewModel viewModel;
    private Region root;
    private ObservableList<DeleteTagsTable> tagsTables;
    @FXML
    private TableView<DeleteTagsTable> tagsTable;
    @FXML
    private TableColumn<DeleteTagsTable, Label> tagColumn;
    @FXML
    private TableColumn<DeleteTagsTable, String> numberColumn;
    @FXML
    private TableColumn<DeleteTagsTable, Button> deleteColumn;

    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = (DeleteTagsViewModel) viewModel;
        this.root = root;
        this.viewModel.load();


        PropertyValueFactory<DeleteTagsTable, Label> tagLabel = new PropertyValueFactory("tag");
        tagColumn.setCellValueFactory(tagLabel);

        PropertyValueFactory<DeleteTagsTable, Button> deleteButton = new PropertyValueFactory("delete");
        deleteColumn.setCellValueFactory(deleteButton);
        tagsTable.getSelectionModel().setCellSelectionEnabled(false);


        tagsTables = FXCollections.observableArrayList();
        fillInTable();

        tagsTable.setItems(tagsTables);
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
    private void openProjects() {

    }

    @FXML
    private void openHome() {

    }

    @FXML
    private void openWorkersView() {

    }

    @FXML
    private void backButtonClick() {

    }


    @Override
    public Region getRoot() {
        return root;
    }

    @Override
    public void reset() {
        viewModel.reset();
        fillInTable();
    }

    private void delete(Tag tag){
    viewModel.deleteTag(tag);
    reset();
    }
}
