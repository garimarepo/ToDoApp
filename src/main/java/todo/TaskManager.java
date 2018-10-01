/**
 * This is the main controller class of ToDoApp.
 * It shows output to the user according to his selection.
 */

package todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


public class TaskManager {
    private TaskStorage taskStorage;
    private ArrayList<Task> tasks;
    private Iterator<Task> it;

    public TaskManager() {
        taskStorage = new TaskStorage();
        tasks = taskStorage.readFromFile();
    }

    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int countToDoTasks() {
        return Long.valueOf(tasks.stream().filter(task -> !task.getStatus()).count()).intValue();
        
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
    public List<Task> displayTasksByProject(final String project) throws IOException, ClassNotFoundException {
        return tasks.stream().filter(task -> project.equals(task.getProject())).collect(Collectors.toList());
    }

    /**
     * Shows all the tasks sorted by date
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<Task> displayTasksByDate() throws IOException, ClassNotFoundException {
        Collections.sort(tasks);
        return tasks;
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


    public void saveToFile() throws IOException {
        taskStorage.saveToFile(tasks);
    }
}
