package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.*;
import viewmodel.ProjectView.ProjectsViewModel;
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
    projectName.textProperty().bind(this.viewModel.projectNameProperty());
    taskName.textProperty().bind(this.viewModel.taskNameProperty());
    taskDescription.textProperty()
        .bind(this.viewModel.taskDescriptionProperty());


    // task table
    title.setCellValueFactory(
        cellData -> cellData.getValue().getTitleProperty());
    deadline.setCellValueFactory(
        cellData -> cellData.getValue().getDeadlineProperty());
    priority.setCellValueFactory(
        cellData -> cellData.getValue().getPriorityProperty());
    status.setCellValueFactory(
        cellData -> cellData.getValue().getStatusProperty());
    taskTable.setItems(this.viewModel.getAll());
    //worker table
    name.setCellValueFactory(
        cellData -> cellData.getValue().getNameProperty());
    number.setCellValueFactory(
        cellData -> cellData.getValue().getNumberProperty());
    workersTable.setItems(((TasksViewModel) viewModel).getWorkersTables());

    delete.setCellFactory(column -> {
      new TableCell<ProjectsViewModel, Void>()
      {
        private final Button button = new Button("Click me");

        {
          // set the button's action
          button.setOnAction(event -> {
            // handle button click with customer object
          });
        }

        @Override protected void updateItem(Void item, boolean empty)
        {
          super.updateItem(item, empty);
          if (empty)
          {
            setGraphic(null);
          }
          else
          {
            setGraphic(button);
          }
        }
      };
      return new TableCell<>();
    });
    // TODO buttons add and edit
  }

  @Override public Region getRoot()
  {
    return root;
  }

  @Override public void reset()
  {

  }
}
