package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import model.Employee;
import viewmodel.AddProjectView.AddProjectViewModel;
import viewmodel.EditProjectViewModel;
import viewmodel.ViewModel;
import viewmodel.WorkersWithCheckboxTable;

public class EditProjectViewController implements ViewController
{
    @FXML
    public ImageView avatarPic;
    @FXML
    public Label nameL;
    @FXML
    public Label workingNumberL;
    @FXML
    public Label editProjectL;
    @FXML
    public TextField title;
    @FXML
    public Label titleE;
    @FXML
    public DatePicker deadline;
    @FXML
    public Label deadlineE;
    @FXML
    public TextArea description;
    @FXML
    public TableView<WorkersWithCheckboxTable> managersTable;
    public Label headlineLabel;
    @FXML
    private TableColumn<WorkersWithCheckboxTable, String> numberColumn;
    @FXML
    private TableColumn<WorkersWithCheckboxTable, String> nameColumn;
    @FXML
    private TableColumn<WorkersWithCheckboxTable, CheckBox> checkBoxColumn;
    private Region root;
    private EditProjectViewModel viewModel;
    private ViewHandler viewHandler;
    private ObservableList<WorkersWithCheckboxTable> workersWithCheckboxTables;
    
    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root)
    {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (EditProjectViewModel) viewModel;
        this.viewModel.load();
        workersWithCheckboxTables = FXCollections.observableArrayList();
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().getNumberProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        PropertyValueFactory<WorkersWithCheckboxTable, CheckBox> checkbox = new PropertyValueFactory("checkbox");
        checkBoxColumn.setCellValueFactory(checkbox);
        checkBoxColumn.setStyle("-fx-alignment: CENTER;");
        avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());
        title.textProperty().bindBidirectional(this.viewModel.getTitleProperty());
        description.textProperty().bindBidirectional(this.viewModel.getDescriptionProperty());
        deadline.valueProperty().bindBidirectional(this.viewModel.getDeadlineProperty());
        managersTable.setItems(workersWithCheckboxTables);
        titleE.textProperty().bind(this.viewModel.getTitleErrorProperty());
        deadlineE.textProperty().bind(this.viewModel.getDeadlineErrorProperty());
        nameL.textProperty().bindBidirectional(this.viewModel.nameProperty());
        workingNumberL.textProperty().bindBidirectional(this.viewModel.workingNumberProperty());
        headlineLabel.textProperty().bind(this.viewModel.headlineProperty());
        fillInManagerTable();
    }
    
    private void fillInManagerTable()
    {
        workersWithCheckboxTables.clear();
        for (int i = 0; i < this.viewModel.getEmployeesOfManager().size(); i++)
        {
            Employee employee = this.viewModel.getEmployeesOfManager().get(i);
            workersWithCheckboxTables.add(new WorkersWithCheckboxTable(employee));
            CheckBox checkBox = new CheckBox(" ");
            checkBox.setId("checklist");
            checkBox.setOnAction(e ->
            {
                switchWorker(employee);
            });
            checkBox.setSelected(this.viewModel.isEmployeeAssigned(employee));
            workersWithCheckboxTables.get(i).setCheckbox(checkBox);
        }
    }
    
    @Override
    public Region getRoot()
    {
        return root;
    }
    @Override
    public void reset()
    {
        viewModel.reset();
        fillInManagerTable();
    }
    
    public void switchWorker(Employee employee)
    {
        viewModel.switchWorker(employee);
    }
    
    public void openHome()
    {
        viewHandler.openView("home");
    }
    
    public void openProjects()
    {
        viewHandler.openView("projects");
    }
    
    public void openWorkersView()
    {
        viewHandler.openView("workers");
    }
    public void backButtonPressed()
    {
        viewHandler.openLastWindow();
    }
    public void saveChangesButtonPressed()
    {
        if (viewModel.saveProject())
        {
            viewHandler.openView("projects");
        }
    }
}
