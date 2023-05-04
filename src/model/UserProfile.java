package model;

import java.io.Serializable;

public class UserProfile implements Serializable {

    public UserProfile(Integer workingNumber, String password) {
        this.workingNumber = workingNumber;
        this.password = password;
    }

    private Integer workingNumber;

    private String password;


    public Integer getWorkingNumber() {
        return workingNumber;
    }

    public void setWorkingNumber(Integer workingNumber) {
        this.workingNumber = workingNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}






