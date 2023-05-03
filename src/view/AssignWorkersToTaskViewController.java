package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Employee;
import viewmodel.AssignWorkersToTaskViewModel;
import viewmodel.ViewModel;
import viewmodel.WorkersWithCheckboxTable;

public class AssignWorkersToTaskViewController implements ViewController
{

  @FXML private TableView<WorkersWithCheckboxTable> workersTable;
  @FXML private TableColumn<WorkersWithCheckboxTable, String> numberColumn;
  @FXML private TableColumn<WorkersWithCheckboxTable, String> nameColumn;
  @FXML private Label projectName;
  @FXML public TableColumn<WorkersWithCheckboxTable, CheckBox> checkboxColumn;
  private Region root;
  private AssignWorkersToTaskViewModel viewModel;
  private ViewHandler viewHandler;

  @Override
  public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root) {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (AssignWorkersToTaskViewModel) viewModel;
    this.viewModel.load();

    projectName.textProperty()
        .bindBidirectional(this.viewModel.getProjectName());


    nameColumn.setCellValueFactory(
        cellData -> cellData.getValue().getNameProperty());
    numberColumn.setCellValueFactory(
        cellData -> cellData.getValue().getNumberProperty());
    workersTable.setItems(this.viewModel.getWorkersWithCheckboxTableObservableList());

    PropertyValueFactory<WorkersWithCheckboxTable, CheckBox> checkbox = new PropertyValueFactory("checkbox"
    );
    checkboxColumn.setCellValueFactory(checkbox);
    checkboxColumn.setStyle("-fx-alignment: CENTER;");



    ObservableList<WorkersWithCheckboxTable> workerTable = FXCollections.observableArrayList();
    for (int i = 0; i < this.viewModel.getEmployees().size(); i++) {
      Employee employee=this.viewModel.getEmployees().get(i);
      workerTable.add(new WorkersWithCheckboxTable(employee));
      CheckBox checkBox = new CheckBox(" ");
      checkBox.setId("checklist");
      checkBox.setOnAction(e -> {
        checkAssignee(employee);
      });
      workerTable.get(i).setCheckbox(checkBox);
    }

    workersTable.setItems(workerTable);

    //    //TODO There are two solutions for one problem -> choose better one ask STEFFEN...
    //    for (int i = 0; i < this.viewModel.getWorkerObservableList().size(); i++) {
    //      Button button1 = new Button(" ");
    //      button1.setId("showTasks");
    //      button1.setOnAction(e -> {
    //        projectTableClick();
    //        viewHandler.openView("tasks");
    //      });
    //      ((ProjectsViewModel) viewModel).getProjectsObservableList().get(i).setBtton(button1);
    //    }
  }

  public void checkAssignee(Employee employee)
  {
    viewModel.checkAsignee(employee);
  }
  @FXML public void openProjects(){
    viewHandler.openView("projects");
  }

  @FXML public void assignWorkers(){
    viewModel.assignWorkers();
  }

  @Override public Region getRoot()
  {
    return root;
  }

  @Override public void reset()
  {

  }

}
