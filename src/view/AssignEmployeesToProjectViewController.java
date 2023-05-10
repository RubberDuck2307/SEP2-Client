package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Employee;
import model.EmployeeRole;
import viewmodel.AssignEmployeesToProjectViewModel;
import viewmodel.ViewModel;
import viewmodel.WorkersWithCheckboxTable;

public class AssignEmployeesToProjectViewController implements ViewController {
    @FXML
    private Label headlineLabel;
    @FXML
    private TableView<WorkersWithCheckboxTable> workersTable;
    @FXML
    private TableColumn<WorkersWithCheckboxTable, String> numberColumn;
    @FXML
    private TableColumn<WorkersWithCheckboxTable, String> nameColumn;
    @FXML
    private Label projectName;
    @FXML
    private Label nameLabel;
    @FXML
    private Label numberLabel;
    @FXML
    public TableColumn<WorkersWithCheckboxTable, CheckBox> checkboxColumn;
    private Region root;
    private AssignEmployeesToProjectViewModel viewModel;
    private ViewHandler viewHandler;

    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel,
                     Region root) {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (AssignEmployeesToProjectViewModel) viewModel;
        this.viewModel.load();


        nameLabel.textProperty().bindBidirectional(this.viewModel.userNameProperty());
        numberLabel.textProperty().bindBidirectional(this.viewModel.userNumberProperty());
        projectName.textProperty()
                .bindBidirectional(this.viewModel.getProjectName());


        nameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getNameProperty());
        numberColumn.setCellValueFactory(
                cellData -> cellData.getValue().getNumberProperty());

        PropertyValueFactory<WorkersWithCheckboxTable, CheckBox> checkbox = new PropertyValueFactory("checkbox"
        );
        checkboxColumn.setCellValueFactory(checkbox);
        checkboxColumn.setStyle("-fx-alignment: CENTER;");


        ObservableList<WorkersWithCheckboxTable> workerTable = FXCollections.observableArrayList();
        for (int i = 0; i < this.viewModel.getEmployeesOfManager().size(); i++) {
            Employee employee = this.viewModel.getEmployeesOfManager().get(i);
            workerTable.add(new WorkersWithCheckboxTable(employee));
            CheckBox checkBox = new CheckBox(" ");
            checkBox.setId("checklist");
            checkBox.setOnAction(e -> {
                assignEmployee(employee);
            });
            checkBox.setSelected(this.viewModel.isAssigned(employee));
            workerTable.get(i).setCheckbox(checkBox);
        }

        workersTable.setItems(workerTable);

        setWindow(this.viewModel.getUser().getRole());

        this.viewModel.userProperty().addListener((observable, oldValue, newValue) -> {
            setWindow((newValue).getRole());
        });
    }

    public void assignEmployee(Employee employee) {
        viewModel.assignEmployee(employee);
    }

    @FXML
    public void backButtonClick() {
        viewHandler.openLastWindow();
    }

    @FXML
    public void openProjects() {
        viewHandler.openView("projects");
    }

    private void setWindow(EmployeeRole employeeRole) {
        if (employeeRole.equals(EmployeeRole.PROJECT_MANAGER))
            headlineLabel.setText("ASSIGN WORKER");
        else if (employeeRole.equals(EmployeeRole.MAIN_MANAGER))
            headlineLabel.setText("ASSIGN PROJECT MANAGER");
    }

    @Override
    public Region getRoot() {
        return root;
    }

}
