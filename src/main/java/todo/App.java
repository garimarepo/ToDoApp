package todo;

import java.io.IOException;
import java.text.ParseException;

public class App {

    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
        Parser p = new Parser();
        p.printWelcome();
        int userOption = p.getUserOption();
        p.startProcessing(userOption);
    }

}
