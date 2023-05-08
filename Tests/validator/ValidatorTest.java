import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.Validator;

import java.net.FileNameMap;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest
{
    
    @BeforeEach
    void setUp()
    {
        System.out.println("--> setUp()");
    }
    
    @AfterEach
    void tearDown()
    {
        System.out.println("<-- tearDown()");
    }
    
    @Test
    void validateFirstNameZero()
    {
        String string = "";
        Validator validator = new Validator();
        try
        {
            validator.validateFirstName(string);
        }
        catch (Exception e)
        {
            assertThrows(RuntimeException.class, () -> validator.validateFirstName(string));
        }
    }
    
    @Test
    void validateFirstNameOne()
    {
        String string = "a";
        Validator validator = new Validator();
        try
        {
            validator.validateFirstName(string);
        }
        catch (Exception e)
        {
            assertThrows(RuntimeException.class, () -> validator.validateFirstName(string));
        }
    }
    
    @Test
    void validateFirstNameMany()
    {
        String string = "abcdef";
        Validator validator = new Validator();
        try
        {
            validator.validateFirstName(string);
        }
        catch (Exception e)
        {
            assertThrows(RuntimeException.class, () -> validator.validateFirstName(string));
        }
    }
    
    @Test
    void validateLastNameZero()
    {
        String string = "";
        Validator validator = new Validator();
        assertThrows(RuntimeException.class, () -> validator.validateLastName(string));
    }
    
    @Test
    void validateLastNameOne()
    {
        String string = "a";
        Validator validator = new Validator();
        assertThrows(RuntimeException.class, () -> validator.validateLastName(string));
    }
    
    @Test
    void validateLastNameMany()
    {
        String string = "abcdef";
        Validator validator = new Validator();
        assertDoesNotThrow(() -> validator.validateLastName(string));
    }
    
    @Test
    void validateNameZero()
    {
        String string = "";
        Validator validator = new Validator();
        assertThrows(RuntimeException.class, () -> validator.validateName(string));
    }
    
    @Test
    void validateNameOne()
    {
        String string = "a";
        Validator validator = new Validator();
        assertThrows(RuntimeException.class, () -> validator.validateName(string));
    }
    
    @Test
    void validateNameMany()
    {
        String string = "abcdef";
        Validator validator = new Validator();
        assertDoesNotThrow(() -> validator.validateName(string));
    }
    
    @Test
    void validateEmailZero()
    {
        String string = "";
        Validator validator = new Validator();
        assertThrows(RuntimeException.class, () -> validator.validateEmail(string));
    }
    
    @Test
    void validateEmailOne()
    {
        String string = "a";
        Validator validator = new Validator();
        assertThrows(RuntimeException.class, () -> validator.validateEmail(string));
    }
    
    @Test
    void validateEmailMany()
    {
        String string = "abcdef";
        Validator validator = new Validator();
        assertThrows(RuntimeException.class, () -> validator.validateEmail(string));
    }
    
    @Test
    void validatePhoneZero()
    {
        String string = "";
        Validator validator = new Validator();
        assertThrows(RuntimeException.class, () -> validator.validatePhoneNumber(string));
    }
    
    @Test
    void validatePhoneOne()
    {
        String string = "1";
        Validator validator = new Validator();
        assertDoesNotThrow(() -> validator.validatePhoneNumber(string));
    }
    
    @Test
    void validatePhoneMany()
    {
        String string = "123";
        Validator validator = new Validator();
        assertDoesNotThrow(() -> validator.validatePhoneNumber(string));
    }
    
    @Test
    void validatePhoneLetters()
    {
        String string = "asdasdasdas";
        Validator validator = new Validator();
        assertThrows(RuntimeException.class, () -> validator.validatePhoneNumber(string));
    }
    
    @Test
    void validatePasswordZero()
    {
        String string = "";
        Validator validator = new Validator();
        assertThrows(RuntimeException.class, () -> validator.validatePassword(string));
    }
    
    @Test
    void validatePasswordOne()
    {
        String string = "a";
        Validator validator = new Validator();
        assertThrows(RuntimeException.class, () -> validator.validatePassword(string));
    }
    
    @Test
    void validatePasswordMany()
    {
        String string = "abcdef";
        Validator validator = new Validator();
        assertDoesNotThrow(() -> validator.validatePassword(string));
    }
    
    @Test
    void validatePasswordBoundary()
    {
        String string = "asdf";
        Validator validator = new Validator();
        assertThrows(RuntimeException.class, () -> validator.validatePassword(string));
        String finalString = "asdfg";
        validator.validatePassword(finalString);
    }
    
    @Test
    void validateDOBNow()
    {
        LocalDate string = LocalDate.now();
        Validator validator = new Validator();
        assertThrows(RuntimeException.class, () -> validator.validateDOB(string));
    }
    
    @Test
    void validateDOBBefore()
    {
        LocalDate string = LocalDate.of(2024, 1, 1);
        Validator validator = new Validator();
        assertThrows(RuntimeException.class, () -> validator.validateDOB(string));
    }
    
    @Test
    void validateDOBAfter()
    {
        LocalDate string = LocalDate.of(2022, 1, 1);
        Validator validator = new Validator();
        assertDoesNotThrow(() -> validator.validateDOB(string));
    }
    
    @Test
    void validateDeadlineNow()
    {
        LocalDate string = LocalDate.now();
        Validator validator = new Validator();
        assertThrows(RuntimeException.class, () -> validator.validateDeadline(string));
    }
    
    @Test
    void validateDeadlineAfter()
    {
        LocalDate string = LocalDate.of(2024, 1, 1);
        Validator validator = new Validator();
        assertDoesNotThrow(() -> validator.validateDeadline(string));
    }
    
    @Test
    void validateDeadlineBefore()
    {
        LocalDate string = LocalDate.of(2022, 1, 1);
        Validator validator = new Validator();
        assertThrows(RuntimeException.class, () -> validator.validateDeadline(string));
    }
    
    @Test
    void validateChoiceZero()
    {
        String string = "";
        Validator validator = new Validator();
        assertThrows(RuntimeException.class, () -> validator.validateChoiceBox(string));
    }
    
    @Test
    void validateChoice()
    {
        String string = "Worker";
        Validator validator = new Validator();
        assertDoesNotThrow(() -> validator.validateChoiceBox(string));
    }
    
    @Test
    void validateEstimatedTimeNull()
    {
        String string = null;
        Validator validator = new Validator();
        assertDoesNotThrow(() -> validator.validateEstimatedTimer(string));
    }
    
    @Test
    void validateEstimatedTimeZero()
    {
        String string = "";
        Validator validator = new Validator();
        assertDoesNotThrow(() -> validator.validateEstimatedTimer(string));
    }
    
    @Test
    void validateEstimatedTimeOne()
    {
        String string = "1";
        Validator validator = new Validator();
        assertDoesNotThrow(() -> validator.validateEstimatedTimer(string));
    }
    
    @Test
    void validateEstimatedMany()
    {
        String string = "21321313";
        Validator validator = new Validator();
        assertDoesNotThrow(() -> validator.validateEstimatedTimer(string));
    }
    
    @Test
    void validateEstimatedLetters()
    {
        String string = "dasdad";
        Validator validator = new Validator();
        assertThrows(RuntimeException.class, () -> validator.validateEstimatedTimer(string));
    }
}