//This class deals with storing and retrieving data from file.
package todo;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TaskStorage {
    TaskManager tasks;

    public void saveToFile(String destinationFile, ArrayList<Task> tasks) throws IOException {
        Path destination = Paths.get(destinationFile).toAbsolutePath();
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(destination.toString()));
        os.writeObject(tasks);
    }

    public ArrayList<Task> readFromFile(String sourceFile) throws IOException, ClassNotFoundException {
        File source = new File(sourceFile);

        System.out.println("a");
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(source));
            ArrayList<Task> t  = (ArrayList<Task>) is.readObject();
            is.close();
            return (t);
    }
}
