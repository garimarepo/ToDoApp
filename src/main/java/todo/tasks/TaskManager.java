/**
 * This is the main controller class of ToDoApp.
 * It shows output to the user according to his selection.
 */

package todo.tasks;

import todo.storage.StorageHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class TaskManager {
    private StorageHelper storageHelper;
    private TaskStore taskStore;

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
     * @return list of tasks of the project
     */
    public List<Task> tasksByProject(final String project) {
        return taskStore.getTasks().stream().filter(task -> project.equals(task.getProject())).collect(Collectors.toList());
    }

    /**
     * Shows all the tasks sorted by date
     *
     * @return list of tasks sorted by date
     */
    public List<Task> tasksByDate() {
        Collections.sort(taskStore.getTasks());
        return taskStore.getTasks();
    }

    /**
     * Update the title of the task with the given id
     *
     * @param id    the id of the task to be updated with new title
     * @param title the new value of title
     * @return true if found the task with the id otherwise returns false
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
     * @return true if found the task with the id otherwise returns false
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
     * @return true if found the task with the id otherwise returns false
     */
    public boolean updateTaskDueDate(int id, Date dueDate) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setDueDate(dueDate);
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
     * @return true if found the task with the id otherwise returns false
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
        Task task = getTaskById(id);
        if (task != null) {
            task.setStatus(true);
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
     * @param id of task to be removed
     */
    public void removeTask(int id) {
        Task task = getTaskById(id);
        getTasks().remove(task);
    }
}
