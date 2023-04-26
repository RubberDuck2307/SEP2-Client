package view;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.ProjectView.ProjectsTable;
import viewmodel.ProjectView.ProjectsViewModel;
import viewmodel.TaskView.WorkersTable;
import viewmodel.ViewModel;

import java.time.LocalDate;

public class ProjectsViewController implements ViewController {
    public TableColumn<ProjectsViewModel, Void> delete;
    public TableColumn edit;
    @FXML
    private TableView<ProjectsTable> projectTable;
    @FXML
    private TableView<ProjectsTable> employeesListTable;

    @FXML
    private TableColumn<ProjectsTable, String> titleColumn;
    @FXML
    private TableColumn<ProjectsTable, String> deadlineColumn;
    @FXML
    private TableColumn<ProjectsTable, String> managerColumn;
    @FXML
    private TableColumn<WorkersTable, String> projectEmployeeNameColumn;
    @FXML
    private TableColumn<WorkersTable, String> projectEmployeePositionColumn;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private Label titleLabel;

    private Region root;
    private ProjectsViewModel viewModel;
    private ViewHandler viewHandler;

    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel,
                     Region root) {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (ProjectsViewModel) viewModel;

        titleLabel.textProperty().bindBidirectional(this.viewModel.getTitleProperty());
        descriptionArea.textProperty().bindBidirectional(this.viewModel.getDescriptionProperty());


        titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitleValue());
        deadlineColumn.setCellValueFactory(cellData -> cellData.getValue().deadlineProperty());
        managerColumn.setCellValueFactory(cellData -> cellData.getValue().getManagerValue());

        projectEmployeeNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        projectTable.setItems(this.viewModel.getProjectsObservableList());


//    delete.setCellFactory(column -> {
//      return new TableCell<>() {
//        private final Button button = new Button("Click me");
//
//        {
//          // set the button's action
//          button.setOnAction(event -> {
//
//            // handle button click with customer object
//          });
//        }
//
//        @Override
//        protected void updateItem(Void item, boolean empty) {
//          super.updateItem(item, empty);
//          if (empty) {
//            setGraphic(null);
//          } else {
//            setGraphic(button);
//          }
//        }
//      };
//    });
//
//    projectsListTable.getColumns().add(delete);
//
//
//    //projectsListTable.setItems();
    }

    @Override
    public void reset() {

    }

    @FXML
    public void projectTableClick() {
        if (projectTable.getSelectionModel().getSelectedItem() != null) {
            viewModel.setProject(projectTable.getSelectionModel().getSelectedItem().getId());
            viewHandler.openView("tasks");
        }
    }

    public Region getRoot() {
        return root;
    }
}
