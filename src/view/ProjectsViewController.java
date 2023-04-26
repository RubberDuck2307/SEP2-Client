package view;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewmodel.ProjectsViewModel;
import viewmodel.TasksViewModel;
import viewmodel.ViewModel;

import java.time.LocalDate;

public class ProjectsViewController implements ViewController
{
  public TableColumn<ProjectsViewModel, Void> delete;
  public TableColumn edit;
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
  private ProjectsViewModel viewModel;
  private ViewHandler viewHandler;

  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
                             Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (ProjectsViewModel) viewModel;
    this.projectsListTable = new TableView<>();
    /*titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitleProperty());
    deadlineColumn.setCellValueFactory(cellData -> cellData.getValue().getDeadlineProperty());
    managerColumn.setCellValueFactory(cellData -> cellData.getValue().getMangerNameProperty());

    projectEmployeeNameColumn.setCellValueFactory(cellData -> cellData.getValue().getEmployeeNameProperty());
    projectEmployeePositionColumn.setCellValueFactory(cellData -> cellData.getValue().getEmployeePositionProperty());*/

    delete.setCellFactory(column -> {
      return new TableCell<ProjectsViewModel, Void>() {
        private final Button button = new Button("Click me");

        {
          // set the button's action
          button.setOnAction(event -> {

            // handle button click with customer object
          });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
          super.updateItem(item, empty);
          if (empty) {
            setGraphic(null);
          } else {
            setGraphic(button);
          }
        }
      };
    });

    projectsListTable.getColumns().add(delete);


    //projectsListTable.setItems();
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
