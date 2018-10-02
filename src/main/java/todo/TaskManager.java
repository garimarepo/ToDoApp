/**
 * This is the main controller class of ToDoApp.
 * It shows output to the user according to his selection.
 */

package todo;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class TaskManager {
    private StorageHelper storageHelper;
    private TaskStore taskStore;
    private Iterator<Task> it;

    public TaskManager() {
        storageHelper = new StorageHelper();
        taskStore = storageHelper.readFromFile();
    }

    public TaskManager(ArrayList<Task> tasks) {
        this.taskStore.setTasks(tasks);
    }

    public ArrayList<Task> getTasks() {
        return taskStore.getTasks();
    }

    public int countToDoTasks() {
        return Long.valueOf(taskStore.getTasks().stream().filter(task -> !task.getStatus()).count()).intValue();
        
    }

    public int countFinishedTasks() {
        int count = 0;
        for (Task task : taskStore.getTasks()) {
            if (task.getStatus() == true) {
                count++;
            }
        }
        return count;
    }

    /**
     * Filter tasks based on project name
     *
     * @param project the name of project
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<Task> displayTasksByProject(final String project) throws IOException, ClassNotFoundException {
        return taskStore.getTasks().stream().filter(task -> project.equals(task.getProject())).collect(Collectors.toList());
    }

    /**
     * Shows all the tasks sorted by date
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<Task> displayTasksByDate() throws IOException, ClassNotFoundException {
        Collections.sort(taskStore.getTasks());
        return taskStore.getTasks();
    }



    public int getNewTaskId() {
        return taskStore.getMaxId();
    }


    public void addNewTask(String title, String project, Date date, boolean status) {
        Task task = new Task(getNewTaskId(), title, project, date, status);
        taskStore.getTasks().add(task);
        System.out.println("Added the task");
    }


    public void saveToFile() throws IOException {
        storageHelper.saveToFile(taskStore);
    }
}
