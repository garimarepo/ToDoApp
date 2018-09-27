/**
 * Task persistence class.
 */

package todo;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TaskStorage {
    private final static String  STORAGE_LOCATION= "/Users/tmp-sda-1181/IdeaProjects/ToDoApp/Storage.txt";

    /**
     * Save task list to the file
     * @param tasks the list of tasks
     * @throws IOException
     */

    public void saveToFile(ArrayList<Task> tasks) throws IOException {
        Path destination = Paths.get(STORAGE_LOCATION).toAbsolutePath();
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(destination.toString()));
        os.writeObject(tasks);
    }

    /**
     * Retrieve data from file
     * @return the list of file
     */

    public ArrayList<Task> readFromFile() {
        File source = new File(STORAGE_LOCATION);
        System.out.println("a");
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(source));
            ArrayList<Task> t = (ArrayList<Task>) is.readObject();
            is.close();
            return (t);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ArrayList<>();
    }
}
