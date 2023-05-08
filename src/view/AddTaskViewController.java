package view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import model.Priority;
import model.Task;
import utility.StringIntegerConverter;
import viewmodel.AddTaskViewModel;
import viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class AddTaskViewController implements ViewController
{
  @FXML public Button backButton;
  @FXML public Label nameOfTheProject;
  @FXML public TextField title;
  @FXML public Label errorTitleMessage;
  @FXML public DatePicker deadline;
  @FXML public TextArea description;
  @FXML public ChoiceBox<String> priority;
  @FXML public TextField estimatedHours;
  @FXML public TextField tags;
  @FXML public HBox hBoxForTags;
  @FXML public Button createTaskButton;
  //TODO steal from Cosmin
  @FXML public TableView workersTable;
  @FXML public TableColumn tableNumber;
  @FXML public TableColumn tableName;
  @FXML public TableColumn checkButton;
  @FXML public Button assignWorkersButton;
  @FXML public Button addTag;
  @FXML public Label errorTitleHours;
  public Label errorDeadlineMessage;
  public Label errorPriorityMessage;
  private Region root;
  private AddTaskViewModel viewModel;
  private ViewHandler viewHandler;
  private int counter;
  private ArrayList<CheckBox> checkboxes;
  private int hoursAsInteger;
  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (AddTaskViewModel) viewModel;
    this.viewModel.load();
    setChoiceBox();
    this.counter = 0;
    this.checkboxes = new ArrayList<>();
    addTag();
    bindEverything();
    this.viewModel.load();
  }

  @Override public Region getRoot()
  {
    return root;
  }
  public void bindEverything(){
    nameOfTheProject.textProperty().bindBidirectional(this.viewModel.getNameOfTheProject());
    errorTitleMessage.textProperty().bindBidirectional(this.viewModel.errorTitleMessageProperty());
    errorDeadlineMessage.textProperty().bindBidirectional(this.viewModel.errorDeadlineMessageProperty());
    errorTitleHours.textProperty().bindBidirectional(this.viewModel.errorTitleHoursProperty());
    errorPriorityMessage.textProperty().bindBidirectional(this.viewModel.errorPriorityMessageProperty());
    title.textProperty().bindBidirectional(this.viewModel.titleProperty());
    estimatedHours.textProperty().bindBidirectional(this.viewModel.estimatedHoursProperty());
    deadline.valueProperty().bindBidirectional(this.viewModel.deadlineProperty());
    description.textProperty().bindBidirectional(this.viewModel.descriptionProperty());
    tags.textProperty().bindBidirectional(this.viewModel.tagsProperty());
    priority.valueProperty().bindBidirectional(this.viewModel.priorityProperty());
  }

  public void addTag(){
    addTag.setOnAction(e -> {
      if(tags.getText()!=null){

        CheckBox checkBox = new CheckBox(tags.getText());
        checkBox.setSelected(true);
        checkBox.setStyle("-fx-padding: 10px 5px 10px 5px; ");
        checkboxes.add(counter,checkBox);
        //checkBox.setId(counter + "");
        counter++;
        hBoxForTags.getChildren().add(checkBox);
        tags.setText(null);
      }
    });
  }

  public void openProjects()
  {
    viewHandler.openView("projects");
  }
  public void createtask(){
    if(viewModel.add()){
      viewHandler.openView("tasks");
    }

  }
  public void backButton(){
    viewHandler.openView("tasks");
  }
  public void setChoiceBox(){
    priority.getItems().add("HIGH");
    priority.getItems().add("MEDIUM");
    priority.getItems().add("LOW");
    priority.setValue("HIGH");
  }

}
