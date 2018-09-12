package dao;

import bean.Comment;

import java.util.List;

public interface CommentsDao {
    int AddComment(Comment comment);

    int DeleteComment(int comId);

    int UpdateCommentState(Comment comment);

    List<Comment> SelectComments(int state);

    List<Comment> SelectCommentsByMId(int mId);

}
