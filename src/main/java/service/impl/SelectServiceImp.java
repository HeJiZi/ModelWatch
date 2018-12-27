package service.impl;

import bean.*;
import dao.*;
import dto.InvitationDto;
import dto.ListObject;
import entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.SelectService;
import util.TransCharsetUtil;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SelectServiceImp implements SelectService {
    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ModelDao modelDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private LogDao logDao;

    @Autowired
    private CommentsDao commentsDao;

    @Autowired
    private InvitationDao invitationDao;

    public List<Project> getUserProjects(String uId) {
        return projectDao.getProjectsByCreateUid(Integer.parseInt(uId));
    }

    public List<Project> getSubProjects(String uId) {
        return projectDao.getSubProjects(Integer.parseInt(uId));
    }

    public List<Model> getMarkModels(String uId) {
        return modelDao.getModelsByMarkUId(Integer.parseInt(uId));
    }

    public List<Comment> getCommentsInModel(String mId){return commentsDao.SelectCommentsByMId(Integer.parseInt(mId));}

    public Model selectModelByMId(String mId) {
        return modelDao.getModelById(Long.valueOf(mId));
    }

    public User getUserData(String uId) {
        return userDao.selectUserByUid(Integer.parseInt(uId));
    }

    public List<User> selectUser(String content) {
        return userDao.selectUsersByContent(content);
    }

    public Project getProjectData(String pId) {
        return projectDao.getProjectById(Long.parseLong(pId));
    }

    public Project selectProject(long pId){
        return projectDao.getProjectById(pId);
    }

    public List<Log> selectLog(long pId,int currentPage,int limit){
        Page page = new Page(limit);
        page.setCurrentPageNum(currentPage);
        return logDao.getLogByPidPage(pId,page);
    }
    public List<Log> filterLog(String beginTime,String endTime,String uUsername,String mName,Long pId,int currentPage,int limit){
        Timestamp beginTimestamp = null;
        Timestamp endTimestamp = null;
        if(beginTime != null && endTime != null){
            beginTimestamp = new Timestamp(Long.parseLong(beginTime));
            endTimestamp = new Timestamp(Long.parseLong(endTime));
        }

        uUsername = TransCharsetUtil.transISOToUTF(uUsername);
        mName = TransCharsetUtil.transISOToUTF(mName);

        Page page = new Page(limit);
        page.setCurrentPageNum(currentPage);

        return logDao.filterLogPage(beginTimestamp,endTimestamp,uUsername,mName,pId,page);
    }

    public List<Model> selectModel(long pId){
        return modelDao.getProjectModelByPid(pId);
    }

    public ListObject selectCollaborators(long pId, int currentPage) {
        Page page = new Page(5);
        page.setCurrentPageNum(currentPage);
        List<User> users = invitationDao.selectCollaboratorsByPidPage(pId,page);
        return new ListObject(users,page);
    }

    public InvitationDto findInvMessage(long pId,String myName){
        Invitation invitation=invitationDao.findMessage(pId,myName);
        String uAvater=invitation.getManager().getuAvater();
        String uUsername=invitation.getManager().getuUsername();
        String mAvater=invitation.getCollaborator().getuAvater();
        String pName=invitation.getProject().getpName();
        return (new InvitationDto(uAvater,uUsername,mAvater,pName));
    }
}
