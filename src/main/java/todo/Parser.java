package todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

//import java.util.Scanner;

public class Parser {
    //private Scanner reader;
    BufferedReader input;
    TaskStorage taskStorage;
    private TaskManager tasksManager;

    public Parser() {
        //reader = new Scanner(System.in);
        input = new BufferedReader(new InputStreamReader(System.in));
        tasksManager = new TaskManager();
        taskStorage = new TaskStorage();
    }

    public void printWelcome() {
        {
            System.out.println("Welcome to ToDoList.\nYou have " + tasksManager.toDoTasks() + "  to do tasks and " +
                    tasksManager.finishedTasks() + " tasks are done");
            System.out.println("Pick an option: \n" +
                    "(1) Show Task List (by date or project)\n" +
                    "(2) Add New Task\n" +
                    "(3) Edit Task (update, mark as done, remove)\n" +
                    "(4) Save and Quit)\n");
        }
    }

    public int getUserOption() {
        return Integer.parseInt(acceptStringInput());
    }

    public void startProcessing(int chosenTask) throws IOException, ClassNotFoundException {

        switch (chosenTask) {
            case 1: {
                System.out.println("By date OR by project");
                String input = acceptStringInput();


                if (input.equals("date")) {
                    tasksManager.tasksByDate();
                } else if (input.equals("project")) {
                    System.out.println("Enter project name");
                    String inputProject = acceptStringInput();
                    tasksManager.tasksByProject(inputProject);
                    //tasks.showtasks();
                }
                break;
            }

            case 2: {
                System.out.println("Enter task title");

                String title = acceptStringInput();
                System.out.println("Enter project");
                String project = acceptStringInput();
                System.out.println("Enter Due Date");
                String inputDate = acceptStringInput();
                Date date = null;
                
                try {
                    date = new SimpleDateFormat("dd/MM/yyyy").parse(inputDate);
                } catch (java.text.ParseException e) {
                    System.out.println("wrong format");
                    return;
                }
                System.out.println("Enter status: false for incomplete and true for complete");
                String inputStatus = acceptStringInput();
                boolean status = Boolean.valueOf(inputStatus);
                Task newTask = new Task(title, project, date, status);
                tasksManager.addNewTask(newTask);
                break;
            }
            case 3: {
                System.out.println("update,mark as done,remove");
                String editInput=acceptStringInput();
                break;
            }
            case 4: {
                taskStorage.saveToFile("Storage.txt", tasksManager.getTasks());
                break;
            }
        }
    }

    public String acceptStringInput() {
        String inputLine;   // will hold the full input line
        
        System.out.print("> ");     // print prompt
        inputLine = null;
        try {
            inputLine = input.readLine();
        } catch (Exception e) {
            System.out.println(e);
        }
        return inputLine;
    }
}

