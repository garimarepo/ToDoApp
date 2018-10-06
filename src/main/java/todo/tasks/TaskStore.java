package todo.tasks;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskStore implements Serializable {
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

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }
}
