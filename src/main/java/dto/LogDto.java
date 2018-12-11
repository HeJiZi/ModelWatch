package dto;

import bean.Log;
import bean.Model;
import bean.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LogDto {
    private  Long lId;
    private String lContext;
    private Timestamp lTime;

    private UserDto user;
    private ProjectDto project;
    private ModelDto model;
    public LogDto(Log log){
        this.lId = log.getlId();
        this.lContext = log.getlContext();
        this.lTime = log.getlTime();
        this.user = new UserDto(log.getUser());
        this.project = new ProjectDto(log.getProject());
        this.model = new ModelDto(log.getModel());
    }

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

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public ModelDto getModel() {
        return model;
    }

    public void setModel(ModelDto model) {
        this.model = model;
    }
}
