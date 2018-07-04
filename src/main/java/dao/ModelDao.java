package dao;


import bean.Model;

import java.util.List;

public interface ModelDao {
    /**
     * 根据用户id获取用户收藏的模型
     * @param uId
     * @return
     */
    List<Model> getModelsByMarkUId(int uId);

    /**
     * 获取全部模型
     * @return
     */
    List<Model> getAllModels();
    
    List<Model> getModelByPid(long pId);

    List<Model> getProjectModelByPid(long pId);
}
