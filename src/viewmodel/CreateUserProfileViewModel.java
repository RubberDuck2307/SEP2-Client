package viewmodel;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import model.Model;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import model.*;

import java.time.LocalDate;
import java.util.Objects;

public class CreateUserProfileViewModel implements ViewModel
{
    private Model model;
    private ViewState viewState;
    private StringProperty firstName;
    private StringProperty firstNameE;
    private StringProperty lastName;
    private StringProperty lastNameE;
    private StringProperty email;
    private StringProperty emailE;
    private ObservableList<EmployeeRole> role;
    private StringProperty roleE;
    private StringProperty phoneNumber;
    private StringProperty phoneNumberE;
    private StringProperty password;
    private StringProperty passwordE;
    private ObjectProperty<LocalDate> dob;
    private StringProperty dobE;
    private StringProperty jobTitle;
    
    CreateUserProfileViewModel(Model model, ViewState viewState)
    {
        this.model = model;
        this.viewState = viewState;
        this.firstName = new SimpleStringProperty("");
        this.firstNameE = new SimpleStringProperty("");
        this.lastName = new SimpleStringProperty("");
        this.lastNameE = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.emailE = new SimpleStringProperty("");
        this.role = new SimpleListProperty<>();
        this.roleE = new SimpleStringProperty("");
        this.phoneNumber = new SimpleStringProperty("");
        this.phoneNumberE = new SimpleStringProperty("");
        this.password = new SimpleStringProperty("");
        this.passwordE = new SimpleStringProperty("");
        LocalDate localDate = LocalDate.now();
        this.dob = new SimpleObjectProperty<>(localDate);
        this.dobE = new SimpleStringProperty("");
        this.jobTitle = new SimpleStringProperty("");
    }
    
    public boolean createUserProfile()
    {
        boolean valid = true;
        if (firstName.getValue().trim().isEmpty() || firstName.getValue().trim().length() <= 1)
        {
            valid = false;
            firstNameE.setValue("First name cannot be empty!");
        } else firstNameE.setValue("✓");
        
        if (lastName.getValue().trim().isEmpty() || lastName.getValue().trim().length() <= 1)
        {
            valid = false;
            lastNameE.setValue("Last name cannot be empty!");
        } else lastNameE.setValue("✓");
        
        if (email.getValue().trim().isEmpty())
        {
            valid = false;
            emailE.setValue("E-mail cannot be empty");
        } else if (!email.getValue().trim().contains("@") || !email.getValue().trim().contains("."))
        {
            emailE.setValue("Wrong email format");
        } else emailE.setValue("✓");
        
        if (Objects.equals(phoneNumber.getValue().trim(), ""))
        {
            valid = false;
            phoneNumberE.setValue("Phone number cannot be empty");
        } else if (phoneNumber.getValue().trim().charAt(0) == '+')
        {
            SimpleStringProperty replacedPhoneNumber = new SimpleStringProperty(phoneNumber.getValue().replace("+",""));
            System.out.println(replacedPhoneNumber.getValue());
        }
        
        if (Objects.equals(password.getValue(), ""))
        {
            valid = false;
            passwordE.setValue("password cannot be empty");
        }
        
        if (Objects.equals(password.getValue(), ""))
        {
            valid = false;
            passwordE.setValue("password cannot be empty");
        }
        
        if (valid)
        {
            UserProfile userProfile = new UserProfile(111, password.getValue());
            Employee employee = new Employee(111, firstName.getValue() + " " + lastName.getValue(), dob.getValue(), phoneNumber.getValue(), "gender not specified jey", EmployeeRole.WORKER, email.getValue());
            add(employee);
        }
        System.out.println(role);
        return valid;
    }
    
    public void add(Employee employee)
    {
        System.out.println(employee.getName() + " added to the system");
    }
    
    public String getFirstName()
    {
        return firstName.get();
    }
    
    public Model getModel()
    {
        return model;
    }
    
    public ViewState getViewState()
    {
        return viewState;
    }
    
    public String getFirstNameE()
    {
        return firstNameE.get();
    }
    
    public StringProperty firstNameEProperty()
    {
        return firstNameE;
    }
    
    public String getLastNameE()
    {
        return lastNameE.get();
    }
    
    public StringProperty lastNameEProperty()
    {
        return lastNameE;
    }
    
    public String getEmailE()
    {
        return emailE.get();
    }
    
    public StringProperty emailEProperty()
    {
        return emailE;
    }
    
    public String getRoleE()
    {
        return roleE.get();
    }
    
    public StringProperty roleEProperty()
    {
        return roleE;
    }
    
    public String getPhoneNumberE()
    {
        return phoneNumberE.get();
    }
    
    public StringProperty phoneNumberEProperty()
    {
        return phoneNumberE;
    }
    
    public String getPasswordE()
    {
        return passwordE.get();
    }
    
    public StringProperty passwordEProperty()
    {
        return passwordE;
    }
    
    public String getDobE()
    {
        return dobE.get();
    }
    
    public StringProperty dobEProperty()
    {
        return dobE;
    }
    
    public StringProperty firstNameProperty()
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName.get();
    }
    
    public StringProperty lastNameProperty()
    {
        return lastName;
    }
    
    public String getEmail()
    {
        return email.get();
    }
    
    public StringProperty emailProperty()
    {
        return email;
    }
    
    public String getRole()
    {
        return role.toString();
    }
    
    public ObservableList<EmployeeRole> roleProperty()
    {
        return role;
    }
    
    public String getPhoneNumber()
    {
        return phoneNumber.get();
    }
    
    public StringProperty phoneNumberProperty()
    {
        return phoneNumber;
    }
    
    public String getPassword()
    {
        return password.get();
    }
    
    public StringProperty passwordProperty()
    {
        return password;
    }
    
    public LocalDate getDob()
    {
        return dob.get();
    }
    
    public ObjectProperty<LocalDate> dobProperty()
    {
        return dob;
    }
    
    public String getJobTitle()
    {
        return jobTitle.get();
    }
    
    public StringProperty jobTitleProperty()
    {
        return jobTitle;
    }
}
