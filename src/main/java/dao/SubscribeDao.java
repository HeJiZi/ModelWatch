package dao;

public interface SubscribeDao {
    /**
     * 用户添加关注
     * @param pid
     * @param uid
     * @return
     */
    int addSubscribe(int pid,int uid);

    /**
     * 用户取消关注
     * @param pid
     * @param uid
     * @return
     */
    int deleteSubscribe(int pid,int uid);
}
