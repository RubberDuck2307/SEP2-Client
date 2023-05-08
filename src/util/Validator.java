package util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ChoiceBox;

import javax.print.DocFlavor;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator
{
    public void validateFirstName(String firstName)
    {
        if (firstName.trim().isEmpty())
        {
            throw new RuntimeException("First name cannot be empty!");
        }
        else if (firstName.trim().length() == 1)
        {
            throw new RuntimeException("First name has to be longer than one character!");
        }
    }
    
    public void validateLastName(String lastName)
    {
        if (lastName.trim().isEmpty())
        {
            throw new RuntimeException("Last name cannot be empty!");
        }
        else if (lastName.trim().length() == 1)
        {
            throw new RuntimeException("Last name has to be longer than one character!");
        }
    }
    
    public void validateName(String name)
    {
        if (name.trim().isEmpty())
        {
            throw new RuntimeException("Name cannot be empty!");
        }
        else if (name.trim().length() == 1)
        {
            throw new RuntimeException("Name has to be longer than one character!");
        }
    }
    
    public void validateEmail(String emailAddress)
    {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(emailAddress.trim());
        
        if (emailAddress.isEmpty())
        {
            throw new RuntimeException("Email cannot be empty!");
        }
        
        if (!matcher.matches())
        {
            throw new RuntimeException("Wrong email format!");
        }
    }
    
    public void validatePhoneNumber(String phoneNumber)
    {
        String patterns = "^\\+?\\d{1,20}$";
        String withoutSpaces = phoneNumber.replace(" ", "");
        Pattern pattern = Pattern.compile(patterns);
        Matcher matcher = pattern.matcher(withoutSpaces);
        
        if (phoneNumber.trim().isEmpty())
        {
            throw new RuntimeException("Phone number cannot be empty!");
        }
        
        if (!matcher.matches())
        {
            throw new RuntimeException("Wrong phone number format!");
        }
        
        if (withoutSpaces.charAt(0) == '+')
        {
            SimpleStringProperty removedPlus = new SimpleStringProperty(withoutSpaces.replace("+", ""));
            System.out.println(removedPlus.getValue());
        }
    }
    
    public void validatePassword(String password)
    {
        if (password.trim().isEmpty())
        {
            throw new RuntimeException("Password cannot be empty");
        }
        if (password.trim().length() < 5)
        {
            throw new RuntimeException("Password mast be longer than 4 characters");
        }
    }
    
    public void validateDOB(LocalDate date)
    {
        if (!date.isBefore(LocalDate.now()))
        {
            throw new RuntimeException("Date of birth has to be in the past");
        }
    }
    
    public void validateDeadline(LocalDate deadline)
    {
        if (deadline!=null && !deadline.isAfter(LocalDate.now()))
        {
            throw new RuntimeException("Deadline has to be in the future");
        }
    }
    
    public void validateChoiceBox(String choiceBox)
    {
        if (choiceBox.trim().isEmpty())
        {
            throw new RuntimeException("Choice box cannot be empty!");
        }
    }
    
    public void validateTitle(String title)
    {
        if (title==null || title.trim().isEmpty())
        {
            throw new RuntimeException("Title cannot be empty!");
        }
        else if (title.trim().length() == 1)
        {
            throw new RuntimeException("Title has to be longer than one character!");
        }
    }
    
    public void validateEstimatedTimer(String estimatedTime)
    {
        String patterns = "^[0-9]$";
        Pattern pattern = Pattern.compile(patterns);
        if(estimatedTime!=null && !estimatedTime.isEmpty()){
            Matcher matcher = pattern.matcher(estimatedTime.trim());
            if (!matcher.matches())
            {
                throw new RuntimeException("Estimated time cannot contain letters!");
            }
        }
    }
}
