package view.ViewControllers;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import model.Employee;
import model.EmployeeRole;
import view.ViewController;
import view.ViewHandler;
import viewmodel.TaskView.TasksTable;
import viewmodel.ViewModel;
import viewmodel.WorkerView.WorkersTable;
import viewmodel.WorkerView.WorkersViewModel;

public class WorkersViewController implements ViewController
{
    @FXML
    public ImageView avatarPic;
    @FXML
    public TableView<viewmodel.WorkerView.WorkersTable> workerTable;
    @FXML
    public TableColumn<viewmodel.WorkerView.WorkersTable, String> name;
    @FXML
    public TableColumn<viewmodel.WorkerView.WorkersTable, String> role;
    @FXML
    public TableColumn<viewmodel.WorkerView.WorkersTable, String> workingNumber;
    @FXML
    public TableColumn<viewmodel.WorkerView.WorkersTable, String> email;
    @FXML
    public TableColumn<viewmodel.WorkerView.WorkersTable, Button> edit;
    public TableColumn<viewmodel.WorkerView.WorkersTable, Button> delete;
    @FXML
    private HBox projectHBox;
    @FXML
    public Button createNewProfileButton;
    @FXML
    public Label employeeName;
    @FXML
    public Label employeeWorkingNumber;
    
    private Employee selectedEmployee;
    private Region root;
    private WorkersViewModel viewModel;
    private ViewHandler viewHandler;
    private ObservableList<viewmodel.WorkerView.WorkersTable> workersTables;
    
    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root)
    {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (WorkersViewModel) viewModel;
        this.viewModel.load();
        avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());
        name.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        workingNumber.setCellValueFactory(cellData -> cellData.getValue().getNumberProperty());
        email.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
        role.setCellValueFactory(cellData -> cellData.getValue().getRoleProperty());
        workerTable.setItems(((WorkersViewModel) viewModel).getWorkersTable());
        employeeName.textProperty().bindBidirectional(this.viewModel.getEmployeeName());
        employeeWorkingNumber.textProperty().bindBidirectional(this.viewModel.getEmployeeWorkingNumber());
        this.viewModel.employeePropertyProperty().addListener((observable, oldValue, newValue) ->
        {
            setWindow(((Employee) newValue).getRole());
        });
        setWindow(this.viewModel.getEmployeeProperty().getRole());
        PropertyValueFactory<viewmodel.WorkerView.WorkersTable, Button> button = new PropertyValueFactory("button");
        PropertyValueFactory<viewmodel.WorkerView.WorkersTable, Button> deleteButton = new PropertyValueFactory("deleteButton");
        edit.setCellValueFactory(button);
        delete.setCellValueFactory(deleteButton);
        //edit.setStyle("-fx-margin-right: 15px;");
        workersTables = FXCollections.observableArrayList();
        fillInTable();
        workerTable.setItems(workersTables);
    }
    
    @Override
    public Region getRoot()
    {
        return root;
    }
    
    @Override
    public void reset()
    {
        viewModel.load();
        setWindow(this.viewModel.getEmployeeProperty().getRole());
        fillInTable();
    }
    
    public void delete(Employee employee)
    {
        viewModel.delete(employee);
        reset();
    }
    
    private void fillInTable()
    {
        workersTables.clear();
        for (int i = 0; i < this.viewModel.getWorkersTable().size(); i++)
        {
            Employee employee = this.viewModel.getEmployeess().get(i);
            workersTables.add(new WorkersTable(employee));
            Button button1 = new Button("");
            Button button2 = new Button("");
            button1.setId("button-edit");
            button2.setId("deleteButton");
            button1.setOnAction(e ->
            {
                workersButtonTableClick(employee);
                viewHandler.openView("editProfile");
            });
            button2.setOnAction(e ->
            {
                alertWindow(employee);
            });
            workersTables.get(i).setButton(button1);
            workersTables.get(i).setDeleteButton(button2);
        }
    }
    
    private void alertWindow(Employee employee)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting User Profile");
        alert.setHeaderText("Are you sure you want to delete " + employee.getName() + " ?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK)
        {
            delete(employee);
        }
    }
    
    public void workerTableClick()
    {
        if (workerTable.getSelectionModel().getSelectedItem() != null)
        {
            viewModel.chooseWorker(workerTable.getSelectionModel().getSelectedItem().getNumber());
            if (viewModel.isMainManager(workerTable.getSelectionModel().getSelectedItem().getNumber()))
            {
                viewHandler.openView("projectManagerPage");
            }
            else if (viewModel.isWorker(workerTable.getSelectionModel().getSelectedItem().getNumber()))
            {
                viewHandler.openView("workerProfile");
            }
            else
            {
                viewHandler.openView("hrAndMainManagerProfile");
            }
        }
    }
    public void workersButtonTableClick(Employee employee)
    {
        viewModel.chooseWorker(employee);
    }
    public void openProjects()
    {
        viewHandler.openView("projects");
    }
    
    public void createNewProfile()
    {
        viewHandler.openView("createUserProfile");
    }
    
    public void openWorkersView()
    {
        viewHandler.openView("workers");
    }
    public void openHome()
    {
        EmployeeRole role = this.viewModel.getEmployeeProperty().getRole();
        switch (role)
        {
            case WORKER ->
            {
                viewHandler.openView("workerHomePage");
            }
            case HR ->
            {
                viewHandler.openView("home");
            }
            case PROJECT_MANAGER ->
            {
                viewHandler.openView("home");
            }
            case MAIN_MANAGER ->
            {
                viewHandler.openView("home");
            }
        }
    }
    private void setWindow(EmployeeRole employeeRole)
    {
        switch (employeeRole)
        {
            case WORKER ->
            {
                projectHBox.setVisible(true);
                projectHBox.setManaged(true);
                edit.setVisible(false);
                createNewProfileButton.setVisible(false);
            }
            case HR ->
            {
                projectHBox.setVisible(false);
                projectHBox.setManaged(false);
                edit.setVisible(true);
                createNewProfileButton.setVisible(true);
            }
            case PROJECT_MANAGER ->
            {
                projectHBox.setVisible(true);
                projectHBox.setManaged(true);
                edit.setVisible(false);
                createNewProfileButton.setVisible(false);
            }
            case MAIN_MANAGER ->
            {
                projectHBox.setVisible(true);
                projectHBox.setManaged(true);
                edit.setVisible(false);
                createNewProfileButton.setVisible(false);
            }
        }
    }
}
