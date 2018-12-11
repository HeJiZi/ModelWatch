package bean;

import java.sql.Timestamp;
import java.util.List;

public class Log {
    private Long lId;
    private String lContext;
    private Timestamp lTime;

    private User user;
    private Project project;
    private Model model;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
