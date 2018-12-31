package dao;

import org.apache.ibatis.annotations.Param;

public interface SubscribeDao {
    /**
     * 用户添加关注
     * @param pId
     * @param uId
     * @return
     */
    int addSubscribe(@Param("pId") long pId, @Param("uId") int uId);

    /**
     * 用户取消关注
     * @param pId
     * @param uId
     * @return
     */
    int deleteSubscribe(@Param("pId") long pId,@Param("uId") int uId);


}
