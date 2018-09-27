/**
 * This is the main controller class of ToDoApp.
 * It shows output to the user according to his selection.
 */

package todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class TaskManager {
    private TaskStorage taskStorage;
    private ArrayList<Task> tasks;
    private Iterator<Task> it;

    public TaskManager() {
        taskStorage = new TaskStorage();
        tasks = taskStorage.readFromFile();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int countToDoTasks() {
        Iterator<Task> it = tasks.iterator();
        int count = 0;
        while (it.hasNext()) {
            Task task = it.next();
            if (task.getStatus() == false) {
                count++;
            }
        }
        return count;
    }

    public int countFinishedTasks() {
        Iterator<Task> it = tasks.iterator();
        int count = 0;
        while (it.hasNext()) {
            Task task = it.next();
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
    public void displayTasksByProject(String project) throws IOException, ClassNotFoundException {
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            if (project.equals(task.getProject())) {
                printTask(task);
            }
        }
    }

    /**
     * Shows all the tasks sorted by date
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void displayTasksByDate() throws IOException, ClassNotFoundException {
        System.out.println("b");
        Collections.sort(tasks);
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            printTask(task);
        }
    }

    public int getNewTaskId() {
        int max = 0;
        it = tasks.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            if (max < task.getId()) {
                max = task.getId();
            }
        }
        return ++max;
    }


    public void addNewTask(Task newTask) {
        tasks.add(newTask);
        System.out.println("Added the task");
    }

    /**
     * print details of task object.
     * @param task The object for which the details are printed.
     */

    public void printTask(Task task) {
        System.out.println("------------------------------");
        System.out.println("Task Id: " + task.getId());
        System.out.println("Project: " + task.getProject());
        System.out.println("Title: " + task.getTitle());
        System.out.println("Due Date: " + task.getdueDate());
        System.out.println("Status: " + task.getStatus());
    }

    public void saveToFile() throws IOException {
        taskStorage.saveToFile(tasks);
    }
}
