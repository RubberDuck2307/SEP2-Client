package viewmodel;

import javafx.beans.property.*;
import javafx.scene.image.Image;
import model.Model;
import model.*;
import util.Validator;

import java.time.LocalDate;
import java.util.Objects;

public class CreateUserProfileViewModel implements ViewModel
{
    private Validator validator;
    private ObjectProperty<Employee> employee;
    private ObjectProperty<Image> avatarPic;
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
    private BooleanProperty gender;
    private StringProperty name;
    private StringProperty workingNumberF;
    
    CreateUserProfileViewModel(Model model, ViewState viewState)
    {
        this.model = model;
        this.viewState = viewState;
        this.name = new SimpleStringProperty("");
        this.workingNumberF = new SimpleStringProperty("");
        
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
        this.dob = new SimpleObjectProperty<>();
        this.dobE = new SimpleStringProperty("");
        this.jobTitle = new SimpleStringProperty("");
        this.gender = new SimpleBooleanProperty(false);
        this.validator = new Validator();
        this.firstNameValue = new SimpleBooleanProperty(false);
        this.lastNameValue = new SimpleBooleanProperty(false);
        this.emailValue = new SimpleBooleanProperty(false);
        this.roleValue = new SimpleBooleanProperty(false);
        this.phoneNumberValue = new SimpleBooleanProperty(false);
        this.passwordValue = new SimpleBooleanProperty(false);
        this.dobValue = new SimpleBooleanProperty(false);
        this.employee=new SimpleObjectProperty<>();
        this.avatarPic=new SimpleObjectProperty<>();
    }

    public void reset(){
        this.firstName.setValue("");
        this.firstNameE.setValue("");
        this.lastName.setValue("");
        this.lastNameE.setValue("");
        this.email.setValue("");
        this.emailE.setValue("");
        this.roleE.setValue("");
        this.phoneNumber.setValue("");
        this.phoneNumberE.setValue("");
        this.password.setValue("");
        this.passwordE.setValue("");
        this.dobE.setValue("");
        this.jobTitle.setValue("");
        this.gender.setValue(true);
        this.firstNameValue.setValue(false);
        this.lastNameValue.setValue(false);
        this.emailValue.setValue(false);
        this.roleValue.setValue(false);
        this.phoneNumberValue.setValue(false);
        this.passwordValue.setValue(false);
        this.dobValue.setValue(false);
        load();

    }
    public void load()
    {
        employee.setValue(model.getUser());
        setAvatarPicture();
        LocalDate localDate = LocalDate.now();
        this.dob.setValue(localDate);
        this.name.setValue(model.getUser().getName());
        this.workingNumberF.setValue(model.getUser().getWorkingNumber().toString());
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
        EmployeeRole selectedRole = switch (role.getValue())
        {
            case "Main Manager" -> EmployeeRole.MAIN_MANAGER;
            case "Project Manager" -> EmployeeRole.PROJECT_MANAGER;
            case "Worker" -> EmployeeRole.WORKER;
            case "HR" -> EmployeeRole.HR;
            default -> throw new RuntimeException("No role matches");
        };
        String genderChar = "";
        if (gender.get())
        {
            genderChar = "M";
        }
        else genderChar = "F";
        System.out.println(genderChar);
        Employee employee = new Employee(firstName.getValue() + " " + lastName.getValue(), dob.getValue(), phoneNumber.getValue(), genderChar, selectedRole, email.getValue());
        workingNumber = model.saveEmployee(employee, password.getValue());
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
    public Employee getEmployeeProperty() {
        return employee.get();
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
    
    public boolean isGender()
    {
        return gender.get();
    }
    public BooleanProperty genderProperty()
    {
        return gender;
    }
    
    public String getName()
    {
        return name.get();
    }
    public StringProperty nameProperty()
    {
        return name;
    }
    public String getWorkingNumberF()
    {
        return workingNumberF.get();
    }
    public StringProperty workingNumberFProperty()
    {
        return workingNumberF;
    }
    public ObjectProperty<Image> avatarPicProperty()
    {
        return avatarPic;
    }
    public boolean isWoman(){
        return Objects.equals(employee.getValue().getGender(), "F");
    }
    public void setAvatarPicture(){
        if(isWoman()){
            avatarPic.setValue(new Image("/icons/woman-avatar.png"));
        }
        else{
            avatarPic.setValue(new Image("/icons/man-avatar.png"));
        }
    }

    public Employee getEmployee() {
        return employee.get();
    }

    public ObjectProperty<Employee> employeeProperty() {
        return employee;
    }
}

