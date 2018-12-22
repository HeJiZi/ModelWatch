package service;

import bean.Comment;
import bean.User;
import dto.ListDto;
import org.springframework.web.multipart.MultipartFile;

//用户服务，关于用户管理的接口都在这里调用
public interface UserService {
    /**
     * 更新用户资料
     * @param user
     */
    User updateUserProfile(String user, MultipartFile file);

    /**
     * 更新用户密码
     * @param user
     */
    void updateUserPass(User user);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return 登录失败返回null
     */
    User login(String username,String password);

    /**
     * 用户注册
     * @param username
     * @param password
     * @return 注册成功返回true，注册失败返回false
     */
    boolean register(String username,String password);

    /**
     * 添加协作者
     * @param addid
     * @return
     */
    boolean add(int addid);

    User selectUser(int uId);

    boolean addComment(Comment comment);

    ListDto selectByName(String uName);

}
