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
        assertTrue(taskManager.getTaskById(3).getStatus()==true);
    }

    @Test
    public void removeTask() throws IOException, ClassNotFoundException {
        taskManager.removeTask(2);
        assertTrue(taskManager.getTaskById(2)==null);
    }


    @Test
    public void tasksByProjectWhenProjectNotExist() throws IOException, ClassNotFoundException {
        assertTrue(taskManager.tasksByProject("blah").size() == 0);
    }

    @Test
    public void countToDoTasksWhenNoTaskExist(){
        ArrayList<Task> tasks=new ArrayList<>();
        taskManager=new TaskManager(tasks);
        assertTrue(taskManager.countToDoTasks()==0);
    }

    @Test
    public void countFinishedTasksWhenNoTaskExist(){
        ArrayList<Task> tasks=new ArrayList<>();
        taskManager=new TaskManager(tasks);
        assertTrue(taskManager.countFinishedTasks()==0);
    }

    @Test
    public void updateTaskTitle(){
        assertTrue(taskManager.updateTaskTitle(1,"speech")==true);
    }

    @Test
    public void updateTaskTitleWhenNoTaskExist(){
        assertTrue(taskManager.updateTaskTitle(5,"speech")==false);
    }

    @Test
    public void updateTaskProject(){
        assertTrue(taskManager.updateTaskProject(1,"math")==true);
    }

    @Test
    public void updateTaskProjectWhenNoTaskExist(){
        assertTrue(taskManager.updateTaskProject(5,"math")==false);
    }

    @Test
    public void updateTaskDueDate(){
        assertTrue(taskManager.updateTaskDueDate(1,Date.valueOf("2018-12-11"))==true);
    }

    @Test
    public void updateTaskDueDateWhenNoTaskExist(){
        assertTrue(taskManager.updateTaskDueDate(5, Date.valueOf("2018-12-11"))==false);
    }

    @Test
    public void updateTaskStatus(){
        assertTrue(taskManager.updateTaskStatus(1,true)==true);
    }

    @Test
    public void updateTaskStatusWhenNoTaskExist(){
        assertTrue(taskManager.updateTaskStatus(5,true)==false);
    }

    @Test
    public void getTaskById(){
        assertTrue(taskManager.getTaskById(1).getId() == 1);
    }

    @Test
    public void getTaskByIdWhenNoTaskExist(){
        assertTrue(taskManager.getTaskById(9) == null);
    }

}
