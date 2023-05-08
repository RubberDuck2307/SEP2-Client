package viewmodel;

import javafx.beans.property.*;
import model.Model;
import model.*;
import util.Validator;

import java.time.LocalDate;

public class CreateUserProfileViewModel implements ViewModel
{
    private Validator validator;
    private Model model;
    private ViewState viewState;
    private StringProperty firstName;
    private StringProperty firstNameE;
    private BooleanProperty firstNameValue;
    private StringProperty lastName;
    private StringProperty lastNameE;
    private BooleanProperty lastNameValue;
    private StringProperty email;
    private StringProperty emailE;
    private BooleanProperty emailValue;
    private ObjectProperty<String> role;
    private StringProperty roleE;
    private BooleanProperty roleValue;
    private StringProperty phoneNumber;
    private StringProperty phoneNumberE;
    private BooleanProperty phoneNumberValue;
    private StringProperty password;
    private StringProperty passwordE;
    private BooleanProperty passwordValue;
    private ObjectProperty<LocalDate> dob;
    private StringProperty dobE;
    private BooleanProperty dobValue;
    private StringProperty jobTitle;
    private Integer workingNumber;
    
    CreateUserProfileViewModel(Model model, ViewState viewState)
    {
        this.model = model;
        this.viewState = viewState;
        load();
    }
    
    public boolean createUserProfile()
    {
        boolean valid = true;
        try
        {
            validator.validateFirstName(firstName.getValue());
            firstNameValue.setValue(true);
            firstNameE.setValue("✓");
        }
        catch (Exception e)
        {
            firstNameValue.setValue(false);
            valid = false;
            firstNameE.setValue(e.getMessage());
        }
        try
        {
            validator.validateLastName(lastName.getValue());
            lastNameValue.setValue(true);
            lastNameE.setValue("✓");
        }
        catch (Exception e)
        {
            lastNameValue.setValue(false);
            valid = false;
            lastNameE.setValue(e.getMessage());
        }
        try
        {
            validator.validateEmail(email.getValue());
            emailValue.setValue(true);
            emailE.setValue("✓");
        }
        catch (Exception e)
        {
            emailValue.setValue(false);
            valid = false;
            emailE.setValue(e.getMessage());
        }
        try
        {
            validator.validateChoiceBox(role.getValue());
            roleValue.setValue(true);
            roleE.setValue("✓");
        }
        catch (Exception e)
        {
            roleValue.setValue(false);
            valid = false;
            roleE.setValue(e.getMessage());
        }
        try
        {
            validator.validatePhoneNumber(phoneNumber.getValue());
            phoneNumberValue.setValue(true);
            phoneNumberE.setValue("✓");
        }
        catch (Exception e)
        {
            phoneNumberValue.setValue(false);
            valid = false;
            phoneNumberE.setValue(e.getMessage());
        }
        try
        {
            validator.validatePassword(password.getValue());
            passwordValue.setValue(true);
            passwordE.setValue("✓");
        }
        catch (Exception e)
        {
            passwordValue.setValue(false);
            valid = false;
            passwordE.setValue(e.getMessage());
        }
        try
        {
            validator.validateDOB(dob.getValue());
            dobValue.setValue(true);
            dobE.setValue("✓");
        }
        catch (Exception e)
        {
            dobValue.setValue(false);
            valid = false;
            dobE.setValue(e.getMessage());
        }
        if (valid)
        {
            add();
        }
        return valid;
    }
    
    private void add()
    {
        EmployeeRole selectedRole;
        switch (role.getValue())
        {
            case "Main Manager":
                selectedRole = EmployeeRole.MAIN_MANAGER;
                break;
            case "Project Manager":
                selectedRole = EmployeeRole.PROJECT_MANAGER;
                break;
            case "Worker":
                selectedRole = EmployeeRole.WORKER;
                break;
            case "HR":
                selectedRole = EmployeeRole.HR;
                break;
            default:
                throw new RuntimeException("No role matches");
        }
        Employee employee = new Employee(firstName.getValue() + " " + lastName.getValue(), dob.getValue(), phoneNumber.getValue(), "F", selectedRole, email.getValue());
        workingNumber = model.saveEmployee(employee, password.getValue());
    }
    
    public void load()
    {
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
        this.validator = new Validator();
        this.firstNameValue = new SimpleBooleanProperty(false);
        this.lastNameValue = new SimpleBooleanProperty(false);
        this.emailValue = new SimpleBooleanProperty(false);
        this.roleValue = new SimpleBooleanProperty(false);
        this.phoneNumberValue = new SimpleBooleanProperty(false);
        this.passwordValue = new SimpleBooleanProperty(false);
        this.dobValue = new SimpleBooleanProperty(false);
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
    
    public boolean getFirstNameValue()
    {
        return firstNameValue.get();
    }
    
    public BooleanProperty firstNameValueProperty()
    {
        return firstNameValue;
    }
    
    public boolean getLastNameValue()
    {
        return lastNameValue.get();
    }
    
    public BooleanProperty lastNameValueProperty()
    {
        return lastNameValue;
    }
    
    public boolean getEmailValue()
    {
        return emailValue.get();
    }
    
    public BooleanProperty emailValueProperty()
    {
        return emailValue;
    }
    
    public boolean getRoleValue()
    {
        return roleValue.get();
    }
    
    public BooleanProperty roleValueProperty()
    {
        return roleValue;
    }
    
    public boolean getPhoneNumberValue()
    {
        return phoneNumberValue.get();
    }
    
    public BooleanProperty phoneNumberValueProperty()
    {
        return phoneNumberValue;
    }
    
    public boolean getPasswordValue()
    {
        return passwordValue.get();
    }
    
    public BooleanProperty passwordValueProperty()
    {
        return passwordValue;
    }
    
    public boolean getDobValue()
    {
        return dobValue.get();
    }
    
    public BooleanProperty dobValueProperty()
    {
        return dobValue;
    }
    
    public Integer getWorkingNumber()
    {
        return workingNumber;
    }
    
}

