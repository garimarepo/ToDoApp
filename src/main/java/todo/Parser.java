package todo;

import java.util.Scanner;

public class Parser {
    private Scanner reader;
    private TaskManager showTasks;

    public Parser()
    {
        reader=new Scanner(System.in);
        showTasks=new TaskManager();
    }

    public void printWelcome() {
        System.out.println("Welcome to ToDoList. You have " + showTasks.toDoTasks() + "to do" + showTasks.finishedTasks() + "tasks are done");
        System.out.println("Pick an option: " +
                "(1) Show Task List (by date or project)" +
                "(2) Add New Task" +
                "(3) Edit Task (update, mark as done, remove)" +
                "(4) Save and Quit)");
    }
    public void startProcessing()
    {

        int chosenTask=reader.nextInt();

        if(chosenTask==1)
        {
            System.out.println("By date OR by project");

                String inputLine;   // will hold the full input line
                String word1 = null;
                String word2 = null;

                System.out.print("> ");     // print prompt
                reader.nextLine();
                inputLine=reader.nextLine();

                // Find up to two words on the line.
                Scanner tokenizer = new Scanner(inputLine);
                if(tokenizer.hasNext()) {
                    word1 = tokenizer.next();      // get first word
                    if(tokenizer.hasNext()) {
                        word2 = tokenizer.next();      // get second word
                        // note: we just ignore the rest of the input line.
                    }
                }
                if(word1.equals("date") || word2.equals("date"))
            {
                showTasks.tasksByDate();
            }
            else if(word1.equals("project") || word2.equals("project"))
            {
                showTasks.tasksByProject();
            }

        }
    }

}
