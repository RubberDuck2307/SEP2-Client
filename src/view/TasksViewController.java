package view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import viewmodel.*;
import viewmodel.TaskView.CommentsTable;
import viewmodel.TaskView.TasksTable;
import viewmodel.TaskView.WorkersTable;

public class TasksViewController implements ViewController
{

  @FXML private Label projectName;
  @FXML private Label taskName;
  @FXML private TextArea taskDescription;

  @FXML private TableView<TasksTable> taskTable;
  @FXML public TableColumn<TasksTable, String> delete;
  @FXML public TableColumn<TasksTable, String> edit;
  @FXML private TableColumn<TasksTable, String> title;
  @FXML private TableColumn<TasksTable, String> deadline;
  @FXML private TableColumn<TasksTable, String> priority;
  @FXML private TableColumn<TasksTable, String> status;
  @FXML private TableColumn<TasksTable, String> tags;

  @FXML private TableView<CommentsTable> commentsTable;
  @FXML private TableColumn<CommentsTable, String> worker;
  @FXML private TableColumn<CommentsTable, String> comment;

  @FXML private TableView<WorkersTable> workersTable;
  @FXML private TableColumn<WorkersTable, String> number;
  @FXML private TableColumn<WorkersTable, String> name;
  @FXML private TableColumn<WorkersTable, String> position;

  private Region root;
  private TasksViewModel viewModel;
  private ViewHandler viewHandler;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (TasksViewModel) viewModel;
    commentsTable.setVisible(false);
    workersTable.setVisible(false);
    projectName.textProperty().bind(this.viewModel.projectNameProperty());
    taskName.textProperty().bind(this.viewModel.taskNameProperty());
    taskDescription.textProperty()
        .bind(this.viewModel.taskDescriptionProperty());

    //buttonColumn.setCellFactory(buttonColumn.forTableColumn());



    // task table
    title.setCellValueFactory(
        cellData -> cellData.getValue().getTitleProperty());
    deadline.setCellValueFactory(
        cellData -> cellData.getValue().getDeadlineProperty());
    priority.setCellValueFactory(
        cellData -> cellData.getValue().getPriorityProperty());
    status.setCellValueFactory(
        cellData -> cellData.getValue().getStatusProperty());
    delete.setCellValueFactory(new PropertyValueFactory<>("button"));
    taskTable.setItems(this.viewModel.getAll());
    //worker table
    name.setCellValueFactory(
        cellData -> cellData.getValue().getNameProperty());
    number.setCellValueFactory(
        cellData -> cellData.getValue().getNumberProperty());
    workersTable.setItems(((TasksViewModel) viewModel).getWorkersTables());

    this.viewModel.load();
  }

  @FXML private void chooseTask()
  {
    if (taskTable.getSelectionModel().getSelectedItem() != null)
    {
      viewModel.chooseTask(taskTable.getSelectionModel().getSelectedItem().getId());
      workersTable.setVisible(true);
      commentsTable.setVisible(true);
    }
  }

  @Override public Region getRoot()
  {
    return root;
  }

  @Override public void reset()
  {
    viewModel.load();
  }

  @FXML public void assignWorker(){
    viewHandler.openView("assignWorkersToTask");
  }

  public void openProjects(MouseEvent mouseEvent)
  {
    viewHandler.openView("projects");
  }

  public void addNewTask(ActionEvent actionEvent)
  {
    viewHandler.openView("addTask");
  }
}
