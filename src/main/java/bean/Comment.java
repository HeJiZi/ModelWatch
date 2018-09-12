package bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class Comment {
    private int comId;
    private String comContent;
    private long comMId;
    private long comUId;
    private int comState;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp comCreateTime;

    User user;

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    public String getComContent() {
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent;
    }

    public long getComMId() {
        return comMId;
    }

    public void setComMId(long comMId) {
        this.comMId = comMId;
    }

    public long getComUId() {
        return comUId;
    }

    public void setComUId(long comUId) {
        this.comUId = comUId;
    }

    public int getComState() {
        return comState;
    }

    public void setComState(int comState) {
        this.comState = comState;
    }

    public Timestamp getComCreateTime() {
        return comCreateTime;
    }

    public void setComCreateTime(Timestamp comCreateTime) {
        this.comCreateTime = comCreateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
