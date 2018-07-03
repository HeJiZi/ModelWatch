package dao;

import org.apache.ibatis.annotations.Param;

public interface invitationDao {
    int addInvitation(@Param("pid") int pid,@Param("uid") int uid);
    int deleteInvitation(@Param("pid") int pid,@Param("uid") int uid);
}
