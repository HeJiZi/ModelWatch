package bean;

import java.security.Timestamp;
import java.util.Date;

public class Invitation {

    private Timestamp invTime;
    private int invState;
    private Project project;
    private User manager;
    private User collaborator;

    public Timestamp getInvTime() {
        return invTime;
    }

    public void setInvTime(Timestamp invTime) {
        this.invTime = invTime;
    }

    public int getInvState() {
        return invState;
    }

    public void setInvState(int invState) {
        this.invState = invState;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public User getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(User collaborator) {
        this.collaborator = collaborator;
    }

}
