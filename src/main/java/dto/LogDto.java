package dto;

import bean.Log;
import dto.model.TinyModelDto;
import dto.project.TinyProjectDto;

import java.sql.Timestamp;

public class LogDto {
    private  Long lId;
    private String lContext;
    private Timestamp lTime;

    private TinyUserDto user;
    private TinyProjectDto project;
    private TinyModelDto model;
    public LogDto(Log log){
        this.lId = log.getlId();
        this.lContext = log.getlContext();
        this.lTime = log.getlTime();
        this.user = new TinyUserDto(log.getUser());
        this.project = new TinyProjectDto(log.getProject());
        this.model = new TinyModelDto(log.getModel());
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

    public TinyProjectDto getProject() {
        return project;
    }

    public void setProject(TinyProjectDto project) {
        this.project = project;
    }

    public TinyUserDto getUser() {
        return user;
    }

    public void setUser(TinyUserDto user) {
        this.user = user;
    }

    public TinyModelDto getModel() {
        return model;
    }

    public void setModel(TinyModelDto model) {
        this.model = model;
    }
}
