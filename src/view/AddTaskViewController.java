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
    createtask();
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
    Bindings.bindBidirectional(estimatedHours.textProperty(),((AddTaskViewModel) viewModel).estimatedHoursProperty(), new StringIntegerConverter(0));
    description.textProperty().bindBidirectional(this.viewModel.descriptionProperty());
    tags.textProperty().bindBidirectional(this.viewModel.tagsProperty());
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
    createTaskButton.setOnAction(e -> {
      String tags = "";
      for(int i = 0; i<checkboxes.size(); i++){
        if(checkboxes.get(i).isSelected()){
          if(tags.equals("")){
            tags = checkboxes.get(i).getText();
          }
          else{
            tags = tags + ", "+ checkboxes.get(i).getText();
          }
        }
      }

      if (Objects.equals(estimatedHours.getText(), ""))
      {
        errorTitleHours.setText("Hours can not be empty!");
      }
      if (!Objects.equals(estimatedHours.getText(), ""))
      {
        try{
          hoursAsInteger = Integer.parseInt(estimatedHours.getText());
        }
        catch (NumberFormatException ex){
          errorTitleHours.setText("Please insert only numbers");
        }
      }

      if (title.getText() == null)
      {
        errorTitleMessage.setText("Title can not be empty!");
      }
      else {
        Task task = new Task(title.getText(), description.getText(), deadline.getValue(), hoursAsInteger, priority.getValue().toString(), "TO DO", 1L, deadline.getValue());

        (viewModel).add(task);
        System.out.println("Here are the tags: " + tags);
        viewHandler.openView("projects");
      }

    });

  }
  public void setChoiceBox(){
    priority.getItems().add(Priority.HIGH);
    priority.getItems().add(Priority.MEDIUM);
    priority.getItems().add(Priority.LOW);
    priority.setValue(Priority.HIGH);
  }

}
