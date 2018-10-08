/**
 * Task persistence class.
 */

package todo.storage;

import todo.tasks.TaskStore;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        os.close();
    }

    /**
     * Retrieve data from file
     *
     * @return the list of file
     */
    public TaskStore readFromFile() throws IOException, ClassNotFoundException {
        File source = new File(STORAGE_LOCATION);
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(source))) {
            return (TaskStore) is.readObject();
        } catch (FileNotFoundException e) {
            return new TaskStore();
        }
    }
}
