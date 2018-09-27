/**
 * Accept & parse input from the user.
 */

package todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class Parser {
    private static final String WELCOME_MSG = "ToDoList: Choose an option";
    private BufferedReader input;
    private TaskManager tasksManager;

    public Parser() {
        input = new BufferedReader(new InputStreamReader(System.in));
        tasksManager = new TaskManager();
    }

    public void printWelcome() {
        {
            System.out.println();
            System.out.println("*********************************");
            System.out.println(WELCOME_MSG);
            System.out.println("You have " + tasksManager.countToDoTasks() + " to do tasks and " +
                    tasksManager.countFinishedTasks() + " tasks are done");
            System.out.println("Pick an option");
            System.out.println("(1) Show Task List (by date or project)");
            System.out.println("(2) Add New Task");
            System.out.println("(3) Edit Task (update, mark as done, remove)");
            System.out.println("(4) Save");
            System.out.println("(5) Quit");
        }
    }

    /**
     * According to the user's chosenTask input, the function manipulates the corresponding functionality
     *
     * @param chosenTask, the number entered by the user for a particular functionality
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public void startProcessing(int chosenTask) throws IOException, ClassNotFoundException, ParseException {

        switch (chosenTask) {
            case 1:
                showTask();
                break;
            case 2:
                addTask();
                break;
            case 3:
                editTask();
                break;
            case 4: {
                tasksManager.saveToFile();
                System.out.println("Your task has saved and here is the new list");
                tasksManager.displayTasksByDate();
                break;
            }
        }
    }

    public int getUserOption() {
        return Integer.parseInt(userInput());
    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void showTask() throws IOException, ClassNotFoundException {
        System.out.println("By date OR by project");
        String input = userInput();
        if (input.equals("date")) {
            tasksManager.displayTasksByDate();
        } else if (input.equals("project")) {
            System.out.println("Enter project name");
            String inputProject = userInput();
            tasksManager.displayTasksByProject(inputProject);
        }
    }


    private void addTask() throws ParseException {
        System.out.println("Enter task title");
        String title = userInput();
        System.out.println("Enter project");
        String project = userInput();
        System.out.println("Enter Due Date");
        String inputDate = userInput();
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(inputDate);
        } catch (java.text.ParseException e) {
            System.out.println("wrong format, please try again");
            date = new SimpleDateFormat("dd/MM/yyyy").parse(inputDate);
            return;
        }
        System.out.println("Enter status: false for incomplete and true for complete");
        String inputStatus = userInput();
        boolean status = Boolean.valueOf(inputStatus);
        tasksManager.addNewTask(new Task(tasksManager.getNewTaskId(), title, "a", new Date(), status));
    }

    private void changeStatus() throws IOException, ClassNotFoundException {
        System.out.println("Choose task(id) for mark as done");
        String markAsDoneInput = userInput();
        int id = Integer.parseInt(markAsDoneInput);
        ArrayList<Task> tasks = tasksManager.getTasks();
        Iterator it = tasks.iterator();
        while (it.hasNext()) {
            Task task = (Task) it.next();
            if (id == task.getId()) {
                task.setStatus(false);
            }
        }
        System.out.println("Your task is now mark as done that is changed the status from true to false" +
                " and here is the new list");
        tasksManager.displayTasksByDate();
    }

    private void removeTask() throws IOException, ClassNotFoundException {
        System.out.println("Choose task(id) for removing");
        String removeInput = userInput();
        int id = Integer.parseInt(removeInput);
        ArrayList<Task> tasks = tasksManager.getTasks();
        Iterator it = tasks.iterator();
        while (it.hasNext()) {
            Task task = (Task) it.next();
            if (id == task.getId()) {
                it.remove();
            }
        }
        System.out.println("Your task is removed. Now the new list is:");
        tasksManager.displayTasksByDate();
    }

    private void editTask() throws IOException, ClassNotFoundException {
        unfinishedTasks();
        System.out.println("Type 1 for update, 2 for mark as done and 3 for remove");
        String editInput = userInput();
        if (editInput.equals("2")) {
            changeStatus();
        } else if (editInput.equals("3")) {
            removeTask();
        }
    }


    private String userInput() {
        String inputLine;
        System.out.print("> ");
        inputLine = null;
        try {
            inputLine = input.readLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return inputLine;
    }

    private void unfinishedTasks() {
        ArrayList<Task> tasks = tasksManager.getTasks();
        Iterator it = tasks.iterator();
        while (it.hasNext()) {
            Task task = (Task) it.next();
            if (task.getStatus() == false) {
                tasksManager.printTask(task);
            }
        }
    }
}

