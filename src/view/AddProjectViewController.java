package view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.AddProjectView.AddProjectViewModel;


public class AddProjectViewController
{
  @FXML private TextField title;

  @FXML private DatePicker deadline;
  @FXML private TextArea description;
  @FXML private Label errorLabel;
  private Region root;
  private AddProjectViewModel viewModel;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, AddProjectViewModel viewModel, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    title.textProperty().bindBidirectional(viewModel.getTitleProperty());
    description.textProperty().bindBidirectional(viewModel.getDescriptionProperty());
    deadline.valueProperty().bindBidirectional(viewModel.getDeadlineProperty());
    errorLabel.textProperty().bind(viewModel.getErrorProperty());
  }
  public void reset()
  {
    viewModel.reset();
  }
  public Region getRoot()
  {
    return root;
  }

  @FXML public void createButtonPressed()
  {
    // maybe here needs to be some sort of check for the possibly selected project manager while creating project

   //Project project = new Project(title.getText(),deadline.SOMETHING, description.getText())

    //viewModel.add(vinyl)
    //viewHandler.openView("projects")
  }
  @FXML public void backButtonPressed()
  {
    viewHandler.openView("projects");
  }



}
