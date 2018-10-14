/**
 * This class represents a single task in ToDoApp.
 */

package todo.tasks;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable, Comparable<Task> {

    private static final long serialVersionUID = 6529685098267757690L;
    private int id;
    private String title;
    private String project;
    private Date dueDate;
    private boolean status;

    public Task(int id, String title, String project, Date dueDate, boolean status) {
        this.id = id;
        this.title = title;
        this.project = project;
        this.dueDate = dueDate;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    /**
     * Compare tasks according to the due dates.
     *
     * @param task Task object
     * @return an integer value for comparison
     */
    @Override
    public int compareTo(Task task) {
        if (this.dueDate.after(task.dueDate)) {
            return 1;
        } else if (this.dueDate.before(task.dueDate)) {
            return -1;
        } else {
            return 0;
        }
    }
}
