package view.ViewControllers;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import model.Employee;
import model.EmployeeRole;
import model.Priority;
import model.Tag;
import util.StringIntegerConverter;
import view.ViewController;
import view.ViewHandler;
import viewmodel.TaskView.EditTaskViewModel;
import viewmodel.ViewModel;
import viewmodel.WorkersWithCheckboxTable;

import java.util.ArrayList;

public class EditTaskViewController implements ViewController
{
    @FXML
    public Button backButton;
    @FXML
    public Label nameOfTheTask;
    @FXML
    public TextField title;
    @FXML public ImageView avatarPic;
    @FXML
    public Label errorTitleMessage;
    @FXML
    public DatePicker deadline;
    @FXML
    public TextArea description;
    @FXML
    public ChoiceBox<String> priority;
    @FXML
    public TextField estimatedHours;
    @FXML
    public TextField tags;
    @FXML
    public HBox hBoxForTags;
    @FXML
    public Button createTaskButton;
    //TODO steal from Cosmin
    @FXML
    public TableView<WorkersWithCheckboxTable> workersTable;
    @FXML
    public TableColumn<WorkersWithCheckboxTable, String> numberColumn;
    @FXML
    public TableColumn<WorkersWithCheckboxTable, String> nameColumn;
    @FXML
    public TableColumn<WorkersWithCheckboxTable, CheckBox> checkBoxColumn;
    @FXML
    public Button assignWorkersButton;
    @FXML
    public Button addTag;
    @FXML ColorPicker colorPicker;
    @FXML Label tagsE;
    @FXML
    public Label errorTitleHours;
    public ChoiceBox<String> status;
    public Label errorPriorityMessage;
    public Label errorDeadlineMessage;
    public Label nameL;
    public Label workingNumberL;
    private Region root;
    private EditTaskViewModel viewModel;
    private ViewHandler viewHandler;
    private int counter;
    private ArrayList<CheckBox> checkboxes;
    private int hoursAsInteger;
    private ObservableList<WorkersWithCheckboxTable> workersTableList;
    
    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root)
    {
        workersTableList = FXCollections.observableArrayList();
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (EditTaskViewModel) viewModel;
        this.viewModel.load();
        errorTitleHours.setText(null);
        setChoiceBox();
        this.counter = 0;
        this.checkboxes = new ArrayList<>();
        avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().getNumberProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        PropertyValueFactory<WorkersWithCheckboxTable, CheckBox> checkbox = new PropertyValueFactory("checkbox");
        checkBoxColumn.setCellValueFactory(checkbox);
        checkBoxColumn.setStyle("-fx-alignment: CENTER;");
        bindEverything();
        ((EditTaskViewModel) viewModel).load();
        fillInWorkerTable();
        fillInTags();
        deadline.setEditable(false);
        nameL.textProperty().bind(this.viewModel.nameProperty());
        workingNumberL.textProperty().bind(this.viewModel.workingNumberProperty());
    }
    
    @Override
    public Region getRoot()
    {
        return root;
    }

    @Override
    public void reset() {
        viewModel.reset();
        fillInWorkerTable();
        fillInTags();
    }

    public void bindEverything()
    {
        errorDeadlineMessage.textProperty().bindBidirectional(this.viewModel.errorDeadlineMessageProperty());
        errorPriorityMessage.textProperty().bindBidirectional(this.viewModel.errorPriorityMessageProperty());
        nameOfTheTask.textProperty().bindBidirectional(this.viewModel.getNameOfTheTask());
        errorTitleMessage.textProperty().bindBidirectional(this.viewModel.errorTitleMessageProperty());
        errorTitleHours.textProperty().bindBidirectional(this.viewModel.errorTitleHoursProperty());
        title.textProperty().bindBidirectional(this.viewModel.titleProperty());
        colorPicker.valueProperty().bindBidirectional(this.viewModel.colorProperty());
        //estimatedHours.textProperty().bindBidirectional(this.viewModel.estimatedHoursProperty());
        Bindings.bindBidirectional(estimatedHours.textProperty(), (viewModel).estimatedHoursProperty(), new StringIntegerConverter(0));
        deadline.valueProperty().bindBidirectional(this.viewModel.deadlineProperty());
        status.valueProperty().bindBidirectional(this.viewModel.statusProperty());
        priority.valueProperty().bindBidirectional(this.viewModel.priorityProperty());
        description.textProperty().bindBidirectional(this.viewModel.descriptionProperty());
        tags.textProperty().bindBidirectional(this.viewModel.tagsProperty());
        tagsE.textProperty().bindBidirectional(this.viewModel.tagsEProperty());
    }
    
    private void fillInWorkerTable()
    {
        workersTableList.clear();
        for (int i = 0; i < viewModel.getEmployees().size(); i++)
        {
            //System.out.println(viewModel.getEmployees().get(i).getName());
            Employee employee = viewModel.getEmployees().get(i);
            workersTableList.add(new WorkersWithCheckboxTable(employee));
            CheckBox checkBox = new CheckBox(" ");
            checkBox.setId("checklist");
            checkBox.setOnAction(e ->
            {
                switchWorker(employee);
                checkBox.setSelected(viewModel.isEmployeeAssigned(employee));
            });
            checkBox.setSelected(viewModel.isEmployeeAssigned(employee));
            workersTableList.get(i).setCheckbox(checkBox);
        }
        workersTable.setItems(workersTableList);
    }

    public void addTag()
    {
        if(viewModel.addTag())
        {
            fillInTags();
        }
        //reset tags \/\/\/
        tags.setText("");
    }

    private void styleTags(CheckBox checkBox, Tag tag){
        checkBox.setId("newTags");
        String colorString = tag.getColor();
        Color color = Color.web(colorString);
        String borderColor= color.darker().toString().replace("0x", "#");
        if(color.getBrightness()<0.7){
            checkBox.setStyle("-fx-background-color: " + colorString + ";"
                +"-fx-border-color: " + borderColor + ";"
                + "-fx-text-fill: white;");
        }
        else checkBox.setStyle("-fx-background-color: " + colorString + ";"
            +"-fx-border-color: " + borderColor + ";");
    }

    private void fillInTags()
    {
        hBoxForTags.getChildren().clear();
        for (int i = 0; i < viewModel.getTagList().size(); i++)
        {
            Tag tag = viewModel.getTagList().get(i);
            CheckBox checkBox = new CheckBox(tag.getName());
            styleTags(checkBox, tag);
            hBoxForTags.getChildren().add(checkBox);
            checkBox.setOnAction(e -> {
                switchTag(tag);
            });
            checkBox.setSelected(viewModel.isTagAssigned(tag));
        }
    }

    private void switchTag(Tag tag)
    {
        viewModel.switchTag(tag);
    }

    public void switchWorker(Employee employee)
    {
        viewModel.switchWorker(employee);
    }
    
    public void setPriority()
    {
        viewModel.setPriority();
    }
    
    public void openProjects()
    {
        viewHandler.openView("projects");
    }
    
    public void createTask()
    {
        if (viewModel.add())
        {
            viewHandler.openView("tasks");
        }
    }
    
    public void backButtonClick()
    {
        viewHandler.openLastWindow();
    }
    
    public void setChoiceBox()
    {
        priority.getItems().add("HIGH");
        priority.getItems().add("MEDIUM");
        priority.getItems().add("LOW");
        status.getItems().add("TO DO");
        status.getItems().add("DONE");
        status.getItems().add("IN PROGRESS");
    }
    
    public void resetDeadlineClick(ActionEvent actionEvent)
    {
        this.deadline.getEditor().clear();
        this.deadline.setEditable(true);
        deadline.setValue(null);
    }
    public void openWorkersView()
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
}
