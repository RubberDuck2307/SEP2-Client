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
import view.ViewController;
import view.ViewHandler;
import viewmodel.ProjectView.EditProjectViewModel;
import viewmodel.ViewModel;
import viewmodel.WorkersWithCheckboxTable;

public class EditProjectViewController extends ViewControllerWithNavigationMenu
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
    private ObservableList<WorkersWithCheckboxTable> workersWithCheckboxTables;
    @FXML
    private HBox projectsHbox;

    @FXML
    private ImageView bellImage;
    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root)
    {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (EditProjectViewModel) viewModel;
        this.viewModel.load();
        super.init(this.viewModel, viewHandler, bellImage ,this.avatarPic, this.nameL, this.workingNumberL, this.projectsHbox);

        bind();
        setUpTable();

        fillInManagerTable();
        super.setWindow(this.viewModel.getEmployee().getRole());
    }

    private void bind(){

        avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());
        title.textProperty().bindBidirectional(this.viewModel.getTitleProperty());
        description.textProperty().bindBidirectional(this.viewModel.getDescriptionProperty());
        deadline.valueProperty().bindBidirectional(this.viewModel.getDeadlineProperty());
        titleE.textProperty().bind(this.viewModel.getTitleErrorProperty());
        deadlineE.textProperty().bind(this.viewModel.getDeadlineErrorProperty());
        headlineLabel.textProperty().bind(this.viewModel.headlineProperty());
    }

    private void setUpTable(){
        workersWithCheckboxTables = FXCollections.observableArrayList();
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().getNumberProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        PropertyValueFactory<WorkersWithCheckboxTable, CheckBox> checkbox = new PropertyValueFactory("checkbox");
        checkBoxColumn.setCellValueFactory(checkbox);
        checkBoxColumn.setStyle("-fx-alignment: CENTER;");
        managersTable.setItems(workersWithCheckboxTables);
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
        super.setWindow(viewModel.getEmployee().getRole());
    }
    
    public void switchWorker(Employee employee)
    {
        viewModel.switchWorker(employee);
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
