package dao;

import org.apache.ibatis.annotations.Param;

public interface MarkDao {
    /**
     * 用户添加收藏
     * @param uid
     * @param mid
     * @return
     */
    int addMark(@Param("mid") int mid,@Param("uid") int uid);

    /**
     * 用户取消收藏
     * @param mid
     * @param uid
     * @return
     */
    int deleteMark(@Param("mid") int mid,@Param("uid") int uid);

    /**
     * 查找是否收藏过该模型
     * @param mid
     * @param uid
     * @return
     */
    int selectMark(@Param("mid") int mid,@Param("uid") int uid);
}
