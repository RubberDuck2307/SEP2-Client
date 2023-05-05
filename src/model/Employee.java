package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Serializable {
    private Integer workingNumber;
    private String name;
    private LocalDate dob;
    private String phoneNumber;
    private String gender;
    private EmployeeRole role;
    private String email;

    private UserProfile userProfile;

    public Employee(Integer workingNumber, String name, LocalDate dob, String phoneNumber, String gender, EmployeeRole role, String email) {
        this.workingNumber = workingNumber;
        this.name = name;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.role = role;
        this.email = email;
    }

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
}
