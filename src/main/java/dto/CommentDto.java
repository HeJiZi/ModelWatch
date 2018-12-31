package dto;

import bean.Comment;
import bean.Reply;
import bean.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import util.DtoListUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CommentDto {

    private int comId;
    private String comContent;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp comCreateTime;
    private int comLikeNum;
    private int comDisLikeNum;
    private ComRepUserDto user;
    private List<ReplyDto> replys;

    public CommentDto(Comment comment) {
        this.comId = comment.getComId();
        this.comContent = comment.getComContent();
        this.comCreateTime = comment.getComCreateTime();
        this.comLikeNum = comment.getComLikeNum();
        this.comDisLikeNum = comment.getComDisLikeNum();
        this.user = new ComRepUserDto(comment.getUser());
        if (comment.getReplys().size() == 0) {
            this.replys = new ArrayList<ReplyDto>();
        } else {
            this.replys = DtoListUtil.transelateList(comment.getReplys(), ReplyDto.class);
        }
    }

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

    public Timestamp getComCreateTime() {
        return comCreateTime;
    }

    public void setComCreateTime(Timestamp comCreateTime) {
        this.comCreateTime = comCreateTime;
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

    public ComRepUserDto getUser() {
        return user;
    }

    public void setUser(ComRepUserDto user) {
        this.user = user;
    }

    public List<ReplyDto> getReplys() {
        return replys;
    }

    public void setReplys(List<ReplyDto> replys) {
        this.replys = replys;
    }


}
