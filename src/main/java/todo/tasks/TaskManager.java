/**
 * This is the main controller class of ToDoApp.
 * It shows output to the user according to his selection.
 */

package todo.tasks;

import todo.storage.StorageHelper;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class TaskManager {
    private StorageHelper storageHelper;
    private TaskStore taskStore;
    //private Iterator<Task> it;

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

    /**
     * Update the title of the task with the given id
     *
     * @param id    the id of the task to be updated with new title
     * @param title the new value of title
     * @return
     */

    public boolean updateTaskTitle(int id, String title) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setTitle(title);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Update the project of the task with the given id
     *
     * @param id      the id of the task to be updated with new project name
     * @param project the new value of project name
     * @return
     */
    public boolean updateTaskProject(int id, String project) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setProject(project);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Update the due date of the task with the given id
     *
     * @param id      the id of the task to be updated with new due date
     * @param dueDate the new value of due date
     * @return
     */
    public boolean updateTaskDueDate(int id, Date dueDate) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setdueDate(dueDate);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Update the status of the task with the given id
     *
     * @param id     the id of the task to be updated with new status
     * @param status the new value of status
     * @return
     */
    public boolean updateTaskStatus(int id, boolean status) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setStatus(status);
            return true;
        } else {
            return false;
        }
    }


    public int getNewTaskId() {
        return taskStore.getMaxId();
    }


    public void addNewTask(String title, String project, Date date, boolean status) {
        Task task = new Task(getNewTaskId(), title, project, date, status);
        taskStore.getTasks().add(task);
    }


    public void saveToFile() throws IOException {
        storageHelper.saveToFile(taskStore);
    }

    /**
     * Change the status of task to true
     *
     * @param id the id of task to be updated to value true
     */
    public void changeStatus(int id) {

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

    /**
     * Returns the task corresponding to the given id
     *
     * @param id the id of task to be returned
     * @return task if present in the list otherwise return null
     */

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
