package bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.List;

public class Project {
    private Long pId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp pCreateTime;
    private String pName;
    private String pContext;
    private String pPreview;
    private int pSubsNum;
    private boolean pSubed;

    private User user;

    private List<Model> models;
    private List<User> collaborators;
    private List<PjHeatStatic> pjHeatStatic;
    private List<LogStatic> logStatic;

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public Timestamp getpCreateTime() {
        return pCreateTime;
    }

    public void setpCreateTime(Timestamp pCreateTime) {
        this.pCreateTime = pCreateTime;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpContext() {
        return pContext;
    }

    public void setpContext(String pContext) {
        this.pContext = pContext;
    }

    public String getpPreview() {
        return pPreview;
    }

    public void setpPreview(String pPreview) {
        this.pPreview = pPreview;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public int getpSubsNum() {
        return pSubsNum;
    }

    public void setpSubsNum(int pSubsNum) {
        this.pSubsNum = pSubsNum;
    }

    public boolean ispSubed() {
        return pSubed;
    }

    public void setpSubed(boolean pSubed) {
        this.pSubed = pSubed;
    }

    public List<User> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<User> collaborators) {
        this.collaborators = collaborators;
    }

    public List<PjHeatStatic> getPjHeatStatic() {
        return pjHeatStatic;
    }

    public void setPjHeatStatic(List<PjHeatStatic> pjHeatStatic) {
        this.pjHeatStatic = pjHeatStatic;
    }

    public List<LogStatic> getLogStatic() {
        return logStatic;
    }

    public void setLogStatic(List<LogStatic> logStatic) {
        this.logStatic = logStatic;
    }

    @Override
    public String toString() {
        return "Project{" +
                "pId=" + pId +
                ", pCreateTime=" + pCreateTime +
                ", pName='" + pName + '\'' +
                ", pContext='" + pContext + '\'' +
                ", pPreview='" + pPreview + '\'' +
                ", pSubsNum=" + pSubsNum +
                ", pIsSubed=" + pSubed +"\n"+
                ", user=" + user +
                ", models=" + models +
                '}'+"\n";
    }
}
