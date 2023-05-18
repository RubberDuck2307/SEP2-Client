package mediator;

import javafx.scene.control.Alert;
import model.Employee;
import model.EmployeeRole;
import util.NamedPropertyChangeSubject;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class ListenerHandler implements NamedPropertyChangeSubject {

    private RemoteModel model;
    private GeneralListener<String, String> listener;
    private PropertyChangeSupport  propertyChangeSupport;

    public ListenerHandler(RemoteModel model, GeneralListener<String, String> listener) {
        this.model = model;
        this.listener = listener;
        propertyChangeSupport = new PropertyChangeSupport(this);
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
        propertyChangeSupport.firePropertyChange("notification", 0, 1);
    }

    @Override
    public void addListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);

    }

    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }
}
