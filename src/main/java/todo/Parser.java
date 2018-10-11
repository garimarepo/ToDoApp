/**
 * Accept & parse input from the user.
 */

package todo;

import todo.tasks.Task;
import todo.tasks.TaskManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Parser {
    private static final String WELCOME_MSG = "ToDoList: Choose an option";
    public TaskManager tasksManager;
    private BufferedReader input;

    public Parser() throws IOException, ClassNotFoundException {
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
            System.out.println("(4) Save and Quit");
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
                System.out.println("Your task has saved and thanks for using the app. Bye");
                return;
            }
        }
    }

    public int getUserOption() {
        int userOption = 4;
        try {
            userOption = Integer.parseInt(userInput());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return userOption;
    }

    private void showTask() throws IOException, ClassNotFoundException {
        System.out.println("Type date(for sorted task list by date) and type project(for filtered by the project name)");
        String input = userInput();
        if (input.equals("date")) {
            System.out.println("The task list sorted by date is as follows:");
            printTaskList(tasksManager.tasksByDate());
        } else if (input.equals("project")) {
            System.out.println("Enter project name");
            String inputProject = userInput();
            List<Task> tasks = tasksManager.tasksByProject(inputProject);
            System.out.println("The task list filtered by the project name is as follows:");
            printTaskList(tasks);
        } else {
            System.out.println("Type either date OR project for show task list");
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
        Date date;
        while (true) {
            try {
                System.out.println("Enter Due Date dd/mm/yyyy");
                String inputDate = userInput();
                date = new SimpleDateFormat("dd/MM/yyyy").parse(inputDate);
                break;
            } catch (java.text.ParseException e) {
                System.out.println("wrong format, please try again");
            }
        }
        boolean status = verifyStatus();
        tasksManager.addNewTask(title, project, date, status);
    }

    /**
     * Change the status of a task to true that is mark as done
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void changeStatus(int id) throws IOException, ClassNotFoundException {
        tasksManager.changeStatus(id);
        System.out.println("Your task is now mark as done that is changed the status from false to true " +
                " and here is the new list");
        printTaskList(tasksManager.tasksByDate());
    }

    /**
     * Remove the task corresponding to the selected id
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void removeTask(int id) throws IOException, ClassNotFoundException {
        tasksManager.removeTask(id);
        System.out.println("Your task is removed. Now the new list is:");
        printTaskList(tasksManager.tasksByDate());
    }

    /**
     * Update the task's field corresponding to the id
     *
     * @param id the id of task to be updated
     */

    public void updateTask(int id) {
        boolean status = false;
        boolean option = true;
        while (option) {
            System.out.println("Which field you want to update. Select 1 for title, 2 for project, 3 for due date, 4 for status");
            int updatefieldInt = getUserOption();
            switch (updatefieldInt) {
                case 0:
                    printWelcome();
                    return;
                case 1:
                    System.out.println("Enter the new value for title");
                    String newtitle = userInput();
                    status = tasksManager.updateTaskTitle(id, newtitle);
                    break;
                case 2:
                    System.out.println("Enter the new value for project");
                    String newProject = userInput();
                    status = tasksManager.updateTaskProject(id, newProject);
                    break;
                case 3:
                    System.out.println("Enter the new value for due date (dd/MM/yyyy)");
                    Date newDate = null;
                    boolean dateStatus = true;
                    while (dateStatus) {
                        try {
                            String inputDate = userInput();
                            newDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDate);
                            dateStatus = false;
                        } catch (java.text.ParseException e) {
                            System.out.println("Wrong format, should be (dd/MM/yyyy), please try again");
                        }
                    }
                    status = tasksManager.updateTaskDueDate(id, newDate);
                    break;
                case 4:
                    boolean projectStatus = verifyStatus();
                    status = tasksManager.updateTaskStatus(id, projectStatus);
                    break;
            }
            if (status) {
                System.out.println("Updated the list and here is the new updated list");
                displayAllTasks();
                option = false;
            } else {
                System.out.println("If you want to see the options again press 0 otherwise");
                option = true;
            }
        }
    }

    /**
     * Edit the task. This may involve updating, marking it as done or removing
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void editTask() throws IOException, ClassNotFoundException {
        int id = verifyId();
        if (id != -1) {
            System.out.println("Type 1 for update, 2 for mark as done and 3 for remove");
            String editInput = userInput();
            if (editInput.equals("1")) {
                updateTask(id);
            } else if (editInput.equals("2")) {
                changeStatus(id);
            } else if (editInput.equals("3")) {
                removeTask(id);
            } else {
                System.out.println("Please Type 1 for update, 2 for mark as done and 3 for remove in Edit Task option");
            }
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

    private void displayAllTasks() {
        ArrayList<Task> tasks = tasksManager.getTasks();
        for (Task task : tasks) {
            printTask(task);
        }
    }

    /**
     * print details of task object.
     *
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

    public void printTaskList(List<Task> tasks) {
        for (Task task : tasks) {
            printTask(task);
        }
    }

    /**
     * Verify the id, that is, the user should select an existing id
     *
     * @return an existing id, or -1 if not found the id.
     */
    public int verifyId() {
        boolean found = false;
        int id, count = 0;
        final int BEYOND_MAX_ATTEMPT = 4;
        final int MAX_ATTEMPT = 3;
        do {
            displayAllTasks();
            System.out.println("Choose task(id) for update");
            String updateInput = userInput();
            id = Integer.parseInt(updateInput);
            for (Task task : tasksManager.getTasks()) {
                if (id == task.getId()) {
                    found = true;
                    count = BEYOND_MAX_ATTEMPT;
                    break;
                }
            }
            if (found == false) {
                System.out.println("Please select an existing id");
                count++;
            }
        } while (count < MAX_ATTEMPT);
        if (count == MAX_ATTEMPT) {
            return -1;
        } else {
            return id;
        }
    }

    /**
     * Verifying the status value i.e. it should be either true or false.
     * If not,then interrupts user.
     *
     * @return
     */

    public boolean verifyStatus() {
        boolean wrongValue = true;
        while (wrongValue) {
            System.out.println("Enter status: false for incomplete and true for complete");
            String inputStatus = userInput();
            if (inputStatus.equals("true") || inputStatus.equals("false")) {
                boolean projectStatus = Boolean.valueOf(inputStatus);
                return projectStatus;
            } else {
                System.out.println("Please specify either true or false");
            }
        }
        return true;
    }
}



