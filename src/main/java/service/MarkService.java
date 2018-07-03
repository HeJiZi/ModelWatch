package service;

public interface MarkService {
    /**
     * 添加用户收藏
     * @param mid
     * @param uid
     * @return
     */
    int addMark(int mid,int uid);

    /**
     * 用户取消收藏
     * @param mid
     * @param uid
     * @return
     */
    int deleteMark(int mid,int uid);

    /**
     * 查找用户是否收藏该模型
     * @param mid
     * @param uid
     * @return
     */
    int selectMark(int mid,int uid);
}
