import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.Validator;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest
{
    
    @BeforeEach
    void setUp()
    {
        System.out.println("--> setUp()");
        Validator validator = new Validator();
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
        try
        {
            validator.validateFirstName(string);
        }
        catch (Exception e)
        {
            assertThrows(RuntimeException.class, () -> validator.validateLastName(string));
        }
    }
    
    @Test
    void validateLastNameOne()
    {
        String string = "a";
        Validator validator = new Validator();
        try
        {
            validator.validateFirstName(string);
        }
        catch (Exception e)
        {
            assertThrows(RuntimeException.class, () -> validator.validateLastName(string));
        }
    }
    @Test
    void validateLastNameMany()
    {
        String string = "abcdef";
        Validator validator = new Validator();
        try
        {
            validator.validateFirstName(string);
        }
        catch (Exception e)
        {
            assertThrows(RuntimeException.class, () -> validator.validateLastName(string));
        }
    }
    
}