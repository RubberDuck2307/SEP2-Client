package view.ViewControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import model.Employee;
import model.EmployeeRole;
import view.ViewController;
import view.ViewHandler;
import viewmodel.EmployeeView.AssignEmployeesToProjectViewModel;
import viewmodel.ViewModel;
import viewmodel.WorkersWithCheckboxTable;

public class AssignEmployeesToProjectViewController extends ViewControllerWithNavigationMenu {
    @FXML
    private Label headlineLabel;
    @FXML
    public ImageView avatarPic;
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
    private ObservableList<WorkersWithCheckboxTable> workerTableColumns;
    @FXML
    private ImageView bellImage;
    @FXML
    private HBox projectsHbox;

    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel,
                     Region root) {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (AssignEmployeesToProjectViewModel) viewModel;
        this.viewModel.load();
        init(this.viewModel, viewHandler, bellImage, this.avatarPic, this.nameLabel, this.numberLabel, projectsHbox);
        projectName.textProperty()
                .bindBidirectional(this.viewModel.getProjectName());


        setWindow(this.viewModel.getUser().getRole());
        setTable();
        fillInTable();

    }

    private void fillInTable() {
        workerTableColumns.clear();
        for (int i = 0; i < this.viewModel.getEmployeesOfManager().size(); i++) {
            Employee employee = this.viewModel.getEmployeesOfManager().get(i);
            workerTableColumns.add(new WorkersWithCheckboxTable(employee));
            CheckBox checkBox = new CheckBox(" ");
            checkBox.setId("checklist");
            checkBox.setOnAction(e -> {
                assignEmployee(employee);
            });
            checkBox.setSelected(this.viewModel.isAssigned(employee));
            workerTableColumns.get(i).setCheckbox(checkBox);
        }
        //System.out.println(workerTableColumns.size());
    }

    private void setTable() {
        workerTableColumns = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getNameProperty());
        numberColumn.setCellValueFactory(
                cellData -> cellData.getValue().getNumberProperty());

        PropertyValueFactory<WorkersWithCheckboxTable, CheckBox> checkbox = new PropertyValueFactory("checkbox"
        );
        checkboxColumn.setCellValueFactory(checkbox);
        checkboxColumn.setStyle("-fx-alignment: CENTER;");


        workersTable.setItems(workerTableColumns);
    }

    public void assignEmployee(Employee employee) {
        viewModel.assignEmployee(employee);
    }

    @FXML
    public void backButtonClick() {
        viewHandler.openLastWindow();
    }

    protected void setWindow(EmployeeRole employeeRole) {
        super.setWindow(employeeRole);
        if (employeeRole.equals(EmployeeRole.PROJECT_MANAGER))
            headlineLabel.setText("ASSIGN WORKER");
        else if (employeeRole.equals(EmployeeRole.MAIN_MANAGER))
            headlineLabel.setText("ASSIGN PROJECT MANAGER");
    }

    @Override
    public Region getRoot() {
        return root;
    }

    @Override
    public void reset() {
        viewModel.reset();
        fillInTable();
        setWindow(viewModel.getUser().getRole());
    }


}
