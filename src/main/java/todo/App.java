package todo;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Parser p = new Parser();
        int userOption;
        do {
            p.printWelcome();
            System.out.println();
            System.out.print("Enter choice : ");
            userOption = p.getUserOption();
            p.startProcessing(userOption);
        } while (userOption != 4);
    }
}
