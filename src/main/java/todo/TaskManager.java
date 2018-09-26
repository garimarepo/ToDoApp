/*This is the main controller class of ToDoApp.
It shows output to the user according to his selection.*/

package todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;


public class TaskManager {
    TaskStorage taskStorage;
    private ArrayList<Task> tasks;
    private Iterator<Task> it;

    public TaskManager() {
        Date date = new Date(2018, 8, 21);
        taskStorage = new TaskStorage();
        tasks = taskStorage.readFromFile("Storage.txt");


    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int toDoTasks() {
        Iterator<Task> it=tasks.iterator();
        int count=0;
        while(it.hasNext())
        {
            Task task=it.next();
            if(task.getStatus()==false)
            {
                count++;
            }
        }
        return count;
    }

    public int finishedTasks() {
        Iterator<Task> it=tasks.iterator();
        int count=0;
        while(it.hasNext())
        {
            Task task=it.next();
            if(task.getStatus()==true)
            {
                count++;
            }
        }
        return count;

    }
//Filtered the tasks according to the selected project name

    public void tasksByProject(String project) throws IOException, ClassNotFoundException {
        ArrayList<Task> tasks = taskStorage.readFromFile("/Users/tmp-sda-1181/IdeaProjects/ToDoApp/Storage.txt");
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            if (project.equals(task.getProject())) {
                printTask(task);
            }
        }
    }
    //Shows all the tasks sorted by date

    public void tasksByDate() throws IOException, ClassNotFoundException {
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

    public void printTask(Task task)
    {
        System.out.println("Project"+" "+"Title"+" "+"Due Date"+" "+"Status"+" "+"Id");
        System.out.println(task.getProject() + " " + task.getTitle() + " " + task.getdueDate() + " "
                + task.getStatus()+" "+task.getId());
    }


}
