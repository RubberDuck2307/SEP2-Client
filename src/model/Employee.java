package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Serializable {
    private Integer workingNumber;
    private String name;
    private LocalDate dob;
    private String phoneNumber;
    private String gender;

    public Employee(Integer workingNumber, String name, LocalDate dob, String phoneNumber, String gender) {
        this.workingNumber = workingNumber;
        this.name = name;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
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
