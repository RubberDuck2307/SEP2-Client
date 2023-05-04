package view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Project;
import viewmodel.AddProjectView.AddProjectViewModel;
import viewmodel.ViewModel;

public class AddProjectViewController implements ViewController
{
  @FXML private TextField title;

  @FXML private DatePicker deadline;
  @FXML private TextArea description;
  @FXML private Label errorLabel;
  private Region root;
  private AddProjectViewModel viewModel;
  private ViewHandler viewHandler;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (AddProjectViewModel) viewModel;

    title.textProperty().bindBidirectional(this.viewModel.getTitleProperty());
    description.textProperty().bindBidirectional(this.viewModel.getDescriptionProperty());
    deadline.valueProperty().bindBidirectional(this.viewModel.getDeadlineProperty());
    errorLabel.textProperty().bind(this.viewModel.getErrorProperty());
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

    viewModel.createButtonPressed();
    //viewModel.add(vinyl)
    //viewHandler.openView("projects")
  }
  @FXML public void backButtonPressed()
  {
    viewHandler.openView("projects");
  }



}
