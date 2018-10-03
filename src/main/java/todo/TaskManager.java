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

    public TaskManager() throws IOException, ClassNotFoundException {
        storageHelper = new StorageHelper();
        taskStore = storageHelper.readFromFile();
    }

    public TaskManager(ArrayList<Task> tasks) {
        taskStore = new TaskStore();
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
    public List<Task> tasksByProject(final String project) throws IOException, ClassNotFoundException {
        return taskStore.getTasks().stream().filter(task -> project.equals(task.getProject())).collect(Collectors.toList());
    }

    /**
     * Shows all the tasks sorted by date
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<Task> tasksByDate() throws IOException, ClassNotFoundException {
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

    public void changeStatus(int id)      {

        ArrayList<Task> tasks = getTasks();
        Iterator it = tasks.iterator();
        while (it.hasNext()) {
            Task task = (Task) it.next();
            if (id == task.getId()) {
                task.setStatus(true);
                break;
            }
        }

    }

    public Task getTaskById(int id) {
        ArrayList<Task> tasks = getTasks();
        for (Task task : tasks) {
            if (id == task.getId()) {
                return task;
            }
        }
        return null;
    }

    /**
     * Remove the task corresponding to the selected id
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void removeTask(int id) throws IOException, ClassNotFoundException {
        ArrayList<Task> tasks = getTasks();
        Iterator it = tasks.iterator();
        while (it.hasNext()) {
            Task task = (Task) it.next();
            if (id == task.getId()) {
                it.remove();
                break;
            }
        }
    }


}
