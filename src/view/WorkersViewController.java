package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class WorkersViewController
{
  @FXML public TableView workerTable;
  @FXML public TableColumn name;
  @FXML public TableColumn role;
  @FXML public TableColumn workingNumber;
  @FXML public TableColumn email;

  public void openProjects(MouseEvent mouseEvent)
  {
  }

  public void createNewProfile(ActionEvent actionEvent)
  {
  }

  public void workerTableClick(MouseEvent mouseEvent)
  {
  }
}
