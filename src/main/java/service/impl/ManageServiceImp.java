package service.impl;

import bean.Log;
import bean.Model;
import bean.Project;
import bean.User;
import dao.LogDao;
import dao.ModelDao;
import dao.ProjectDao;
import exception.CreateProException;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import service.ManageService;
import util.MyFileUtil;

import java.io.File;
import java.io.IOException;

@Service
public class ManageServiceImp implements ManageService {
    @Autowired
    ProjectDao projectDao;

    @Autowired
    LogDao logDao;

    @Autowired
    ModelDao modelDao;

    @Transactional
    public boolean createProject(User user, String project, MultipartFile file) {
        String[] fileName=file.getOriginalFilename().split("\\.");
        String suffix=fileName[fileName.length-1];


        int res;
        Project pj= (Project) JSONObject.toBean(JSONObject.fromObject(project),Project.class);
        String fileSaveName=user.getuId()+"-"+System.currentTimeMillis()+"."+suffix;
        pj.setUser(user);
        pj.setpPreview("/resources/upload/pjCover/"+fileSaveName);


        Log log = new Log();
        log.setProject(pj);
        log.setlContext("用户" + user.getuUsername() + "创建了项目");


        try {
            res = projectDao.addProject(pj);
            if (res == 0)
                return false;
            logDao.addLog(log);
        }
        catch (CreateProException e){
            throw e;
        }


        try {
            File outDir =new File(MyFileUtil.getResourcesUrl()+"/upload/pjCover");
            if(!outDir.exists())
                outDir.mkdir();
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(outDir,fileSaveName));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Transactional
    public boolean addModel(User user,Model model,long pId){
        Log log = new Log();
        Project project = new Project();
        project.setpId(pId);
        log.setProject(project);
        log.setlContext("用户" + user.getuUsername() + "创建了项目");
        try {
            if (modelDao.addModel(model)==0){
                return false;
            }
            logDao.addLog(log);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
