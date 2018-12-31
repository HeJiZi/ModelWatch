package bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.List;

public class Comment {
    private int comId;
    private String comContent;
    private int comLikeNum;
    private int comDisLikeNum;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp comCreateTime;
    private User user;
    private List<Reply> replys;

    private long comMId;
    private long comUId;
    private int comState;

//    public Comment(int comId, String comContent, int comLikeNum, int comDisLikeNum,
//                   Timestamp comCreateTime, User user, List<Reply> replys) {
//        this.comId = comId;
//        this.comContent = comContent;
//        this.comLikeNum = comLikeNum;
//        this.comDisLikeNum = comDisLikeNum;
//        this.comCreateTime = comCreateTime;
//        this.user = user;
//        this.replys = replys;
//    }

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

    public int getComLikeNum() {
        return comLikeNum;
    }

    public void setComLikeNum(int comLikeNum) {
        this.comLikeNum = comLikeNum;
    }

    public int getComDisLikeNum() {
        return comDisLikeNum;
    }

    public void setComDisLikeNum(int comDisLikeNum) {
        this.comDisLikeNum = comDisLikeNum;
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

    public List<Reply> getReplys() {
        return replys;
    }

    public void setReplys(List<Reply> replys) {
        this.replys = replys;
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

    @Override
    public String toString() {
        return "Comment{" +
                "comId=" + comId +
                ", comContent='" + comContent + '\'' +
                ", comLikeNum=" + comLikeNum +
                ", comDisLikeNum=" + comDisLikeNum +
                ", comCreateTime=" + comCreateTime +
                ", user=" + user +
                ", replys=" + replys +
                ", comMId=" + comMId +
                ", comUId=" + comUId +
                ", comState=" + comState +
                '}'+'\n';
    }
}
