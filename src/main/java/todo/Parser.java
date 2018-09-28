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

    /**
     * Add a task, accepts input from user for a task like title, project, due date, status
     *
     * @throws ParseException
     */
    private void addTask() throws ParseException {
        System.out.println("Enter task title");
        String title = userInput();
        System.out.println("Enter project");
        String project = userInput();
        Date date = null;
        while (true) {
            try {
                System.out.println("Enter Due Date");
                String inputDate = userInput();
                date = new SimpleDateFormat("dd/MM/yyyy").parse(inputDate);
                break;
            } catch (java.text.ParseException e) {
                System.out.println("wrong format, please try again");
            }
        }
        System.out.println("Enter status: false for incomplete and true for complete");
        String inputStatus = userInput();
        boolean status = Boolean.valueOf(inputStatus);
        tasksManager.addNewTask(new Task(tasksManager.getNewTaskId(), title, project, date, status));
    }

    /**
     * Change the status of a task to false that is mark as done
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
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

    /**
     * Remove the task corresponding to the selected id
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
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


    public void updateTask() {
        displayAllTasks();
        System.out.println("Choose task(id) for update");
        String updateInput = userInput();
        int id = Integer.parseInt(updateInput);
        Task task = null;
        boolean idStatus = true;
        while (idStatus) {
            task = getTaskById(id);
            if (task == null) {
                System.out.println("This Id does not exist. Please select an existing task(id) for update");
                updateInput = userInput();
                id = Integer.parseInt(updateInput);
            } else {
                break;
            }
        }
        System.out.println("Which field you want to update. Select 1 for title, 2 for project, 3 for due date, 4 for status");
        String updatefieldInput = userInput();
        int updatefieldInt = Integer.parseInt(updatefieldInput);
        switch (updatefieldInt) {
            case 1:
                updateTitle(task);
                break;
            case 2:
                updateProject(task);
                break;
            case 3:
                updateDueDate(task);
                break;
            case 4:
                updateStatus(task);
                break;
        }
        System.out.println("Updated the list and here is the new updated list");
        displayAllTasks();
    }


    /**
     * Edit the task. This may involve updating, marking it as done or removing
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void editTask() throws IOException, ClassNotFoundException {
        System.out.println("Type 1 for update, 2 for mark as done and 3 for remove");
        String editInput = userInput();
        if (editInput.equals("1")) {
            updateTask();
        } else if (editInput.equals("2")) {
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

    private void displayAllTasks() {
        ArrayList<Task> tasks = tasksManager.getTasks();
        Iterator it = tasks.iterator();
        while (it.hasNext()) {
            Task task = (Task) it.next();
            tasksManager.printTask(task);
        }
    }


    private void updateTitle(Task task) {
        System.out.println("Enter the new value for title");
        String newtitle = userInput();
        task.setTitle(newtitle);
    }

    private void updateProject(Task task) {
        System.out.println("Enter the new value for project");
        String newProject = userInput();
        task.setProject(newProject);
    }

    private void updateDueDate(Task task) {
        System.out.println("Enter the new value for due date");
        Date newDate = null;
        boolean dateStatus = true;
        while (dateStatus) {
            try {
                String inputDate = userInput();
                newDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDate);
                dateStatus = false;
            } catch (java.text.ParseException e) {
                System.out.println("wrong format, please try again");
            }
        }
        task.setdueDate(newDate);
    }

    private void updateStatus(Task task) {
        System.out.println("Enter status: false for incomplete and true for complete");
        String inputStatus = userInput();
        boolean status = Boolean.valueOf(inputStatus);
        task.setStatus(status);
    }

    private Task getTaskById(int id) {
        ArrayList<Task> tasks = tasksManager.getTasks();
        Iterator<Task> it = tasks.iterator();
        Task task = null;
        while (it.hasNext()) {
            task = it.next();
            if (id == task.getId()) {
                return task;
            }
        }
        return null;
    }
}


