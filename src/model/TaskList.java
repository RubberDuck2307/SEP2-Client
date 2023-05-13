package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A class representing a list of tasks.
 * @author Anna Andrlova, Alex Bolfa, Cosmin Demian, Jan Metela, Arturs Ricards Rijnieks
 * @version 1.0 - May 2023
 */
public class TaskList implements Serializable {

    private ArrayList<Task> tasksList;

    /**
     * 1-argument constructor sets the taskList to the given arrayList.
     * @param tasksList
     */
    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    /**
     * No-argument constructor sets the taskList to a new empty arrayList.
     */

    public TaskList() {
        tasksList = new ArrayList<>();
    }

    /**
     *
     * @param id
     * @return Task with the given id
     * @throws RuntimeException if no task with such id found
     */

    public Task getTaskById(Long id) {
        for (Task task : tasksList) {
            if (Objects.equals(task.getId(), id)) {
                return task;
            }
        }
        throw new RuntimeException("No task with such id found");
    }

    /**
     * Replaces first task that has a same id by the task given as a parameter
     * @param task
     **/
    public void replaceTaskById(Task task) {
        for (Task currentTask : tasksList) {
            if (Objects.equals(currentTask.getId(), task.getId())) {
                currentTask = task;
                return;
            }
        }
        throw new RuntimeException("No task with such id found");
    }

    /**
     * Adds a task to the list.
     * @param task
     */

    public void addTask(Task task) {
        tasksList.add(task);
    }
    /**
     * @param
     * @return task on the given index
     */

    public Task getTask(int index) {
        return tasksList.get(index);
    }

    /**
     *
     * @return the size of the list
     */

    public int size() {
        return tasksList.size();
    }

    /**
     *
     * @param task
     * @return true if the list contains the task given as a parameter and false otherwise
     */
    public boolean contains(Task task) {
        return tasksList.contains(task);
    }

    @Override
    public String toString() {
        return "{" + tasksList +
                '}';
    }
}
