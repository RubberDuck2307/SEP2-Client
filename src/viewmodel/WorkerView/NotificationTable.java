package viewmodel.WorkerView;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import model.Employee;

public class NotificationTable
{

  private StringProperty message;
  private Button button;
  public NotificationTable(String message){
    this.message = new SimpleStringProperty(message);
  }


  public Button getButton()
  {
    return button;
  }

  public void setButton(Button button)
  {
    this.button = button;
  }

  public String getMessage()
  {
    return message.get();
  }

  public StringProperty messageProperty()
  {
    return message;
  }
}
