package dao;

import bean.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    /**
     * 根据用户名与密码查询用户
     * @param username
     * @param password
     * @return 如果查询不到，返回null
     */
    User selectUserByUNAndPass(@Param("username") String username,
                               @Param("password")String password);

    /**
     * 根据用户名查询用户
     * @param username
     * @return 如果查询不到返回null
     */
    User selectUserByUsername(String username);

    /**
     * 根据用户id查询用户
     * @param uId
     * @return
     */
    User selectUserByUid(int uId);

    /**
     * 添加用户
     * @param username
     * @param password
     * @return 添加失败返回0
     */
    int addUser(@Param("username") String username,
                @Param("password") String password);

    /**
     * 更改用户的资料
     * @param user
     * @return 找不到更改的用户返回0
     */
    int updateData(User user);

    /**
     * 更改密码
     * @param uId
     * @param password
     * @return 找不到更改的用户返回0
     */
    int updatePassword(@Param("uId") int uId,
                       @Param("password") String password);

}
