package viewmodel;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import model.Model;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import model.*;
import util.Validator;

import java.time.LocalDate;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class CreateUserProfileViewModel implements ViewModel
{
    private Validator validator;
    private Model model;
    private ViewState viewState;
    private StringProperty firstName;
    private StringProperty firstNameE;
    private BooleanProperty firstNameB;
    private StringProperty lastName;
    private StringProperty lastNameE;
    private BooleanProperty lastNameD;
    private StringProperty email;
    private StringProperty emailE;
    private BooleanProperty emailB;
    private ObjectProperty<String> role;
    private StringProperty roleE;
    private BooleanProperty roleB;
    private StringProperty phoneNumber;
    private StringProperty phoneNumberE;
    private BooleanProperty phoneNumberB;
    private StringProperty password;
    private StringProperty passwordE;
    private BooleanProperty passwordB;
    private ObjectProperty<LocalDate> dob;
    private StringProperty dobE;
    private BooleanProperty dobB;
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
        this.role = new SimpleObjectProperty<>();
        this.roleE = new SimpleStringProperty("");
        this.phoneNumber = new SimpleStringProperty("");
        this.phoneNumberE = new SimpleStringProperty("");
        this.password = new SimpleStringProperty("");
        this.passwordE = new SimpleStringProperty("");
        LocalDate localDate = LocalDate.now();
        this.dob = new SimpleObjectProperty<>(localDate);
        this.dobE = new SimpleStringProperty("");
        this.jobTitle = new SimpleStringProperty("");
        validator = new Validator();
    }
    
    public boolean createUserProfile()
    {
        boolean valid = true;
        try
        {
            validator.validateFirstName(firstName);
            firstNameE.setValue("✓");
        }
        catch (Exception e)
        {
            valid = false;
            firstNameE.setValue(e.getMessage());
        }
        
        try
        {
            validator.validateLastName(lastName);
            lastNameE.setValue("✓");
        }
        catch (Exception e)
        {
            valid = false;
            lastNameE.setValue(e.getMessage());
        }
        try
        {
            validator.validateEmail(email.getValue());
            emailE.setValue("✓");
        }
        catch (Exception e)
        {
            valid = false;
            emailE.setValue(e.getMessage());
        }
        
        try
        {
            validator.validateChoiceBox(role.getValue());
            roleE.setValue("✓");
            
        }
        catch (Exception e)
        {
            valid = false;
            roleE.setValue(e.getMessage());
        }
        
        try
        {
            validator.validatePhoneNumber(phoneNumber.getValue());
            phoneNumberE.setValue("✓");
        }
        catch (Exception e)
        {
            valid = false;
            phoneNumberE.setValue(e.getMessage());
        }
        
        try
        {
            validator.validatePassword(password.getValue());
            passwordE.setValue("✓");
        }
        catch (Exception e)
        {
            valid = false;
            passwordE.setValue(e.getMessage());
        }
        
        try
        {
            validator.validateDOB(dob.getValue());
            dobE.setValue("✓");
        }
        catch (Exception e)
        {
            valid = false;
            dobE.setValue(e.getMessage());
        }
        
        if (valid)
        {
            //TODO choice-box and datepicker
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
    
    public ObjectProperty<String> roleProperty()
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
