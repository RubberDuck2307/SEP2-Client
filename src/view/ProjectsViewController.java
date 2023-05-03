package view;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import viewmodel.ProjectView.ProjectManagersTable;
import viewmodel.ProjectView.ProjectsTable;
import viewmodel.ProjectView.ProjectsViewModel;
import viewmodel.TaskView.TasksTable;
import viewmodel.TaskView.WorkersTable;
import viewmodel.ViewModel;

import java.awt.event.InputEvent;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectsViewController implements ViewController {
    public TableColumn<ProjectsTable, Button> delete;
    public TableColumn<ProjectsTable, Button> openTask;
    public TableColumn edit;
    @FXML
    private TableView<ProjectsTable> projectTable;
    @FXML
    private TableColumn<ProjectsTable, String> titleColumn;
    @FXML
    private TableColumn<ProjectsTable, String> deadlineColumn;

    @FXML
    private TableView<ProjectManagersTable> employeesListTable;
    @FXML
    private TableColumn<ProjectManagersTable, String> projectEmployeeNameColumn;
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

        titleLabel.textProperty()
                .bindBidirectional(this.viewModel.getTitleProperty());
        descriptionArea.textProperty()
                .bindBidirectional(this.viewModel.getDescriptionProperty());

        titleColumn.setCellValueFactory(
                cellData -> cellData.getValue().getTitleValue());
        deadlineColumn.setCellValueFactory(
                cellData -> cellData.getValue().deadlineProperty());


        projectEmployeeNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getNameValue());
        employeesListTable.setItems(this.viewModel.getProjectManagersObservableList());

        PropertyValueFactory<ProjectsTable, Button> button = new PropertyValueFactory("btton"
        );
        delete.setCellValueFactory(button);
        delete.setStyle("-fx-alignment: CENTER;");

        ObservableList<ProjectsTable> projectsTables = FXCollections.observableArrayList();
        for (int i = 0; i < this.viewModel.getProjects().size(); i++) {
            projectsTables.add(new ProjectsTable(this.viewModel.getProjects().get(i)));
            Button button1 = new Button(" ");
            button1.setId("showTasks");
            button1.setOnAction(e -> {
                projectTableClick();
                viewHandler.openView("tasks");
            });
            projectsTables.get(i).setBtton(button1);
        }


        //TODO There are two solutions for one problem -> choose better one ask STEFFEN
        for (int i = 0; i < this.viewModel.getProjectsObservableList().size(); i++) {
            Button button1 = new Button(" ");
            button1.setId("showTasks");
            button1.setOnAction(e -> {
                projectTableClick();
                viewHandler.openView("tasks");
            });
            ((ProjectsViewModel) viewModel).getProjectsObservableList().get(i).setBtton(button1);
        }

        projectTable.setItems(projectsTables);

    }

    @Override
    public void reset() {
    }

    // TODO fix the buttons - projectTable.getSelectionModel().getSelectedItem() does not work inside button

    @FXML
    public void projectTableClick() {
        if (projectTable.getSelectionModel().getSelectedItem() != null) {
            viewModel.setProject(
                    projectTable.getSelectionModel().getSelectedItem().getId());
        }
    }

    public Region getRoot() {
        return root;
    }

    public void openTask() {
        viewHandler.openView("tasks");
    }
}
