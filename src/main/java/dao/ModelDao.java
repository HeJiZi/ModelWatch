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
}
