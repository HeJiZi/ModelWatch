package service;

import bean.Project;
import bean.User;
import org.springframework.web.multipart.MultipartFile;

//项目管理服务，有关项目管理的接口都在这里调用
public interface ManageService {

    /**
     * 创建项目
     * @param user
     * @param project
     * @param multipartFile 预览图文件
     * @return 创建成功返回true，创建失败返回false
     */
    boolean createProject(User user, String project, MultipartFile multipartFile);
}
