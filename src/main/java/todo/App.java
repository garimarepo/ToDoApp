package todo;

import java.io.IOException;
import java.text.ParseException;

public class App {

    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
        Parser p = new Parser();
        do {
            p.printWelcome();
            int userOption = p.getUserOption();
            if (userOption == 5) {
                System.out.println("Thanks for using the app. Bye");
                return;
            }
            p.startProcessing(userOption);
        } while (true);
    }

}
