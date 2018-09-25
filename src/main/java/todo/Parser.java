/**
 * Accept & parse input from the user.
 */

package todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class Parser {
    private static final String WELCOME_MSG = "Welcome to ToDoList.";
    private BufferedReader input;
    private TaskStorage taskStorage;
    private TaskManager tasksManager;

    public Parser() {
        input = new BufferedReader(new InputStreamReader(System.in));
        tasksManager = new TaskManager();
        taskStorage = new TaskStorage();
    }

    public void printWelcome() {
        {
            System.out.println(WELCOME_MSG);
            System.out.println("You have " + tasksManager.toDoTasks() + " to do tasks and " +
                    tasksManager.finishedTasks() + " tasks are done");
            System.out.println("Pick an option");
            System.out.println("(1) Show Task List (by date or project)");
            System.out.println("(2) Add New Task");
            System.out.println("(3) Edit Task (update, mark as done, remove)");
            System.out.println("(4) Save and Quit");
        }
    }

    public int getUserOption() {
        return Integer.parseInt(userInput());
    }

    public void startProcessing(int chosenTask) throws IOException, ClassNotFoundException {

        switch (chosenTask) {
            case 1: {
                System.out.println("By date OR by project");
                String input = userInput();


                if (input.equals("date")) {
                    tasksManager.tasksByDate();
                } else if (input.equals("project")) {
                    System.out.println("Enter project name");
                    String inputProject = userInput();
                    tasksManager.tasksByProject(inputProject);

                }
                break;
            }

            case 2: {
                System.out.println("Enter task title");

                String title = userInput();
//                System.out.println("Enter project");
//                String project = userInput();
//                System.out.println("Enter Due Date");
//                String inputDate = userInput();
//                Date date = null;
//
//                try {
//                    date = new SimpleDateFormat("dd/MM/yyyy").parse(inputDate);
//                } catch (java.text.ParseException e) {
//                    System.out.println("wrong format");
//                    return;
//                }
//                System.out.println("Enter status: false for incomplete and true for complete");
//                String inputStatus = userInput();
//                boolean status = Boolean.valueOf(inputStatus);
//                Task newTask = new Task(title, project, date, status);
                tasksManager.addNewTask(new Task(title,"a", new Date(), true));
                break;
            }
            case 3: {
                System.out.println("Type 1 for update, 2 for mark as done and 3 for remove");
                unfinishedTasks();
                String editInput = userInput();
                if(editInput.equals("2"))
                {

                }
                break;
            }
            case 4: {
                taskStorage.saveToFile("Storage.txt", tasksManager.getTasks());
                break;
            }
        }
    }

    public String userInput() {
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

    public void unfinishedTasks() throws IOException, ClassNotFoundException {
        ArrayList<Task> tasks=taskStorage.readFromFile("Storage.txt");
        Iterator it=tasks.iterator();
        while(it.hasNext())
        {
            Task task=(Task)it.next();
            if(task.getStatus()==false)
            {
                System.out.println(task.getProject() + " " + task.getTitle() + " " + task.getdueDate()
                        + " " + task.getStatus());
            }
        }
    }
}

