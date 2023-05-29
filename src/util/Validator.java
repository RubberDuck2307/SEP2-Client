package util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;
import model.Tag;

import javax.print.DocFlavor;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class for validating user input.
 */
public class Validator
{
    /**
     * checks if the firstname is not an empty string or a string with only one character. If it is it throws an exception.
     * @param firstName
     */
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

    /**
     * checks if the lastname is not an empty string or a string with only one character. If it is it throws an exception.
     * @param lastName
     */
    
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

    /**
     * checks if the name is not an empty string or a string with only one character. If it is it throws an exception.
     * @param name
     */
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

    /**
     * checks if the email is not an empty string and if it is in valid format.If it is it throws an exception.
     * @param emailAddress
     */
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


    /**
     * checks if the phone number is not an empty string and if it is in valid format.If it is it throws an exception.
     * @param phoneNumber
     */
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
        }
    }
    /**
     * checks if the password is not an empty string and if it is longer than 4 characters.If it is it throws an exception.
     */
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

    /**
     * checks if the date of birth is in the past. If it is not throws an exception.
     * @param date
     */
    public void validateDOB(LocalDate date)
    {
        if (!date.isBefore(LocalDate.now()))
        {
            throw new RuntimeException("Date of birth has to be in the past");
        }
    }

    /**
     * checks if the deadline of the project is in the future. If it is not throws an exception.
     * @param deadline
     */
    public void validateDeadline(LocalDate deadline)
    {
        if (deadline!=null && !deadline.isAfter(LocalDate.now()))
        {
            throw new RuntimeException("Deadline has to be in the future");
        }
    }

    /**
     * checks if the deadline of the task is not after the deadline of the project. If it is throws an exception.
     * @param deadlineOfTheTask
     * @param deadlineOfTheProject
     */
    public void validateTaskDeadline(LocalDate deadlineOfTheTask, LocalDate deadlineOfTheProject)
    {
        if (deadlineOfTheProject == null)
        {
            return;
        }
        if (deadlineOfTheTask.isAfter(deadlineOfTheProject))
        {
            throw new RuntimeException("Deadline of the task can not be after the deadline of the project");
        }
    }


    /**
     * checks if the choice box is not empty. If it is throws an exception.
     * @param choiceBox
     */
    public void validateChoiceBox(String choiceBox)
    {
        if (choiceBox.trim().isEmpty())
        {
            throw new RuntimeException("Choice box cannot be empty!");
        }
    }

    /**
     * checks if the title is not an empty string or a string with only one character. If it is throws an exception.
     * @param title
     */
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

    /**
     * checks if the estimated time does not contain letters. If it does, throws an exception.
     * @param estimatedTime
     */
    public void validateEstimatedTimer(String estimatedTime)
    {
        String patterns = "^( )?[0-9]*$";
        Pattern pattern = Pattern.compile(patterns);
        if(estimatedTime!=null && !estimatedTime.isEmpty()){
            Matcher matcher = pattern.matcher(estimatedTime.trim());
            if (!matcher.matches())
            {
                throw new RuntimeException("Estimated time cannot contain letters!");
            }
        }
    }

    /**
     * check if the color is null. If it is throws an exception.
     * @param color
     */
    public void validateColor(Color color)
    {
        if (color==null)
        {
            throw new RuntimeException("Color cannot be empty!");
        }

    }

    /**
     * checks if name or color of the tag are null. If they are throws an exception.
     * @param tag
     */

    public void validateTag(Tag tag)
    {
        if (tag==null)
        {
            throw new RuntimeException("Tag cannot be empty!");
        }
        if(tag.getName().isEmpty()){
            throw new RuntimeException("Tag name cannot be empty!");
        }
        if(tag.getColor()==null){
            throw new RuntimeException("Tag color cannot be empty!");
        }
        //        if(color.getBrightness()<0.5){
        //            throw new RuntimeException("Color cannot be too dark!");
        //        }
        //        if(color.getSaturation()<0.3){
        //            throw new RuntimeException("Color cannot be too pale!");
        //        }
    }
}
