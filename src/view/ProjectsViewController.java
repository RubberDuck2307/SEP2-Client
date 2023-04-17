package view;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewmodel.ProjectsViewModel;
import viewmodel.ViewModel;

import java.time.LocalDate;

public class ProjectsViewController implements ViewController
{
  @FXML
  private TableView<ProjectsViewModel> projectsListTable;
  @FXML
  private TableView<ProjectsViewModel> employeesListTable;

  @FXML private TableColumn<ProjectsViewModel, String>  titleColumn;
  @FXML private TableColumn<ProjectsViewModel, LocalDate> deadlineColumn;
  @FXML private TableColumn<ProjectsViewModel, String> managerColumn;
  @FXML private TableColumn<ProjectsViewModel, String>  projectEmployeeNameColumn;
  @FXML private TableColumn<ProjectsViewModel, String> projectEmployeePositionColumn;
  @FXML private StringProperty error;
  private Region root;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
                             Region root)
  {
    titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitleProperty());
    deadlineColumn.setCellValueFactory(cellData -> cellData.getValue().getDeadlineProperty());
    managerColumn.setCellValueFactory(cellData -> cellData.getValue().getMangerNameProperty());

    projectEmployeeNameColumn.setCellValueFactory(cellData -> cellData.getValue().getEmployeeNameProperty());
    projectEmployeePositionColumn.setCellValueFactory(cellData -> cellData.getValue().getEmployeePositionProperty());

    //projectsListTable.setItems(viewModel.getList());
  }

  @Override public void reset()
  {
    error.setValue("");
  }
  public Region getRoot()
  {
    return root;
  }
}
