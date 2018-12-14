package dao;

import bean.User;
import entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvitationDao {
    int addInvitation(@Param("pid") int pid,@Param("uid") int uid);
    int addInvitationByUNameAndPid(@Param("pid") int pid,@Param("uUsername") String uUsername);
    int deleteInvitation(@Param("pid") int pid,@Param("uid") int uid);
    List<User> selectInvitationByPid(@Param("pid") int pid);

    List<User> selectCollaboratorsByPidPage(@Param("pid")long pid, @Param("page")Page page);
}
