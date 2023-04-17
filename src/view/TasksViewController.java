package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.*;

import java.time.LocalDate;

public class TasksViewController implements ViewController
{
  @FXML private Label projectName;
  @FXML private Label taskName;
  @FXML private TextArea taskDescription;

  @FXML private TableView<TasksTable> taskTable;
  @FXML private TableColumn<TasksTable, String> title;
  @FXML private TableColumn<TasksTable, LocalDate> deadline;
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
    projectName.textProperty().bind(this.viewModel.projectNameProperty());
    taskName.textProperty().bind(this.viewModel.taskNameProperty());
    taskDescription.textProperty().bind(this.viewModel.taskDescriptionProperty());

  }

  @Override public Region getRoot()
  {
    return root;
  }




  @Override public void reset()
  {

  }
}
