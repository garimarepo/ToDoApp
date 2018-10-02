/**
 * Task persistence class.
 */

package todo;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class StorageHelper {
    private final static String STORAGE_LOCATION = "Storage.txt";

    /**
     * Save task list to the file
     *
     * @param taskStore the list of tasks
     * @throws IOException
     */
    public void saveToFile(TaskStore taskStore) throws IOException {
        Path destination = Paths.get(STORAGE_LOCATION).toAbsolutePath();
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(destination.toString()));
        os.writeObject(taskStore);
    }

    /**
     * Retrieve data from file
     *
     * @return the list of file
     */
    public TaskStore readFromFile() {
        File source = new File(STORAGE_LOCATION);
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(source));
            TaskStore t = (TaskStore) is.readObject();
            is.close();
            return (t);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new TaskStore();
    }
}
