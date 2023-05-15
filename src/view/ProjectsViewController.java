package view;

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
import viewmodel.ProjectView.ProjectManagersTable;
import viewmodel.ProjectView.ProjectsTable;
import viewmodel.ProjectView.ProjectsViewModel;
import viewmodel.ViewModel;

public class ProjectsViewController implements ViewController {
    public TableColumn<ProjectsTable, Button> open;
    //public TableColumn<ProjectsTable, Button> openTask;
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
    private ViewHandler viewHandler;
    private ObservableList<ProjectsTable> projectsTables;

    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root) {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (ProjectsViewModel) viewModel;
        avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());
        titleLabel.textProperty().bindBidirectional(this.viewModel.getTitleProperty());
        descriptionArea.textProperty().bindBidirectional(this.viewModel.getDescriptionProperty());
        nameLabel.textProperty().bindBidirectional(this.viewModel.userNameProperty());
        numberLabel.textProperty().bindBidirectional(this.viewModel.userNumberProperty());

        titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitleValue());
        deadlineColumn.setCellValueFactory(cellData -> cellData.getValue().deadlineProperty());


        projectEmployeeNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameValue());
        projectEmployeeRoleColumn.setCellValueFactory(cellData -> cellData.getValue().getRoleValue());
        employeesListTable.setItems(this.viewModel.getProjectManagersObservableList());

        PropertyValueFactory<ProjectsTable, Button> button = new PropertyValueFactory("btton");
        open.setCellValueFactory(button);
        open.setStyle("-fx-alignment: CENTER;");
        PropertyValueFactory<ProjectsTable, Button> buttonEdit = new PropertyValueFactory("buttonEdit");
        edit.setCellValueFactory(buttonEdit);
        edit.setStyle("-fx-alignment: CENTER;");

        this.viewModel.employeePropertyProperty().addListener((observable, oldValue, newValue) -> {
            setWindow(((Employee) newValue).getRole());
        });

        this.viewModel.load();

        setWindow(this.viewModel.getEmployeeProperty().getRole());

        projectsTables = FXCollections.observableArrayList();
        fillInProjectsTable();

        assignButton.setVisible(false);
        this.viewModel.selectedProjectProperty().addListener(((observable, oldValue, newValue) -> {
            if (((ProjectsViewModel) viewModel).getEmployeeProperty().getRole().equals(EmployeeRole.PROJECT_MANAGER) || ((ProjectsViewModel) viewModel).getEmployeeProperty().getRole().equals(EmployeeRole.MAIN_MANAGER)) {
                assignButton.setVisible(newValue);
            }
        }));

        projectTable.setItems(projectsTables);
        employeesListTable.setItems(this.viewModel.getProjectManagersObservableList());

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
       
        assignButton.setVisible(false);
        this.viewModel.selectedProjectProperty().addListener(((observable, oldValue, newValue) -> {
            if (((ProjectsViewModel) viewModel).getEmployeeProperty().getRole().equals(EmployeeRole.PROJECT_MANAGER) || ((ProjectsViewModel) viewModel).getEmployeeProperty().getRole().equals(EmployeeRole.MAIN_MANAGER)) {
                assignButton.setVisible(newValue);
            }
        }));

        projectTable.setItems(projectsTables);
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

    private void setWindow(EmployeeRole employeeRole) {
        switch (employeeRole) {
            case WORKER -> {
                addProjectButton.setVisible(false);
                assignButton.setVisible(false);
                open.setVisible(true);
                edit.setVisible(false);
                projectHBox.setVisible(true);
                projectHBox.setManaged(true);
            }
            case HR -> {
                projectHBox.setVisible(false);
                projectHBox.setManaged(false);
                addProjectButton.setVisible(false);
                assignButton.setVisible(false);
                open.setVisible(false);
                edit.setVisible(false);


            }
            case PROJECT_MANAGER -> {
                addProjectButton.setVisible(false);
                open.setVisible(true);
                edit.setVisible(true);
                projectHBox.setVisible(true);
                projectHBox.setManaged(true);
            }
            case MAIN_MANAGER -> {
                addProjectButton.setVisible(true);
                open.setVisible(true);
                edit.setVisible(true);
                projectHBox.setVisible(true);
                projectHBox.setManaged(true);
            }
        }

    }

    public Region getRoot() {
        return root;
    }

    @Override
    public void reset() {
        assignButton.setVisible(false);
        setWindow(viewModel.getEmployeeProperty().getRole());
        viewModel.reset();
        fillInProjectsTable();
    }

    @FXML
    public void openAddProject() {
        viewHandler.openView("addProject");
    }

    public void openTask() {
        viewHandler.openView("tasks");
    }


    public void openWorkers()
    {
        viewHandler.openView("workers");
    }

    public void openHome()
    {
        EmployeeRole role = this.viewModel.getEmployeeProperty().getRole();
        switch (role) {
            case WORKER -> {
                viewHandler.openView("workerHomePage");
            }
            case HR -> {
                viewHandler.openView("home");
            }
            case PROJECT_MANAGER -> {
                viewHandler.openView("home");
            }
            case MAIN_MANAGER -> {
                viewHandler.openView("home");
            }
        }
    }
    public void openEditProject()
    {
        viewHandler.openView("editProject");
    }
}