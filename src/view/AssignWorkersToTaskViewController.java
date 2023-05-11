package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import model.Employee;
import viewmodel.AssignWorkersToTaskViewModel;
import viewmodel.ViewModel;
import viewmodel.WorkersWithCheckboxTable;

public class AssignWorkersToTaskViewController implements ViewController
{
    
    public Label nameL;
    public Label workingNumberL;
    @FXML public ImageView avatarPic;
    @FXML
    private TableView<WorkersWithCheckboxTable> workersTable;

    @FXML
    private TableColumn<WorkersWithCheckboxTable, String> numberColumn;
    @FXML
    private TableColumn<WorkersWithCheckboxTable, String> nameColumn;
    @FXML
    private Label projectName;
    @FXML
    public TableColumn<WorkersWithCheckboxTable, CheckBox> checkboxColumn;
    private Region root;
    private AssignWorkersToTaskViewModel viewModel;
    private ViewHandler viewHandler;
    
    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root)
    {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (AssignWorkersToTaskViewModel) viewModel;
        this.viewModel.load();
        projectName.textProperty().bindBidirectional(this.viewModel.getProjectName());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().getNumberProperty());
        PropertyValueFactory<WorkersWithCheckboxTable, CheckBox> checkbox = new PropertyValueFactory("checkbox");
        checkboxColumn.setCellValueFactory(checkbox);
        checkboxColumn.setStyle("-fx-alignment: CENTER;");
        avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());
        ObservableList<WorkersWithCheckboxTable> workerTable = FXCollections.observableArrayList();
        for (int i = 0; i < this.viewModel.getEmployeesOfManager().size(); i++)
        {
            Employee employee = this.viewModel.getEmployeesOfManager().get(i);
            workerTable.add(new WorkersWithCheckboxTable(employee));
            CheckBox checkBox = new CheckBox(" ");
            checkBox.setId("checklist");
            checkBox.setOnAction(e ->
            {
                assignEmployee(employee);
            });
            checkBox.setSelected(this.viewModel.isAssigned(employee));
            workerTable.get(i).setCheckbox(checkBox);
        }
        workersTable.setItems(workerTable);
        nameL.textProperty().bindBidirectional(this.viewModel.userNameProperty());
        workingNumberL.textProperty().bindBidirectional(this.viewModel.userNumberProperty());
    }
    
    public void assignEmployee(Employee employee)
    {
        viewModel.assignEmployee(employee);
    }
    @FXML
    public void backButtonClick()
    {
        viewHandler.openLastWindow();
    }
    
    @FXML
    public void openProjects()
    {
        viewHandler.openView("projects");
    }
    
    @Override
    public Region getRoot()
    {
        return root;
    }
    public void openWorkersView()
    {
        viewHandler.openView("workers");
    }
}
