package viewmodel.EmployeeView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import viewmodel.ViewModel;

import java.rmi.UnmarshalException;

public class ForgotPasswordViewModel implements ViewModel {

    private StringProperty workingNumber;
    private StringProperty error;
    private Model model;

    public ForgotPasswordViewModel(Model model) {
        this.model = model;
        this.workingNumber = new SimpleStringProperty("");
        this.error = new SimpleStringProperty("");
    }

    public void reset() {
        this.workingNumber.setValue("");
    }

    public String getWorkingNumber() {
        return workingNumber.get();
    }

    public StringProperty workingNumberProperty() {
        return workingNumber;
    }

    public boolean sendNotification() {
        try {
            if (model.addForgetPasswordNotification(Integer.valueOf(workingNumber.getValue())))
                return true;
            else {
                error.setValue("Invalid working number");
                return false;
            }
        } catch (NumberFormatException e) {
            error.setValue("Invalid working number");
            return false;
        } catch (Exception e) {
            error.set("Server is not working");
        }
        return false;
    }

    public String getError() {
        return error.get();
    }

    public StringProperty errorProperty() {
        return error;
    }
}
