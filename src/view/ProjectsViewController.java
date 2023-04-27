package view;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import viewmodel.ProjectView.ProjectManagersTable;
import viewmodel.ProjectView.ProjectsTable;
import viewmodel.ProjectView.ProjectsViewModel;
import viewmodel.TaskView.TasksTable;
import viewmodel.TaskView.WorkersTable;
import viewmodel.ViewModel;

import java.time.LocalDate;

public class ProjectsViewController implements ViewController
{
  public TableColumn<ProjectsTable, String> delete;
  public TableColumn edit;
  @FXML private TableView<ProjectsTable> projectTable;
  @FXML private TableColumn<ProjectsTable, String> titleColumn;
  @FXML private TableColumn<ProjectsTable, String> deadlineColumn;

  @FXML private TableView<ProjectManagersTable> employeesListTable;
  @FXML private TableColumn<ProjectManagersTable, String> projectEmployeeNameColumn;
  @FXML private TextArea descriptionArea;
  @FXML private Label titleLabel;

  private Region root;
  private ProjectsViewModel viewModel;
  private ViewHandler viewHandler;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
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

    delete.setCellValueFactory(new PropertyValueFactory<>("button"));
    delete.setStyle("-fx-alignment: CENTER;");
    projectTable.setItems(this.viewModel.getProjectsObservableList());

  }

  @Override public void reset()
  {

  }

  @FXML public void projectTableClick()
  {
    if (projectTable.getSelectionModel().getSelectedItem() != null)
    {
      viewModel.setProject(
          projectTable.getSelectionModel().getSelectedItem().getId());
      viewHandler.openView("tasks");
    }
  }

  public Region getRoot()
  {
    return root;
  }
}
