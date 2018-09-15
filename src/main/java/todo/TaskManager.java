package todo;

import java.util.ArrayList;

public class TaskManager {
    ArrayList<Task> tasks;

    public TaskManager()
    {
        tasks=new ArrayList<>();
    }
    public int toDoTasks()
    {
        return 5;
    }
    public int finishedTasks()
    {
       return 5;
    }

    public void tasksByDate()
    {
        System.out.println("called tasksbydate");
    }
    public void tasksByProject()
    {
        System.out.println("called tasksbyproject");
    }



}
