package mediator;

import javafx.scene.control.Alert;
import model.Employee;
import model.EmployeeRole;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;

import java.rmi.RemoteException;

public class ListenerHandler {

    private RemoteModel model;
    private GeneralListener<String, String> listener;

    public ListenerHandler(RemoteModel model, GeneralListener<String, String> listener) {
        this.model = model;
        this.listener = listener;
    }

    public void addListener(Employee employee) throws RemoteException {
        if (employee != null) {
            if (employee.getRole().equals(EmployeeRole.HR)) {
                model.addListener(listener, "forgetPasswordNotification");
            } else model.addListener(listener, "general");
        }
    }


    public void removeListener() {

        try {
            model.removeListener(listener);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }


    }

    public void handlePropertyChange(ObserverEvent<String, String> event) {
        System.out.println("happening");
        if (event.getPropertyName().equals("forgetPasswordNotification"))
            handleForgetPasswordNotification();
    }

    public void handleForgetPasswordNotification() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User Profile Created");
        alert.setHeaderText("I am monkey!");
        alert.showAndWait();
    }

}
