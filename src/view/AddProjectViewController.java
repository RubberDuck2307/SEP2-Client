package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Employee;
import viewmodel.AddProjectView.AddProjectViewModel;
import viewmodel.ViewModel;
import viewmodel.WorkersWithCheckboxTable;

public class AddProjectViewController implements ViewController {
    @FXML
    public Label nameL;
    @FXML
    public Label workingNumberL;
    @FXML
    private TextField title;

    @FXML
    private DatePicker deadline;
    @FXML
    private TextArea description;
    @FXML
    private Label titleE;
    @FXML
    private Label deadlineE;
    @FXML
    public TableView<WorkersWithCheckboxTable> managersTable;
    @FXML
    private TableColumn<WorkersWithCheckboxTable, String> numberColumn;
    @FXML
    private TableColumn<WorkersWithCheckboxTable, String> nameColumn;
    @FXML
    private TableColumn<WorkersWithCheckboxTable, CheckBox> checkBoxColumn;
    private Region root;
    private AddProjectViewModel viewModel;
    private ViewHandler viewHandler;
    private ObservableList<WorkersWithCheckboxTable> workersWithCheckboxTables;

    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel,
                     Region root) {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (AddProjectViewModel) viewModel;
        this.viewModel.load();
        workersWithCheckboxTables = FXCollections.observableArrayList();

        numberColumn.setCellValueFactory(
                cellData -> cellData.getValue().getNumberProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        PropertyValueFactory<WorkersWithCheckboxTable, CheckBox> checkbox = new PropertyValueFactory("checkbox"
        );
        checkBoxColumn.setCellValueFactory(checkbox);
        checkBoxColumn.setStyle("-fx-alignment: CENTER;");

        title.textProperty().bindBidirectional(this.viewModel.getTitleProperty());
        description.textProperty().bindBidirectional(this.viewModel.getDescriptionProperty());
        deadline.valueProperty().bindBidirectional(this.viewModel.getDeadlineProperty());
        for (int i = 0; i < this.viewModel.getManagers().size(); i++) {
            Employee employee = this.viewModel.getManagers().get(i);
            workersWithCheckboxTables.add(new WorkersWithCheckboxTable(employee));
            CheckBox checkBox = new CheckBox(" ");
            checkBox.setId("checklist");
            checkBox.setOnAction(e -> {
                assignEmployee(employee);
            });
            checkBox.setSelected(false);
            workersWithCheckboxTables.get(i).setCheckbox(checkBox);
        }
        managersTable.setItems(workersWithCheckboxTables);
        titleE.textProperty().bind(this.viewModel.getTitleErrorProperty());
        deadlineE.textProperty().bind(this.viewModel.getDeadlineErrorProperty());
        nameL.textProperty().bindBidirectional(this.viewModel.nameProperty());
        workingNumberL.textProperty().bindBidirectional(this.viewModel.workingNumberProperty());
    }


    public Region getRoot() {
        return root;
    }

    @FXML
    public void createButtonPressed() {
        if (viewModel.addProject()) {
            viewHandler.openView("projects");
        }
    }

    @FXML
    public void backButtonPressed() {
        viewHandler.openLastWindow();
    }

    private void assignEmployee(Employee employee) {

    }
    public void openWorkersView()
    {
        viewHandler.openView("workers");
    }
}
