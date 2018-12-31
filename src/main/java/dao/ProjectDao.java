package dao;

import bean.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectDao {
    /**
     * 根据项目id查询项目
     * @param pId
     * @return
     */
    Project getProjectById(@Param("pId") long pId,@Param("uId") int uId);

    Project getStatic(long pId);

    Project getProjectData(long pId);


    /**
     * 根据uid获取用户创建项目
     * @param uId
     * @return
     */
    List<Project> getProjectsByCreateUId(int uId);

    /**
     * 获取用户关注的项目
     * @param uId
     * @return
     */
    List<Project> getSubProjects(int uId);

    /**
     * 添加项目记录
     * @param project
     * @return
     */
    int addProject(Project project);
    /**
     * 更改项目资料
     * @return 更新成功返回1，更新失败返回0
     */
    int updateProjectData(Project project);


    /**
     * 删除项目
     * @param pId
     * @return
     */
    int deleteProject(@Param("pId") long pId);


}

