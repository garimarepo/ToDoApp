package todo;

import java.io.IOException;
import java.text.ParseException;

public class App {

    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
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
