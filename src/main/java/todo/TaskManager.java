package todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;


public class TaskManager {


    // Parser p=new Parser();
    TaskStorage taskStorage;
    private ArrayList<Task> tasks;
    private Iterator<Task> it;

    public TaskManager() {
        Date date = new Date(2018, 8, 21);
        tasks = new ArrayList<>();

        Task task = new Task("title", "project", date, true);
        tasks.add(task);
        taskStorage = new TaskStorage();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int toDoTasks() {
        return 5;
    }

    public int finishedTasks() {
        return 5;
    }


    public void tasksByProject(String project) throws IOException, ClassNotFoundException {
        ArrayList<Task> tasks = taskStorage.readFromFile("/Users/tmp-sda-1181/IdeaProjects/todo/Storage.txt");
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            if (project.equals(task.getProject())) {
                System.out.println(task.getProject() + " " + task.getTitle() + " " + task.getdueDate() + " " + task.getStatus());

            }
        }
    }

    public void tasksByDate() throws IOException, ClassNotFoundException {
        Collections.sort(tasks);
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            System.out.println(task.getProject() + " " + task.getTitle() + " " + task.getdueDate() + " " + task.getStatus());


        }


    }


    public void addNewTask(Task newTask) {

        tasks.add(newTask);
        //showtasks();
        System.out.println("Added the task");

        //p.printWelcome();
        // p.startProcessing();

    }

    public void showtasks() {
        it = tasks.iterator();

        while (it.hasNext()) {
            Task t = it.next();
            System.out.println("a");
            System.out.println(t.getProject() + t.getTitle() + t.getdueDate() + t.getStatus());
        }
    }
}
