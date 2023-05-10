package view;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ProjectManagerProfileViewController
{
  public Button backButton;
  public ImageView avatarPic;
  public Label managerFirstName;
  public Label managerLastName;
  public Label managerRole;
  public Label managerDateOfBirth;
  public Label managerPhoneNumber;
  public Label managerEmail;
  public TableView currentProjectsTable;
  public TableColumn projectTitle;
  public TableColumn projectDeadline;
  public TableColumn projectPriority;
  public TableView assignWorkersTable;
  public TableColumn workerNumber;
  public TableColumn workerName;
  public TableColumn workerEmail;

  public void openProjects(MouseEvent mouseEvent)
  {
  }

  public void projectTableClick()
  {
  }

  public void assignedWorkerTableClick()
  {
  }

  public void goBackButton()
  {
  }
}
