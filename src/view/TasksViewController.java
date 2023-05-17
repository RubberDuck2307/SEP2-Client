
package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import model.EmployeeRole;
import viewmodel.*;
import viewmodel.TaskView.CommentsTable;
import viewmodel.TaskView.TasksTable;
import viewmodel.TaskView.TasksViewModel;
import viewmodel.TaskView.WorkersTable;

public class TasksViewController implements ViewController
{
  @FXML private Label employeeName;
  @FXML private Label employeeWorkingNumber;

  @FXML private HBox taskHBox;
  @FXML public ImageView avatarPic;
  @FXML private Button assignWorkerButton;
  @FXML private Button addButton;
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
    commentsTable.setVisible(false);
    workersTable.setVisible(false);
    avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());
    employeeName.textProperty().bindBidirectional(this.viewModel.getEmployeeName());
    employeeWorkingNumber.textProperty().bindBidirectional(this.viewModel.getEmployeeWorkingNumber());
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

    this.viewModel.employeeProperty().addListener((observable, oldValue, newValue) -> {
      setWindow(newValue.getRole());
    });
    this.viewModel.load();
    setWindow(this.viewModel.getEmployee().getRole());
    assignWorkerButton.setVisible(false);
    this.viewModel.isTaskSelectedProperty().addListener(((observable, oldValue, newValue) -> {
      if (((TasksViewModel) viewModel).getEmployee().getRole().equals(EmployeeRole.PROJECT_MANAGER)) {
        assignWorkerButton.setVisible(newValue);
      }
    }));

    taskTables = FXCollections.observableArrayList();
    fillInTasksTable();
    taskTable.setItems(taskTables);

  }

  private void fillInTasksTable(){
    taskTables.clear();
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

  public void setWindow(EmployeeRole employeeRole){
    switch (employeeRole){
      case HR -> {
        edit.setVisible(false);
        addButton.setVisible(false);
        taskHBox.setVisible(false);
        taskHBox.setManaged(false);
      }
      case MAIN_MANAGER -> {
        edit.setVisible(false);
        addButton.setVisible(false);
        taskHBox.setVisible(true);
        taskHBox.setManaged(true);
      }
      case PROJECT_MANAGER -> {
        edit.setVisible(true);
        addButton.setVisible(true);
        taskHBox.setVisible(true);
        taskHBox.setManaged(true);
      }
      case WORKER -> {
        edit.setVisible(false);
        addButton.setVisible(false);
        taskHBox.setVisible(true);
        taskHBox.setManaged(true);
      }
    }
  }

  @Override public Region getRoot()
  {
    return root;
  }

  @Override
  public void reset() {
    viewModel.reset();
    fillInTasksTable();
    setWindow(viewModel.getEmployee().getRole());
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
  public void openHome()
  {
    EmployeeRole role = this.viewModel.getEmployeeProperty().getRole();
    switch (role) {
      case WORKER -> {
        viewHandler.openView("workerHomePage");
      }
      case HR -> {
        viewHandler.openView("home");
      }
      case PROJECT_MANAGER -> {
        viewHandler.openView("home");
      }
      case MAIN_MANAGER -> {
        viewHandler.openView("home");
      }
    }
  }
}