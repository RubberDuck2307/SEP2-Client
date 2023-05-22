package model;

import java.io.Serializable;

/**
 * Class representing assigned to project notification.
 * @author Anna Andrlova, Alex Bolfa, Cosmin Demian, Jan Metela, Arturs Ricards Rijnieks
 * @version 1.0 - May 2023
 */

public class ForgottenPasswordNotification implements Serializable, IdObject {
    /**
     * The id of the notification.
     */
    private Long id;
    /**
     * The working number of the employee who forgot his password.
     */
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
