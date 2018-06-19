package bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class Project {
    private Long pId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp pCreateTime;
    private String pName;
    private String pContext;
    private String pPreview;

    private User user;

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

    @Override
    public String toString() {
        return "Project{" +
                "pId=" + pId +
                ", pCreateTime=" + pCreateTime +
                ", pName='" + pName + '\'' +
                ", pContext='" + pContext + '\'' +
                ", pPreview='" + pPreview + '\'' +
                ", user=" + user +
                '}';
    }
}
