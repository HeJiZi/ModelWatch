package service.impl;

import bean.Log;
import bean.Model;
import bean.Project;
import bean.User;
import dao.LogDao;
import dao.ModelDao;
import dao.ProjectDao;
import exception.UpdateModelException;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.UpdateService;
import util.MyFileUtil;

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


    /**
     * 更改项目资料
     *
     * @param project
     * @param file
     */
    public void UpdateProject(String project, MultipartFile file) {

    }

    public boolean updateModel(User user, String model, MultipartFile[] files) {
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
        log.setlContext(user.getuUsername()+"对模型"+mo.getmId()+"进行了上传");
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
        return (logDao.updateLogContext(lId,lContext)==1);
    }
}
