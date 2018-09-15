package todo;

import java.util.Date;

public class Task {
    private String title;
    private String project;
    private Date dueDate;
    private boolean status;


    public Task(String title, String project, Date dueDate, boolean status)

    {
        this.title=title;
        this.project=project;
        this.dueDate=dueDate;
        this.status=status;

    }

    public void setTitle(String title)
    {
        this.title=title;
    }
    public String getTitle()
    {
        return title;
    }
    public void setProject(String project)
    {
        this.project=project;
    }
    public String getProject()
    {
        return project;
    }
    public void setdueDate(Date dueDate)
    {
        this.dueDate=dueDate;
    }
    public Date getdueDate()
    {
        return dueDate;
    }
    public void setStatus(boolean status)
    {
        this.status=status;
    }
    public boolean getStatus()
    {
        return status;
    }
}
