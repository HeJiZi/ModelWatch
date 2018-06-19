package service.impl;

import bean.Model;
import bean.Project;
import dao.ModelDao;
import dao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.SelectService;

import java.util.List;

@Service
public class SelectServiceImpl implements SelectService {
    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ModelDao modelDao;

    public List<Project> getUserProjects(String uId) {
        return projectDao.getProjectsByCreateUid(Integer.parseInt(uId));
    }

    public List<Project> getSubProjects(String uId) {
        return projectDao.getSubProjects(Integer.parseInt(uId));
    }

    public List<Model> getMarkModels(String uId) {
        return modelDao.getModelsByMarkUId(Integer.parseInt(uId));
    }
}
