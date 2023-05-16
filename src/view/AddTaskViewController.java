package view;

//import com.sun.javafx.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Employee;
import model.EmployeeRole;
import model.Tag;
import viewmodel.AddTaskViewModel;
import viewmodel.ViewModel;
import viewmodel.WorkersWithCheckboxTable;

import java.util.ArrayList;

public class AddTaskViewController implements ViewController
{
  @FXML private Button backButton;
  @FXML private Label nameOfTheProject;
  @FXML private TextField title;
  @FXML private Label errorDeadlineMessage;
  @FXML private ImageView avatarPic;
  @FXML private Label errorPriorityMessage;
  @FXML private Label errorTitleMessage;
  @FXML private DatePicker deadline;
  @FXML private TextArea description;
  @FXML private ChoiceBox priority;
  @FXML private TextField estimatedHours;
  @FXML private TextField tags;
  @FXML private HBox hBoxForTags;
  @FXML private Button createTaskButton;
  @FXML private TableView<WorkersWithCheckboxTable> workersTable;
  @FXML private TableColumn<WorkersWithCheckboxTable, String> numberColumn;
  @FXML private TableColumn<WorkersWithCheckboxTable, String> nameColumn;
  @FXML private TableColumn<WorkersWithCheckboxTable, CheckBox> checkBoxColumn;
  @FXML private Button addTag;
  @FXML private ColorPicker colorPicker;
  @FXML private Label tagsE;
  @FXML private Label errorTitleHours;
  @FXML private Label nameL;
  @FXML private Label workingNumberL;
  private Region root;
  private AddTaskViewModel viewModel;
  private ViewHandler viewHandler;
  private ArrayList<CheckBox> checkboxes;
  private ObservableList<WorkersWithCheckboxTable> workersTableList = FXCollections.observableArrayList();
  private int hoursAsInteger;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (AddTaskViewModel) viewModel;
    this.viewModel.load();
    checkboxes= new ArrayList<>();
    setChoiceBox();
    fillInTags();
    bindEverything();

    numberColumn.setCellValueFactory(
        cellData -> cellData.getValue().getNumberProperty());
    nameColumn.setCellValueFactory(
        cellData -> cellData.getValue().getNameProperty());
    PropertyValueFactory<WorkersWithCheckboxTable, CheckBox> checkbox = new PropertyValueFactory(
        "checkbox");

    checkBoxColumn.setCellValueFactory(checkbox);
    checkBoxColumn.setStyle("-fx-alignment: CENTER;");

    this.viewModel.load();
    fillInWorkerTable();
    workersTable.setItems(workersTableList);
    errorTitleHours.setText(null);

    nameL.textProperty().bindBidirectional(this.viewModel.nameProperty());
    workingNumberL.textProperty()
        .bindBidirectional(this.viewModel.workingNumberProperty());
    avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());
  }

  @Override public Region getRoot()
  {
    return root;
  }

  @Override
  public void reset() {
    viewModel.reset();
    fillInTags();
    fillInWorkerTable();
    priority.setValue("");
    hBoxForTags.getChildren().addAll(checkboxes);
  }

  public void bindEverything()
  {
    colorPicker.valueProperty()
        .bindBidirectional(this.viewModel.colorProperty());
    nameOfTheProject.textProperty()
        .bindBidirectional(this.viewModel.getNameOfTheProject());
    errorTitleMessage.textProperty()
        .bindBidirectional(this.viewModel.errorTitleMessageProperty());
    errorDeadlineMessage.textProperty()
        .bindBidirectional(this.viewModel.errorDeadlineMessageProperty());
    errorTitleHours.textProperty()
        .bindBidirectional(this.viewModel.errorTitleHoursProperty());
    errorPriorityMessage.textProperty()
        .bindBidirectional(this.viewModel.errorPriorityMessageProperty());
    title.textProperty().bindBidirectional(this.viewModel.titleProperty());
    estimatedHours.textProperty()
        .bindBidirectional(this.viewModel.estimatedHoursProperty());
    deadline.valueProperty()
        .bindBidirectional(this.viewModel.deadlineProperty());
    description.textProperty()
        .bindBidirectional(this.viewModel.descriptionProperty());
    tags.textProperty().bindBidirectional(this.viewModel.tagsProperty());
    priority.valueProperty()
        .bindBidirectional(this.viewModel.priorityProperty());
    tagsE.textProperty().bindBidirectional(this.viewModel.tagsEProperty());
  }

  public void addTag()
  {
    if(viewModel.addTag())
    {
      fillInTags();
    }
    //reset tags \/\/\/
    tags.setText("");
  }

  private void styleTags(CheckBox checkBox, Tag tag){
    checkBox.setId("newTags");
    String colorString = tag.getColor();
    Color color = Color.web(colorString);
    String borderColor= color.darker().toString().replace("0x", "#");
    if(color.getBrightness()<0.7){
      checkBox.setStyle("-fx-background-color: " + colorString + ";"
          +"-fx-border-color: " + borderColor + ";"
          + "-fx-text-fill: white;");
    }
    else checkBox.setStyle("-fx-background-color: " + colorString + ";"
        +"-fx-border-color: " + borderColor + ";");
  }

  private void fillInTags()
  {
    hBoxForTags.getChildren().clear();
    for (int i = 0; i < viewModel.getTagList().size(); i++)
    {
      Tag tag = viewModel.getTagList().get(i);
      CheckBox checkBox = new CheckBox(tag.getName());
      styleTags(checkBox, tag);
      hBoxForTags.getChildren().add(checkBox);
      checkBox.setOnAction(e -> {
        switchTag(tag);
      });
      checkBox.setSelected(false);
    }
  }

  private void fillInWorkerTable()
  {
    workersTableList.clear();
    for (int i = 0; i < viewModel.getWorkers().size(); i++)
    {
      Employee employee = viewModel.getWorkers().get(i);
      workersTableList.add(new WorkersWithCheckboxTable(employee));
      CheckBox checkBox = new CheckBox(" ");
      checkBox.setId("checklist");
      checkBox.setOnAction(e -> {
        assignWorker(employee);
      });
      checkBox.setSelected(false);
      workersTableList.get(i).setCheckbox(checkBox);
    }
  }

  private void switchTag(Tag tag)
  {
    viewModel.switchTag(tag);
  }

  private void assignWorker(Employee employee)
  {
    viewModel.assignWorker(employee);
  }

  public void openProjects()
  {
    viewHandler.openView("projects");
  }

  public void createTask()
  {
    if (viewModel.add())
    {
      viewHandler.openView("tasks");
    }

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
  public void backButton()
  {
    viewHandler.openView("tasks");
  }

  public void setChoiceBox()
  {
    priority.getItems().add("HIGH");
    priority.getItems().add("MEDIUM");
    priority.getItems().add("LOW");
    priority.setValue("HIGH");
  }

  public void openWorkersView()
  {
    viewHandler.openView("workers");
  }

}
