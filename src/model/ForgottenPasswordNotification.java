package model;

import java.io.Serializable;

public class ForgottenPasswordNotification implements Serializable, IdObject {
    private Long id;
    private Integer workingNumber;
    public ForgottenPasswordNotification(Long id, Integer workingNumber) {
        this.id = id;
        this.workingNumber = workingNumber;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWorkingNumber() {
        return workingNumber;
    }

    public void setWorkingNumber(Integer workingNumber) {
        this.workingNumber = workingNumber;
    }
}
