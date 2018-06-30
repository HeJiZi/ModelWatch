package service.impl;

import dao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UpdateService;

@Service
public class UpdateServiceImp implements UpdateService {
    @Autowired
    private ProjectDao projectDao;
    public void UpdateProject(int pId, String pContext) {
        projectDao.updateProjectData(pId,pContext);
    }
}
