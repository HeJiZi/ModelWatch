package dao;

import org.apache.ibatis.annotations.Param;

public interface SubscribeDao {
    /**
     * 用户添加关注
     * @param pid
     * @param uid
     * @return
     */
    int addSubscribe(@Param("pid") int pid, @Param("uid") int uid);

    /**
     * 用户取消关注
     * @param pid
     * @param uid
     * @return
     */
    int deleteSubscribe(@Param("pid") int pid,@Param("uid") int uid);

    /**
     * 查找用户是否关注个该项目
     * @param pid
     * @param uid
     * @return
     */
    int selectSubscribe(@Param("pid") int pid,@Param("uid") int uid);
}
