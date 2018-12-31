package dao;

import bean.Comment;
import bean.User;
import entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentsDao {
    int AddComment(Comment comment);

    int DeleteComment(int comId);

    int UpdateCommentState(Comment comment);

    List<Comment> SelectComments(int state);

    List<Comment> SelectCommentsByMIdPage(@Param("mId") int mId, @Param("page")Page page);

    int UpdateCommentPositiveNum(@Param("comId") int comId);

    int UpdateCommentObjectiveNum(@Param("comId") int comId);

}
