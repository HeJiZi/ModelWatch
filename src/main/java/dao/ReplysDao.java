package dao;

import bean.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplysDao {

    int UpdateReplyPositiveNum(@Param("repId") int repId);

    int UpdateReplyObjectiveNum(@Param("repId") int repId);

    int AddReply(Reply reply);

    List<Reply> SelectReplysByComId(int comId);
}
