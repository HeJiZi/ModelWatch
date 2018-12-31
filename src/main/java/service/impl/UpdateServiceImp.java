package service.impl;

import bean.Log;
import bean.Model;
import bean.Project;
import bean.User;
import dao.InvitationDao;
import dao.LogDao;
import dao.ModelDao;
import dao.ProjectDao;
import exception.CreateProException;
import exception.UpdateModelException;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import service.UpdateService;
import util.MyFileUtil;
import util.TransCharsetUtil;

import java.io.File;
import java.io.IOException;

@Service
public class UpdateServiceImp implements UpdateService {
    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ModelDao modelDao;

    @Autowired
    private LogDao logDao;

    @Autowired
    private InvitationDao invitationDao;
    /**
     * 更改项目资料
     *
     * @param project
     * @param file
     */
    @Transactional
    public boolean UpdateProject(String project, MultipartFile file) {
        Project pj= (Project) JSONObject.toBean(JSONObject.fromObject(project),Project.class);
        String fileSaveName =null;
        if(file!=null){
            String[] fileName=file.getOriginalFilename().split("\\.");
            String suffix=fileName[fileName.length-1];
            fileSaveName=pj.getpId()+"."+suffix;
            pj.setpPreview("/resources/upload/pjCover/"+fileSaveName);
        }

        int res =projectDao.updateProjectData(pj);

        if(file!=null){
            try {
                File outDir =new File(MyFileUtil.getResourcesUrl()+"/upload/pjCover");
                FileUtils.copyInputStreamToFile(file.getInputStream(),new File(outDir,fileSaveName));
            } catch (IOException e) {
                e.printStackTrace();
                res =0;
            }
        }

        return res!=0;
    }

    public boolean updateModel(User user, String model, MultipartFile[] files,String lContext) {
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd"}) );
        Model mo=(Model)JSONObject.toBean(JSONObject.fromObject(model),Model.class);
        if(files.length!=0){
            for (int i=0;i<files.length; i++) {
                MultipartFile file=files[i];
                String upUrl;
                if(i==1) upUrl="/upload/model";
                else upUrl="/upload/mPreview";
                File outDir =new File(MyFileUtil.getResourcesUrl()+upUrl);
                String[] fileName=file.getOriginalFilename().split("\\.");
                String suffix=fileName[fileName.length-1];
                String fName=mo.getmId()+"."+suffix;

                try {
                    if(!outDir.exists())
                        outDir.mkdir();
                    FileUtils.copyInputStreamToFile(file.getInputStream(),new File(outDir,fName));
                    if(i==1) mo.setmData("/resources"+upUrl+"/"+fName);
                    else mo.setmPreview("/resources"+upUrl+"/"+fName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        Log log=new Log();
        log.setProject(mo.getProject());
        log.setlContext(lContext);
        boolean flag=false;
        try {
            logDao.addLog(log);
            modelDao.updateModel(mo);
            flag = true;
        }
        catch (UpdateModelException e){
            throw e;
        }

        return flag;
    }

    public boolean updateLogContext(Long lId,String lContext) {
        lContext = TransCharsetUtil.transISOToUTF(lContext);
        return (logDao.updateLogContext(lId,lContext)==1);
    }

    public int changeInvState(Long pid,String uName){
        return (invitationDao.alterInvState(pid,uName));
    }
}
