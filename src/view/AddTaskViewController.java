package view;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import model.Priority;
import model.Task;
import utility.StringIntegerConverter;
import viewmodel.AddTaskViewModel;
import viewmodel.TasksViewModel;
import viewmodel.ViewModel;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AddTaskViewController implements ViewController
{
  @FXML public Button backButton;
  @FXML public Label nameOfTheProject;
  @FXML public TextField title;
  @FXML public Label errorTitleMessage;
  @FXML public DatePicker deadline;
  @FXML public TextArea description;
  @FXML public ChoiceBox priority;
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
    errorTitleHours.setText(null);
    this.viewModel.load();
  }

  @Override public Region getRoot()
  {
    return root;
  }
  public void bindEverything(){

    nameOfTheProject.textProperty().bindBidirectional(this.viewModel.getNameOfTheProject());
    errorTitleMessage.textProperty().bindBidirectional(this.viewModel.errorTitleMessageProperty());
    errorTitleHours.textProperty().bindBidirectional(this.viewModel.errorTitleHoursProperty());
    // not needed
    title.textProperty().bindBidirectional(this.viewModel.titleProperty());
    estimatedHours.textProperty().bindBidirectional(this.viewModel.estimatedHoursProperty());
    deadline.valueProperty().bindBidirectional(this.viewModel.deadlineProperty());
    //Bindings.bindBidirectional(estimatedHours.textProperty(),((AddTaskViewModel) viewModel).estimatedHoursProperty(), new StringIntegerConverter(0));
    description.textProperty().bindBidirectional(this.viewModel.descriptionProperty());
    tags.textProperty().bindBidirectional(this.viewModel.tagsProperty());
  }

  @Override public void reset()
  {
    viewModel.load();
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
    (viewModel).add();
    viewHandler.openView("projects");
  }
  public void setChoiceBox(){
    priority.getItems().add(Priority.HIGH);
    priority.getItems().add(Priority.MEDIUM);
    priority.getItems().add(Priority.LOW);
    priority.setValue(Priority.HIGH);
  }

}
