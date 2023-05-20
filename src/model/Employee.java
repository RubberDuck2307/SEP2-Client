package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A class representing an employee.
 * @author Anna Andrlova, Alex Bolfa, Cosmin Demian, Jan Metela, Arturs Ricards Rijnieks
 * @version 1.0 - May 2023
 */
public class Employee implements Serializable, IdObject {
    /**
     * The working number of the employee.
     */
    private Integer workingNumber;
    /**
     * The name of the employee.
     */
    private String name;
    /**
     * Date of birth
     */
    private LocalDate dob;
    /**
     * The phone number of the employee.
     */
    private String phoneNumber;
    /**
     * The gender of employee.
     */
    private String gender;
    /**
     * The role of the employee.
     */
    private EmployeeRole role;
    /**
     * The email of the employee.
     */
    private String email;

    private UserProfile userProfile;

    /**
     * 7-argument constructor for the class Employee. Does not set UserProfile.
     * @param workingNumber
     * @param name
     * @param dob
     * @param phoneNumber
     * @param gender
     * @param role
     * @param email
     */


    public Employee(Integer workingNumber, String name, LocalDate dob, String phoneNumber, String gender, EmployeeRole role, String email) {
        this.workingNumber = workingNumber;
        this.name = name;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.role = role;
        this.email = email;
    }

    /**
     * 8-argument constructor for the class Employee.
     * @param workingNumber
     * @param name
     * @param dob
     * @param phoneNumber
     * @param gender
     * @param role
     * @param email
     * @param userProfile
     */
    public Employee(Integer workingNumber, String name, LocalDate dob, String phoneNumber, String gender, EmployeeRole role, String email, UserProfile userProfile) {
        this.workingNumber = workingNumber;
        this.name = name;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.role = role;
        this.email = email;
        setUserProfile(userProfile);
    }

    /**
     * 6-argument constructor for the class Employee. Does not set UserProfile and working number.
     * @param name
     * @param dob
     * @param phoneNumber
     * @param gender
     * @param role
     * @param email
     */

    public Employee(String name, LocalDate dob, String phoneNumber, String gender, EmployeeRole role, String email) {
        this.name = name;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.role = role;
        this.email = email;
    }

    public Integer getWorkingNumber() {
        return workingNumber;
    }

    public void setWorkingNumber(Integer workingNumber) {
        this.workingNumber = workingNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Checks if the working number of the employee is the same as working number of the user profile. If it is, sets the user profile. If not, throws an exception.
     * @param userProfile
     * @throws RuntimeException
     */
    public void setUserProfile(UserProfile userProfile) {
        if (userProfile == null) {
            this.userProfile = null;
        } else if (!userProfile.getWorkingNumber().equals(this.workingNumber)) {
            throw new RuntimeException("Working number of user profile is not the same as employee's working number");
        } else {
            this.userProfile = userProfile;
        }
    }

    public String getEmail() {
        return email;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "workingNumber=" + workingNumber +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    @Override
    public Long getId() {
        return null;
    }
}
