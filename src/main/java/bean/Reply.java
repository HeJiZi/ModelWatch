package bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.List;

public class Reply {

    private int repId;
    private String repContent;
    private int repLikeNum;
    private int repDisLikeNum;
    private int repType;
    private int repComId;
    private String repUsername;
    private int repRId;
    private int repUId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp repCreateTime;
    private User user;
    private int repState;

    public String getRepUsername() {
        return repUsername;
    }

    public void setRepUsername(String repUsername) {
        this.repUsername = repUsername;
    }

    public int getRepRId() {
        return repRId;
    }

    public void setRepRId(int repRId) {
        this.repRId = repRId;
    }

    public int getRepState() {
        return repState;
    }

    public void setRepState(int repState) {
        this.repState = repState;
    }

    public int getRepId() {
        return repId;
    }

    public void setRepId(int repId) {
        this.repId = repId;
    }

    public String getRepContent() {
        return repContent;
    }

    public void setRepContent(String repContent) {
        this.repContent = repContent;
    }

    public int getRepLikeNum() {
        return repLikeNum;
    }

    public void setRepLikeNum(int repLikeNum) {
        this.repLikeNum = repLikeNum;
    }

    public int getRepDisLikeNum() {
        return repDisLikeNum;
    }

    public void setRepDisLikeNum(int repDisLikeNum) {
        this.repDisLikeNum = repDisLikeNum;
    }

    public Timestamp getRepCreateTime() {
        return repCreateTime;
    }

    public void setRepCreateTime(Timestamp repCreateTime) {
        this.repCreateTime = repCreateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRepUId() {
        return repUId;
    }

    public void setRepUId(int repUId) {
        this.repUId = repUId;
    }

    public int getRepType() {
        return repType;
    }

    public void setRepType(int repType) {
        this.repType = repType;
    }

    public int getRepComId() {
        return repComId;
    }

    public void setRepComId(int repComId) {
        this.repComId = repComId;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "repId=" + repId +
                ", repContent='" + repContent + '\'' +
                ", repLikeNum=" + repLikeNum +
                ", repDisLikeNum=" + repDisLikeNum +
                ", repType=" + repType +
                ", repComId=" + repComId +
                ", repUsername='" + repUsername + '\'' +
                ", repRId=" + repRId +
                ", repUId=" + repUId +
                ", repCreateTime=" + repCreateTime +
                ", user=" + user +
                ", repState=" + repState +
                '}' + "\n";
    }

}
