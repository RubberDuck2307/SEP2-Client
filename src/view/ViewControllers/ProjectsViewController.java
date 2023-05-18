package view.ViewControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import model.Employee;
import model.EmployeeRole;
import view.ViewController;
import view.ViewHandler;
import viewmodel.ProjectView.ProjectManagersTable;
import viewmodel.ProjectView.ProjectsTable;
import viewmodel.ProjectView.ProjectsViewModel;
import viewmodel.ViewModel;

public class ProjectsViewController extends ViewControllerWithNavigationMenu
{
    public TableColumn<ProjectsTable, Button> open;
    public TableColumn<ProjectsTable, Button> edit;
    @FXML public ImageView avatarPic;
    @FXML private HBox homeButton;
    @FXML
    private Label nameLabel;
    @FXML
    private Label numberLabel;

    @FXML
    private HBox projectHBox;
    @FXML
    private HBox workersHBox;
    @FXML
    private Button addProjectButton;
    @FXML
    private Button assignButton;
    @FXML
    private TableView<ProjectsTable> projectTable;
    @FXML
    private TableColumn<ProjectsTable, String> titleColumn;
    @FXML
    private TableColumn<ProjectsTable, String> deadlineColumn;

    @FXML
    private TableView<ProjectManagersTable> employeesListTable;
    @FXML
    private TableColumn<ProjectManagersTable, String> projectEmployeeNameColumn;
    public TableColumn<ProjectManagersTable, String> projectEmployeeRoleColumn;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private Label titleLabel;
    private Region root;
    private ProjectsViewModel viewModel;
    private ObservableList<ProjectsTable> projectsTables;
    @FXML
    private ImageView bellImage;

    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root) {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (ProjectsViewModel) viewModel;
        this.viewModel.load();
        super.init(this.viewModel, viewHandler,bellImage ,this.avatarPic, this.nameLabel, this.numberLabel, this.projectHBox);

        titleLabel.textProperty().bindBidirectional(this.viewModel.getTitleProperty());
        descriptionArea.textProperty().bindBidirectional(this.viewModel.getDescriptionProperty());

        setUpWorkersTable();

        setWindow(this.viewModel.getEmployee().getRole());

        setUpProjectsTable();
        fillInProjectsTable();

        assignButton.setVisible(false);
        this.viewModel.selectedProjectProperty().addListener(((observable, oldValue, newValue) -> {
            if (((ProjectsViewModel) viewModel).getEmployee().getRole().equals(EmployeeRole.PROJECT_MANAGER) || ((ProjectsViewModel) viewModel).getEmployee().getRole().equals(EmployeeRole.MAIN_MANAGER)) {
                assignButton.setVisible(newValue);
            }
        }));
    }

    private void setUpWorkersTable(){
        projectEmployeeNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameValue());
        projectEmployeeRoleColumn.setCellValueFactory(cellData -> cellData.getValue().getRoleValue());
        employeesListTable.setItems(this.viewModel.getProjectManagersObservableList());

    }
    private void setUpProjectsTable(){
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitleValue());
        deadlineColumn.setCellValueFactory(cellData -> cellData.getValue().deadlineProperty());
        PropertyValueFactory<ProjectsTable, Button> button = new PropertyValueFactory("btton");
        open.setCellValueFactory(button);
        open.setStyle("-fx-alignment: CENTER;");
        PropertyValueFactory<ProjectsTable, Button> buttonEdit = new PropertyValueFactory("buttonEdit");
        edit.setCellValueFactory(buttonEdit);
        edit.setStyle("-fx-alignment: CENTER;");
        projectsTables = FXCollections.observableArrayList();
        projectTable.setItems(projectsTables);
    }

    private void fillInProjectsTable(){
        projectsTables.clear();
        for (int i = 0; i < this.viewModel.getProjectList().size(); i++) {
            projectsTables.add(new ProjectsTable(this.viewModel.getProjectList().get(i)));
            Button button1 = new Button(" ");
            Button button2 = new Button(" ");
            button1.setId("showTasks");
            button2.setId("button-edit");
            Long index = (long) i;
            button1.setOnAction(e ->
            {
                projectButtonTableClick(index);
                viewHandler.openView("tasks");
            });
            button2.setOnAction(e ->
            {
                projectButtonTableClick(index);
                viewHandler.openView("editProject");
            });
            projectsTables.get(i).setBtton(button1);
            
            projectsTables.get(i).setButtonEdit(button2);
        }

        employeesListTable.setItems(this.viewModel.getProjectManagersObservableList());

    }


    @FXML
    public void projectTableClick() {
        if (projectTable.getSelectionModel().getSelectedItem() != null) {
            viewModel.setProject(projectTable.getSelectionModel().getSelectedItem().getId());
        }
    }

    @FXML
    public void assign() {
        viewHandler.openView("assignWorkersToProject");
    }

    public void projectButtonTableClick(Long index) {
        projectTable.getSelectionModel().select(index.intValue());
        projectTableClick();
    }

    protected void setWindow(EmployeeRole employeeRole) {
        super.setWindow(employeeRole);
        switch (employeeRole) {
            case WORKER -> {
                addProjectButton.setVisible(false);
                assignButton.setVisible(false);
                open.setVisible(true);
                edit.setVisible(false);
            }
            case HR -> {
                addProjectButton.setVisible(false);
                assignButton.setVisible(false);
                open.setVisible(false);
                edit.setVisible(false);


            }
            case PROJECT_MANAGER -> {
                addProjectButton.setVisible(false);
                open.setVisible(true);
                edit.setVisible(true);
            }
            case MAIN_MANAGER -> {
                addProjectButton.setVisible(true);
                open.setVisible(true);
                edit.setVisible(true);
            }
        }

    }

    public Region getRoot() {
        return root;
    }

    @Override
    public void reset() {
        assignButton.setVisible(false);
        setWindow(viewModel.getEmployee().getRole());
        viewModel.reset();
        fillInProjectsTable();
    }

    @FXML
    public void openAddProject() {
        viewHandler.openView("addProject");
    }
    public void openEditProject()
    {
        viewHandler.openView("editProject");
    }
}