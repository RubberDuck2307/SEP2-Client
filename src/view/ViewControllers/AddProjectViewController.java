package view.ViewControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import model.Employee;
import model.EmployeeRole;
import view.ViewController;
import view.ViewHandler;
import viewmodel.ProjectView.AddProjectViewModel;
import viewmodel.ViewModel;
import viewmodel.WorkersWithCheckboxTable;

public class AddProjectViewController extends ViewControllerWithNavigationMenu
{
    @FXML
    private Label nameL;
    @FXML
    private Label workingNumberL;
    @FXML
    private ImageView avatarPic;
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
    private TableView<WorkersWithCheckboxTable> managersTable;
    @FXML
    private TableColumn<WorkersWithCheckboxTable, String> numberColumn;
    @FXML
    private TableColumn<WorkersWithCheckboxTable, String> nameColumn;
    @FXML
    private TableColumn<WorkersWithCheckboxTable, CheckBox> checkBoxColumn;
    private Region root;
    private AddProjectViewModel viewModel;
    private ObservableList<WorkersWithCheckboxTable> workersWithCheckboxTables;
    @FXML
    private HBox projectsHbox;
    @FXML
    private ImageView bellImage;
    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel,
                     Region root) {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (AddProjectViewModel) viewModel;
        this.viewModel.load();
        super.init(this.viewModel, viewHandler, bellImage, avatarPic, nameL, workingNumberL, projectsHbox);
        workersWithCheckboxTables = FXCollections.observableArrayList();

        numberColumn.setCellValueFactory(
                cellData -> cellData.getValue().getNumberProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        PropertyValueFactory<WorkersWithCheckboxTable, CheckBox> checkbox = new PropertyValueFactory("checkbox"
        );

        checkBoxColumn.setCellValueFactory(checkbox);
        checkBoxColumn.setStyle("-fx-alignment: CENTER;");
        avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());
        title.textProperty().bindBidirectional(this.viewModel.getTitleProperty());
        description.textProperty().bindBidirectional(this.viewModel.getDescriptionProperty());
        deadline.valueProperty().bindBidirectional(this.viewModel.getDeadlineProperty());
        managersTable.setItems(workersWithCheckboxTables);
        titleE.textProperty().bind(this.viewModel.getTitleErrorProperty());
        deadlineE.textProperty().bind(this.viewModel.getDeadlineErrorProperty());

        super.setWindow(this.viewModel.getEmployee().getRole());
        fillInTable();
    }

    private void fillInTable(){
        workersWithCheckboxTables.clear();
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
    }

    @Override
    public void reset() {
        viewModel.reset();
        deadline.setValue(null);
        fillInTable();
        super.setWindow(viewModel.getEmployee().getRole());
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

    public void assignEmployee(Employee employee) {
        viewModel.assignEmployee(employee);
    }



}
