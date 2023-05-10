package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

  @FXML private Button assignWorkerButton;
  @FXML private Label projectName;
  @FXML private Label taskName;
  @FXML private TextArea taskDescription;

  @FXML private TableView<TasksTable> taskTable;
  @FXML public TableColumn<TasksTable, String> delete;
  @FXML public TableColumn<TasksTable, Button> edit;
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
  private ObservableList<TasksTable> taskTables;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (TasksViewModel) viewModel;
    this.viewModel.load();
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
    //delete.setCellValueFactory(new PropertyValueFactory<>("button"));
    //taskTable.setItems(this.viewModel.getAll());
    //worker table
    name.setCellValueFactory(
        cellData -> cellData.getValue().getNameProperty());
    number.setCellValueFactory(
        cellData -> cellData.getValue().getNumberProperty());
    workersTable.setItems(((TasksViewModel) viewModel).getWorkersTables());

    PropertyValueFactory<TasksTable, Button> button = new PropertyValueFactory("btton");
    edit.setCellValueFactory(button);
    edit.setStyle("-fx-alignment: CENTER;");

    assignWorkerButton.setVisible(false);
    this.viewModel.isTaskSelectedProperty().addListener((observable, oldValue, newValue) -> {
      assignWorkerButton.setVisible(newValue);
    });

    taskTables = FXCollections.observableArrayList();
    for (int i = 0; i < this.viewModel.getTasks().size(); i++) {
      taskTables.add(new TasksTable(this.viewModel.getTasks().get(i)));
      Button button1 = new Button("");
      button1.setId("button-edit");
      Long index = (long) i;
      button1.setOnAction(e -> {
        taskButtonTableClick(index);
        viewHandler.openView("editTask");
      });
      taskTables.get(i).setBtton(button1);
    }
    taskTable.setItems(taskTables);

  }
  public void taskButtonTableClick(Long index){
    taskTable.getSelectionModel().select(index.intValue());
    taskTableClick();
  }
  public void taskTableClick() {
    if (taskTable.getSelectionModel().getSelectedItem() != null) {
      viewModel.chooseTask(
          taskTable.getSelectionModel().getSelectedItem().getId());
      workersTable.setVisible(true);
      commentsTable.setVisible(true);
    }
  }


  @Override public Region getRoot()
  {
    return root;
  }

  @FXML public void assignWorker(){
    viewHandler.openView("assignWorkersToTask");
  }

  public void openProjects(MouseEvent mouseEvent)
  {
    viewHandler.openView("projects");
  }

  public void addNewTask()
  {
    viewHandler.openView("addTask");
  }
  public void editTask()
  {
    viewHandler.openView("editTask");
  }

  public void openWorkersView()
  {
    viewHandler.openView("workers");
  }
}
