package bean;

import java.sql.Timestamp;

public class Log {
    private  Long lId;
    private String lContext;
    private Timestamp lTime;

    private Project project;

    public Long getlId() {
        return lId;
    }

    public void setlId(Long lId) {
        this.lId = lId;
    }

    public String getlContext() {
        return lContext;
    }

    public void setlContext(String lContext) {
        this.lContext = lContext;
    }

    public Timestamp getlTime() {
        return lTime;
    }

    public void setlTime(Timestamp lTime) {
        this.lTime = lTime;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
