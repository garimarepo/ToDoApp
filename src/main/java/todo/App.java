package todo;


import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Parser p = new Parser();
        do {
            p.printWelcome();
            int userOption = p.getUserOption();
            if (userOption == 5) {
                return;
            }

            p.startProcessing(userOption);
        } while (true);


    }
}
