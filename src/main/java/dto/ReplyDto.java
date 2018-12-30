package dto;

import bean.Reply;
import bean.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class ReplyDto {

    private int repId;
    private String repContent;
    private int repLikeNum;
    private int repDisLikeNum;
    private int repRId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp repCreateTime;
    private ComRepUserDto user;

    public ReplyDto(Reply reply) {
        this.repId = reply.getRepId();
        this.repContent = reply.getRepContent();
        this.repLikeNum = reply.getRepLikeNum();
        this.repDisLikeNum = reply.getRepDisLikeNum();
        this.repRId = reply.getRepRId();
        this.repCreateTime = reply.getRepCreateTime();
        this.user = new ComRepUserDto(reply.getUser());
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

    public int getRepRId() {
        return repRId;
    }

    public void setRepRId(int repRId) {
        this.repRId = repRId;
    }

    public Timestamp getRepCreateTime() {
        return repCreateTime;
    }

    public void setRepCreateTime(Timestamp repCreateTime) {
        this.repCreateTime = repCreateTime;
    }


    public ComRepUserDto getUser() {
        return user;
    }

    public void setUser(ComRepUserDto user) {
        this.user = user;
    }
}
