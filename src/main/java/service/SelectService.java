package service;

import bean.Model;
import bean.Project;
import bean.User;
import bean.Log;


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

    Model selectModelByMId(String mId);

    User getUserData(String uId);

    Project selectProject(long pId);

    List<Log> selectLog(long pId);
    List<Model> selectModel(long pId);
}
