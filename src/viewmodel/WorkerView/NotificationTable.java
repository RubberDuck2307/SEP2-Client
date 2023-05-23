package viewmodel.WorkerView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.*;

public class NotificationTable implements IdObject {
    private StringProperty text;
    private Long id;

    public NotificationTable(String text, Long id) {
        this.text = new SimpleStringProperty(text);;
        this.id = id;
    }

    public NotificationTable(AssignedToProjectNotification notification) {
        this.text = new SimpleStringProperty();
        this.text.set( "You have been assigned to project " + notification.getProjectName());
    }

    public NotificationTable(AssignedToTaskNotification notification){
        this.text = new SimpleStringProperty();
        this.text.set( "You have been assigned to task " + notification.getTaskName());
    }

    public NotificationTable(ForgottenPasswordNotification notification) {
        this.text = new SimpleStringProperty();
        this.text.set( "User with working number " + notification.getWorkingNumber() + " has requested a password reset.");
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getText() {
        return text.get();
    }

    public StringProperty textProperty() {
        return text;
    }
}
