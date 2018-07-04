package dao;

import bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface invitationDao {
    int addInvitation(@Param("pid") int pid,@Param("uid") int uid);
    int addInvitationByUNameAndPid(@Param("pid") int pid,@Param("uUsername") String uUsername);
    int deleteInvitation(@Param("pid") int pid,@Param("uid") int uid);
    List<User> selectInvitationByPid(@Param("pid") int pid);
}
