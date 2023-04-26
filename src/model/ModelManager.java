package model;


import mediator.ClientInterface;

import java.sql.SQLException;

public class ModelManager implements Model {

    ClientInterface client;

    public ModelManager(ClientInterface client) {
        this.client = client;
    }

    @Override
    public TaskList getAllTasksOfProject(Long id) {
        return client.getAllTasksOfProject(id);
    }

    @Override
    public ProjectList getAllProjectsByWorkingNumber(Integer workingNumber) {
        return client.getAllProjectsByWorkingNumber(workingNumber);
    }
}
