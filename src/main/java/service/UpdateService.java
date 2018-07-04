package service;

import bean.User;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateService {
    /**
     * 更改项目资料
     * @param pId
     * @param pContext
     */
    public void UpdateProject(int pId,String pContext);

    public boolean updateModel(User user, String model, MultipartFile[] multipartFile);

}
