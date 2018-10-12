/**
 * Models the object for storage in file with unique Id generation for each object.
 */
package todo.tasks;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskStore implements Serializable {
    private static final long serialVersionUID = 6529685098267757691L;
    private ArrayList<Task> tasks;
    private int maxId;

    public TaskStore() {
        tasks = new ArrayList<>();
        maxId = 0;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getMaxId() {
        return maxId++;
    }

}
