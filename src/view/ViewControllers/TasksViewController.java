package view.ViewControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import model.EmployeeRole;
import model.Tag;
import model.Task;
import view.ViewController;
import view.ViewHandler;
import viewmodel.*;
import viewmodel.TaskView.CommentsTable;
import viewmodel.TaskView.TasksTable;
import viewmodel.TaskView.TasksViewModel;
import viewmodel.TaskView.WorkersTable;

public class TasksViewController extends ViewControllerWithNavigationMenu
{
    @FXML
    private Label employeeName;
    @FXML
    private Label employeeWorkingNumber;

    @FXML
    private HBox taskHBox;
    @FXML
    public ImageView avatarPic;
    @FXML
    private Button assignWorkerButton;
    @FXML
    private Button addButton;
    @FXML
    private Label projectName;
    @FXML
    private Label taskName;
    @FXML
    private TextArea taskDescription;

    @FXML
    private TableView<TasksTable> taskTable;
    @FXML
    public TableColumn<TasksTable, String> delete;
    @FXML
    public TableColumn<TasksTable, Button> edit;
    @FXML
    private TableColumn<TasksTable, String> title;
    @FXML
    private TableColumn<TasksTable, String> deadline;
    @FXML
    private TableColumn<TasksTable, String> priority;
    @FXML
    private TableColumn<TasksTable, Button> status;
    @FXML private HBox hBoxForTags;
    @FXML private Button deleteTags;
    @FXML
    private TableView<WorkersTable> workersTable;
    @FXML
    private TableColumn<WorkersTable, String> number;
    @FXML
    private TableColumn<WorkersTable, String> name;
    @FXML
    private TableColumn<WorkersTable, String> position;

