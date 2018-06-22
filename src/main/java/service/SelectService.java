package service;

import bean.Model;
import bean.Project;
import bean.User;


import java.util.List;

public interface SelectService {
    /**
     * 根据用户创建的项目
     * @param uId
     * @return
     */
    List<Project> getUserProjects(String uId);

    /**
     * 获得用户关注的项目
     * @param uId
     * @return
     */
    List<Project> getSubProjects(String uId);

    /**
     * 获得用户收藏的模型
     * @param uId
     * @return
     */
    List<Model> getMarkModels(String uId);

    User getUserData(String uId);

    public void selectProject(int pId,String pName,String pContext);
}
