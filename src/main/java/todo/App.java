package todo;

import java.io.IOException;
import java.text.ParseException;

public class App {

    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
        Parser p = new Parser();
        do {
            p.printWelcome();
            int userOption = p.getUserOption();
            if(userOption == 4)
                break;
            p.startProcessing(userOption);
        } while (true);
    }
}
