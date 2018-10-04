package todo;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;


public class TaskManagerTest {
    TaskManager taskManager;

    @Before
    public void setUp() {
        Task task1 = new Task(1, "presentation", "history", Date.valueOf("2018-11-11"), false);
        Task task2 = new Task(2, "essay", "history", Date.valueOf("2018-11-12"), false);
        Task task3 = new Task(3, "presentation", "english", Date.valueOf("2018-12-11"), true);
        ArrayList<Task> tasks=new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        taskManager=new TaskManager(tasks);
    }


    @Test
    public void countToDoTasks() {
        assertTrue(taskManager.countToDoTasks()==2);
    }

    @Test
    public void countFinishedTasks(){
        assertTrue(taskManager.countFinishedTasks()==1);
    }


    @Test
    public void tasksByProject() throws IOException, ClassNotFoundException {
        assertTrue(taskManager.tasksByProject("history").size() == 2);
    }

    @Test
    public void tasksByDate() {
    }

    @Test
    public void changeStatus() {
        taskManager.changeStatus(3);
        assertTrue(taskManager.getTaskById(3).getStatus()==false);
    }

    @Test
    public void removeTask() throws IOException, ClassNotFoundException {
        taskManager.removeTask(2);
        assertTrue(taskManager.getTaskById(2)==null);
    }

    @Test
    public void getNewTaskId()
    {
        assertTrue(taskManager.getNewTaskId()==4);
    }


}
