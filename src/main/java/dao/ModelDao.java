package dao;


import bean.Model;
import com.sun.org.apache.xpath.internal.operations.Mod;

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

    /**
     * 根据id获得模型
     * @param mId
     * @return
     */
    Model getModelById(Long mId);

    /**
     *
     * @param model
     * @return
     */
    int updateModel(Model model);
}
