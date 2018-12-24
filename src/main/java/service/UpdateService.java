package service;

import bean.Project;
import bean.User;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateService {

    /**
     * 更改项目资料
     * @param project
     * @param file
     */
    public void UpdateProject(String project, MultipartFile file);

    public boolean updateModel(User user, String model, MultipartFile[] multipartFile);

    public boolean updateLogContext(Long lId,String lContext);

    public int changeInvState(Long pId,String uName);
}
