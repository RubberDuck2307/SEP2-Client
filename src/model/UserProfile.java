package model;

import java.io.Serializable;

/**
 * A class representing a user profile.
 */
public class UserProfile implements Serializable, IdObject {
    /**
     * working number of the user
     */
    private Integer workingNumber;

    /**
     * password of the user
     */
    private String password;

    /**
     * 2-argument constructor for the class UserProfile.
     * @param workingNumber
     * @param password
     */
    public UserProfile(Integer workingNumber, String password) {
        this.workingNumber = workingNumber;
        this.password = password;
    }

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


    @Override
    public Long getId() {
        return workingNumber.longValue();
    }
}




