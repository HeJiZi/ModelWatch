package dao;

import bean.Invitation;
import bean.User;
import entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvitationDao {
    int addInvitation(@Param("pid") int pid,@Param("uid") int uid);
    int addInvByNamePid(@Param("pid") Long pid,@Param("uUsername") String uUsername);
    int deleteInvitation(@Param("pid") long pid,@Param("uid") int uid);
    List<User> selectInvitationByPid(@Param("pid") int pid);
    List<User> selectCollaboratorsByPidPage(@Param("pid")long pid, @Param("page")Page page);
    int alterInvState(@Param("pId") long pId,@Param("uName") String uName);
    int deleteWaitItem(@Param("pId") Long pId,@Param("uName") String uName);
    Invitation findMessage(@Param("pId") long pId,@Param("myName") String myName);
    List<User> getMailByName(@Param("myName") String myName);
    List<User> getUserName(@Param("pId") Long pId);
}
