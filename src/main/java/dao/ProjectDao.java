package dao;

import bean.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectDao {
    /**
     * 根据项目id查询项目
     * @param pid
     * @return
     */
    Project getProjectById(long pid);



    /**
     * 根据uid获取用户创建项目
     * @param uid
     * @return
     */
    List<Project> getProjectsByCreateUid(int uid);

    /**
     * 获取用户关注的项目
     * @param uid
     * @return
     */
    List<Project> getSubProjects(int uid);

    /**
     * 添加项目记录
     * @param project
     * @return
     */
    int addProject(Project project);
    /**
     * 更改项目资料
     * @param pId
     * @param pContext
     * @return 更新成功返回1，更新失败返回0
     */
    int updateProjectData(@Param("pId") int pId,@Param("pContext") String pContext);

    /**
     * 项目显示
     * @param pId
     * @param pPname
     * @param pContext
     * @return
     */
    int showProjectData(@Param("pId") int pId,@Param("pPname") String pPname,@Param("pContext") String pContext);

    /**
     * 删除项目
     * @param pId
     * @return
     */
    int deleteProject(@Param("pId") int pId);
}

