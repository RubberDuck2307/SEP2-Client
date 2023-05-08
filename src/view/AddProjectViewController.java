package view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.AddProjectView.AddProjectViewModel;
import viewmodel.ViewModel;

public class AddProjectViewController implements ViewController
{
  @FXML private TextField title;
  @FXML private Label titleE;
  @FXML private DatePicker deadline;
  @FXML private Label deadlineE;
  @FXML private TextArea description;
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
    titleE.textProperty().bind(this.viewModel.getTitleErrorProperty());
    deadlineE.textProperty().bind(this.viewModel.getDeadlineErrorProperty());
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML public void createButtonPressed()
  {
    if(viewModel.addProject()){
      viewHandler.openView("projects");
    }
  }
  @FXML public void backButtonPressed()
  {
    viewHandler.openView("projects");
  }



}
