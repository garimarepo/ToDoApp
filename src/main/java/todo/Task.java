// This class represents a single task in ToDoApp


package todo;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable, Comparable {


    private int id;
    private String title;
    private String project;
    private Date dueDate;
    private boolean status;
    private static final long serialVersionUID = 6529685098267757690L;

    public Task(int id, String title, String project, Date dueDate, boolean status) {
        this.id=id;
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

    public void setdueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getdueDate() {
        return dueDate;
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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object o) {
        Task tasks = (Task) o;
        if (this.dueDate.after(tasks.dueDate)) {
            return -1;
        } else if (this.dueDate.before(tasks.dueDate)) {
            return 1;
        } else {
            return 0;
        }
    }
}
