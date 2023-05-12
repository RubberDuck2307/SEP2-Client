package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
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
    }
    
    @Override
    public Region getRoot()
    {
        return root;
    }
    
    public void openHome()
    {
        viewHandler.openView("home");
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
        if (viewModel.saveProject()) {
            viewHandler.openView("projects");
        }
    }
}