    private Region root;
    private TasksViewModel viewModel;
    private ObservableList<TasksTable> taskTables;
    @FXML private ImageView bellImage;

    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel,
                     Region root) {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (TasksViewModel) viewModel;
        this.viewModel.load();
        super.init(this.viewModel, viewHandler, bellImage, avatarPic, employeeName, employeeWorkingNumber, taskHBox);

        bind();

        workersTable.setVisible(false);

        setWindow(this.viewModel.getEmployee().getRole());

        assignWorkerButton.setVisible(false);

        hBoxForTags.setVisible(false);

        this.viewModel.isTaskSelectedProperty().addListener(((observable, oldValue, newValue) -> {
            if (((TasksViewModel) viewModel).getEmployee().getRole().equals(EmployeeRole.PROJECT_MANAGER)) {
                assignWorkerButton.setVisible(newValue);
                hBoxForTags.setVisible(newValue);
                fillInTags();
            }
        }));

        taskTables = FXCollections.observableArrayList();

        setTaskTable();
        fillInTasksTable();
    }

    private void bind(){
        projectName.textProperty().bind(this.viewModel.projectNameProperty());
        taskName.textProperty().bind(this.viewModel.taskNameProperty());
        taskDescription.textProperty()
                .bind(this.viewModel.taskDescriptionProperty());

    }
    private void setTaskTable(){
        // task table
        title.setCellValueFactory(
                cellData -> cellData.getValue().getTitleProperty());
        deadline.setCellValueFactory(
                cellData -> cellData.getValue().getDeadlineProperty());
        priority.setCellValueFactory(
                cellData -> cellData.getValue().getPriorityProperty());

        name.setCellValueFactory(
                cellData -> cellData.getValue().getNameProperty());
        number.setCellValueFactory(
                cellData -> cellData.getValue().getNumberProperty());
        workersTable.setItems(((TasksViewModel) viewModel).getWorkersTables());

        PropertyValueFactory<TasksTable, Button> button = new PropertyValueFactory("btton");
        edit.setCellValueFactory(button);
        edit.setStyle("-fx-alignment: CENTER;");

        PropertyValueFactory<TasksTable, Button> statusButton = new PropertyValueFactory("statusButton");
        status.setCellValueFactory(statusButton);
        status.setStyle("-fx-alignment: CENTER;");

        taskTable.setItems(taskTables);
    }

    private void fillInTasksTable() {
        taskTables.clear();
        for (int i = 0; i < this.viewModel.getTasks().size(); i++) {
            Task task=this.viewModel.getTasks().get(i);
            taskTables.add(new TasksTable(task));
            Button button1 = new Button("");
            Button statusButton = createStatusButton(this.viewModel.getTasks().get(i));
            button1.setId("button-edit");
            button1.setOnAction(e -> {
                taskButtonTableClick(task.getId());
                viewHandler.openView("editTask");
            });
            taskTables.get(i).setBtton(button1);
            taskTables.get(i).setStatusButton(statusButton);
        }
        System.out.println(taskTables.size());

    }

    private void fillInTags()
    {
        hBoxForTags.getChildren().clear();
        for (int i = 0; i < viewModel.getTagList().size(); i++)
        {
            Tag tag = viewModel.getTagList().get(i);
            Label label = new Label(tag.getName());
            styleTags(label, tag);
            hBoxForTags.getChildren().add(label);
        }
    }

    private void styleTags(Label label, Tag tag){
        label.setId("newTags");
        String colorString = tag.getColor();
        Color color = Color.web(colorString);
        String borderColor= color.darker().toString().replace("0x", "#");
        if(color.getBrightness()<0.7){
            label.setStyle("-fx-background-color: " + colorString + ";"
                +"-fx-border-color: " + borderColor + ";"
                + "-fx-text-fill: white;");
        }
        else label.setStyle("-fx-background-color: " + colorString + ";"
            +"-fx-border-color: " + borderColor + ";");
    }

    private Button createStatusButton(Task task) {
        Button statusButton = new Button(task.getStatus());
        switch (task.getStatus()) {
            case "TO DO" -> statusButton.setId("button-todo");
            case "IN PROGRESS" -> statusButton.setId("button-in-progress");
            case "DONE" -> statusButton.setId("button-done");
        }
        statusButton.setOnAction(e -> {
            changeStatus(statusButton, task);
        });

        return statusButton;
    }

    public void taskButtonTableClick(Long index) {
        viewModel.chooseTask(index);
    }

    public void taskTableClick() {
        if (taskTable.getSelectionModel().getSelectedItem() != null) {
            viewModel.chooseTask(taskTable.getSelectionModel().getSelectedItem().getId());
            fillInTags();
            workersTable.setVisible(true);
        }
    }

    public void setWindow(EmployeeRole employeeRole) {
        super.setWindow(employeeRole);
        switch (employeeRole) {
            case HR -> {
                edit.setVisible(false);
                addButton.setVisible(false);
                deleteTags.setVisible(false);

            }
            case MAIN_MANAGER -> {
                edit.setVisible(false);
                addButton.setVisible(false);
                deleteTags.setVisible(false);

            }
            case PROJECT_MANAGER -> {
                edit.setVisible(true);
                addButton.setVisible(true);
                deleteTags.setVisible(true);

            }
            case WORKER -> {
                edit.setVisible(false);
                addButton.setVisible(false);
                deleteTags.setVisible(false);

            }
        }
    }

    public void changeStatus(Button statusButton, Task task) {

        switch (statusButton.getId()) {
            case "button-todo" -> {

                if (viewModel.changeStatus("IN PROGRESS", task)) {
                    statusButton.setId("button-in-progress");
                    statusButton.setText("IN PROGRESS");
                }
            }
            case "button-in-progress" -> {
                if (viewModel.changeStatus("DONE", task)) {
                    statusButton.setId("button-done");
                    statusButton.setText("DONE");
                }
            }
            case "button-done" -> {
                if (viewModel.changeStatus("TO DO", task)) {
                    statusButton.setId("button-todo");
                    statusButton.setText("TO DO");
                }
            }
        }
    }

    @Override
    public Region getRoot() {
        return root;
    }

    @Override
    public void reset() {
        viewModel.reset();
        fillInTasksTable();
        setWindow(viewModel.getEmployee().getRole());
    }

    @FXML
    public void assignWorker() {
        viewHandler.openView("assignWorkersToTask");
    }

    public void openProjects(MouseEvent mouseEvent) {
        viewHandler.openView("projects");
    }

    public void addNewTask() {
        viewHandler.openView("addTask");
    }

    public void openDeleteTags(){
        viewHandler.openView("deleteTags");
    }


}
