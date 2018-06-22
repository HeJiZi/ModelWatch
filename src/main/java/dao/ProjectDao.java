package dao;

import bean.Project;

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
}
