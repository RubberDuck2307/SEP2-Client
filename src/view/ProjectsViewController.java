package view;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import viewmodel.ProjectView.ProjectManagersTable;
import viewmodel.ProjectView.ProjectsTable;
import viewmodel.ProjectView.ProjectsViewModel;
import viewmodel.TaskView.TasksTable;
import viewmodel.TaskView.WorkersTable;
import viewmodel.ViewModel;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectsViewController implements ViewController
{
    public TableColumn<ProjectsTable, Button> delete;
    //public TableColumn<ProjectsTable, Button> openTask;
    public TableColumn edit;

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
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root)
    {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (ProjectsViewModel) viewModel;
        
        titleLabel.textProperty().bindBidirectional(this.viewModel.getTitleProperty());
        descriptionArea.textProperty().bindBidirectional(this.viewModel.getDescriptionProperty());
        
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitleValue());
        deadlineColumn.setCellValueFactory(cellData -> cellData.getValue().deadlineProperty());
        
        
        projectEmployeeNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameValue());
        projectEmployeeRoleColumn.setCellValueFactory(cellData -> cellData.getValue().getRoleValue());
        employeesListTable.setItems(this.viewModel.getProjectManagersObservableList());
        
        PropertyValueFactory<ProjectsTable, Button> button = new PropertyValueFactory("btton");
        delete.setCellValueFactory(button);
        delete.setStyle("-fx-alignment: CENTER;");
        
        this.viewModel.load();
        
        projectsTables = FXCollections.observableArrayList();
        for (int i = 0; i < this.viewModel.getProjectList().size(); i++)
        {
            projectsTables.add(new ProjectsTable(this.viewModel.getProjectList().get(i)));
            Button button1 = new Button(" ");
            button1.setId("showTasks");
            Long index = (long) i;
            button1.setOnAction(e ->
            {
                projectButtonTableClick(index);
                viewHandler.openView("tasks");
            });
            projectsTables.get(i).setBtton(button1);
        }
        
        
        projectTable.setItems(projectsTables);
        employeesListTable.setItems(this.viewModel.getProjectManagersObservableList());
        
    }
    
    @Override
    public void reset()
    {
    }
    
    
    @FXML
    public void projectTableClick()
    {
        
        if (projectTable.getSelectionModel().getSelectedItem() != null)
        {
            viewModel.setProject(projectTable.getSelectionModel().getSelectedItem().getId());
        }
    }
    
    public void projectButtonTableClick(Long index)
    {
        projectTable.getSelectionModel().select(index.intValue());
        projectTableClick();
    }
    
    public Region getRoot()
    {
        return root;
    }
    
    @FXML
    public void openAddProject()
    {
        viewHandler.openView("addProject");
    }
    
    public void openTask()
    {
        viewHandler.openView("tasks");
    }
    
    
    @FXML
    public void openCreateUserProfiles()
    {
        viewHandler.openView("createUserProfile");
    }
}