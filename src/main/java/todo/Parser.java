/**
 * Accept & parse input from the user.
 */

package todo;

import todo.tasks.Task;
import todo.tasks.TaskManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Parser {
    public TaskManager tasksManager;
    private BufferedReader input;

    public Parser() throws IOException, ClassNotFoundException {
        input = new BufferedReader(new InputStreamReader(System.in));
        tasksManager = new TaskManager();
    }

    public void printWelcome() {
        {
            System.out.println();
            System.out.println("*****************************************************");
            System.out.println();
            System.out.println("--------------------Tasks Summary--------------------");
            System.out.println("Todo Tasks : " + tasksManager.countToDoTasks());
            System.out.println("Completed Tasks : " + tasksManager.countFinishedTasks());
            System.out.println("-----------------------------------------------------");
            System.out.println();
            System.out.println("Menu");
            System.out.println("(1) Show Task List (by date or project)");
            System.out.println("(2) Add New Task");
            System.out.println("(3) Edit Task (update, mark as done, remove)");
            System.out.println("(4) Save and Quit");
        }
    }

    /**
     * According to the user's chosenTask input, the function manipulates the corresponding functionality
     *
     * @param userOption, the number entered by the user for a particular functionality
     * @throws IOException
     */
    public void startProcessing(int userOption) throws IOException {
        switch (userOption) {
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

    private void showTask() {
        System.out.println();
        System.out.println("Choose show option:");
        System.out.println("1) Task by date");
        System.out.println("2) Task filter by project");
        System.out.print("Enter choice : ");
        int input = getUserOption();
        if (input == 1) {
            System.out.println();
            System.out.println("Task sorted by due date:");
            printTaskList(tasksManager.tasksByDate());
        } else if (input == 2) {
            System.out.println("Enter project name");
            String inputProject = userInput();
            List<Task> tasks = tasksManager.tasksByProject(inputProject);
            System.out.println("Tasks filtered by project:");
            printTaskList(tasks);
        } else {
            System.out.println("Invalid input. Please choose option again.");
        }
    }

    /**
     * Add a task, accepts input from user for a task like title, project, due date, status
     */
    private void addTask() {
        System.out.println();
        System.out.print("Enter task title : ");
        String title = userInput();
        System.out.print("Enter project : ");
        String project = userInput();
        Date date = verifyDueDateFormat();
        Date newDate;
        if (date != null) {
            newDate = date;
        } else {
            return;
        }
        int inputStatus = verifyStatus();
        if (inputStatus != -1) {
            boolean projectStatus;
            if (inputStatus == 1) {
                projectStatus = true;
            } else {
                projectStatus = false;
            }
            tasksManager.addNewTask(title, project, newDate, projectStatus);
        }
    }

    /**
     * Change the status of a task to true that is mark as done
     */
    private void changeStatus(int id) {
        tasksManager.changeStatus(id);
        System.out.println("Your task is now mark as done that is changed the status from false to true " +
                " and here is the new list");
        printTaskList(tasksManager.tasksByDate());
    }

    /**
     * Remove the task corresponding to the selected id
     */
    private void removeTask(int id) {
        tasksManager.removeTask(id);
        System.out.println("Task removed!!!");
        System.out.println("Task List:");
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
            System.out.println("Field to update.");
            System.out.println("1) Title");
            System.out.println("2) Project");
            System.out.println("3) Due date");
            System.out.println("4) Status");
            int updatefieldInt = getUserOption();
            switch (updatefieldInt) {
                case 0:
                    return;
                case 1:
                    System.out.println("Enter the new value for title");
                    String newTitle = userInput();
                    status = tasksManager.updateTaskTitle(id, newTitle);
                    break;
                case 2:
                    System.out.println("Enter the new value for project");
                    String newProject = userInput();
                    status = tasksManager.updateTaskProject(id, newProject);
                    break;
                case 3:
                    Date newDate = verifyDueDateFormat();
                    if (newDate != null) {
                        status = tasksManager.updateTaskDueDate(id, newDate);
                    }
                    break;
                case 4:
                    int inputStatus = verifyStatus();
                    if (inputStatus != -1) {
                        boolean projectStatus;
                        if (inputStatus == 1) {
                            projectStatus = true;
                        } else {
                            projectStatus = false;
                        }
                        status = tasksManager.updateTaskStatus(id, projectStatus);
                    }
                    break;
            }
            if (status) {
                System.out.println("Updated the list and here is the new updated list");
                displayAllTasks();
                option = false;
            } else {
                System.out.println("OR If you want to see the options again press 0 otherwise continue");
                option = true;
            }
        }
    }

    /**
     * Edit the task. This may involve updating, marking it as done or removing
     */
    private void editTask() {
        int id = verifyId();
        if (id != -1) {
            System.out.println("Choose option:");
            System.out.println("1) Update");
            System.out.println("2) Mark as done");
            System.out.println("3) Remove");
            String editInput = userInput();
            if (editInput.equals("1")) {
                updateTask(id);
            } else if (editInput.equals("2")) {
                changeStatus(id);
            } else if (editInput.equals("3")) {
                removeTask(id);
            } else {
                System.out.println("Invalid input. Please try again.");
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
        System.out.println();
        System.out.println("----------Task List Begin-----------");
        for (Task task : tasks) {
            printTask(task);
        }
        System.out.println("----------Task List End-------------");
        System.out.println();
    }

    /**
     * Verify the id, that is, the user should select an existing id
     * Gives user a maximum of two attempts if he gives wrong input.
     *
     * @return an existing id, or -1 if not found the id.
     */
    public int verifyId() {
        boolean found = false;
        int count = 1;
        int id;
        final int MAX_ATTEMPT = 2;
        while (count <= MAX_ATTEMPT) {
            displayAllTasks();
            System.out.println("Enter Task(id) for update: ");
            String updateInput = userInput();
            id = Integer.parseInt(updateInput);
            for (Task task : tasksManager.getTasks()) {
                if (id == task.getId()) {
                    return id;
                }
            }
            if (found == false) {
                System.out.println("Invalid Task Id.");
                count++;
            }
        }
        return -1;
    }

    /**
     * Verifying the status value i.e. it should be either true or false.
     * Gives user a maximum of two attempts if he enters wrong.
     *
     * @return 1 for true, 0 for false and -1 for invalid value.
     */

    public int verifyStatus() {
        int count = 1;
        final int MAX_ATTEMPT = 2;
        while (count <= MAX_ATTEMPT) {
            System.out.print("Enter status (false/true) : ");
            String inputStatus = userInput();
            if (inputStatus.equals("true")) {
                return 1;
            } else if (inputStatus.equals("false")) {
                return 0;
            } else {
                System.out.println("Please specify either true or false");
                count++;
            }
        }
        return -1;

    }

    /**
     * Verifies the date format
     * Gives user a maximum of two attempts if he enters wrong input
     *
     * @return date in correct format otherwise null value
     */
    public Date verifyDueDateFormat() {
        System.out.println("Enter the new value for due date (dd/MM/yyyy)");
        Date newDate = null;
        int count = 1;
        final int MAX_ATTEMPT = 2;
        while (count <= MAX_ATTEMPT) {
            try {
                String inputDate = userInput();
                newDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDate);
                break;
            } catch (java.text.ParseException e) {
                System.out.println("Wrong format, should be (dd/MM/yyyy), please try again");
                count++;
            }
        }
        if (count <= MAX_ATTEMPT) {
            return newDate;
        }
        return null;
    }
}



