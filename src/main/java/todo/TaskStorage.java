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

    public ArrayList<Task> readFromFile(String sourceFile)
            throws IOException, ClassNotFoundException {
        // Make sure the file can be found.
        // URL resource = getClass().getResource(sourceFile);

        File source = new File(sourceFile);
        ObjectInputStream is = new ObjectInputStream(
                new FileInputStream(source));

        ArrayList<Task> t = (ArrayList<Task>) is.readObject();
        is.close();
        return (t);

    }
}
