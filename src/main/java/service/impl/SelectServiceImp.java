package service.impl;

import bean.*;
import dao.*;
import dto.InvitationDto;
import dto.ListObject;
import dto.LogDto;
import dto.project.PjOverviewDto;
import dto.project.ProjectDataDto;
import dto.project.ProjectStaticDto;
import entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.SelectService;
import util.DtoListUtil;
import util.TransCharsetUtil;

import javax.servlet.http.HttpSession;
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

    @Autowired
    HttpSession session;

    @Autowired
    private ReplysDao replysDao;

    public List<Project> getUserProjects(String uId) {
        return projectDao.getProjectsByCreateUId(Integer.parseInt(uId));
    }

    public List<Project> getSubProjects(String uId) {
        return projectDao.getSubProjects(Integer.parseInt(uId));
    }

    public List<Model> getMarkModels(String uId) {
        return modelDao.getModelsByMarkUId(Integer.parseInt(uId));
    }

    public List<Comment> getCommentsInModel(String mId, Page page) {

        return commentsDao.SelectCommentsByMIdPage(Integer.parseInt(mId),page);
    }

    public Model selectModelByMId(String mId) {
        return modelDao.getModelById(mId);
    }

    public User getUserData(String uId) {
        return userDao.selectUserByUid(Integer.parseInt(uId));
    }

    public List<User> selectUser(String content) {
        return userDao.selectUsersByContent(content);
    }


    public PjOverviewDto selectProject(long pId){
        User user = (User) session.getAttribute("user");
        Project project = projectDao.getProjectById(pId,user==null?0:user.getuId());
        return project == null?null:new PjOverviewDto(project);
    }

    public ProjectDataDto selectProjectData(long pId) {
        Project project = projectDao.getProjectData(pId);
        return project==null?null:new ProjectDataDto(project);
    }

    public ListObject selectLog(long pId,int currentPage,int limit){
        Page page = new Page(limit);
        page.setCurrentPageNum(currentPage);
        return new ListObject(logDao.getLogByPidPage(pId,page),page);
    }
    public ListObject filterLog(String beginTime, String endTime, String uUsername, String mName, Long pId, int currentPage, int limit){
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

        List<Log> logs = logDao.filterLogPage(beginTimestamp,endTimestamp,uUsername,mName,pId,page);
        DtoListUtil.transelateList(logs, LogDto.class);

        ListObject listObject = new ListObject(logs,page);
        return listObject;
    }


    public ListObject selectCollaborators(long pId, int currentPage) {
        Page page = new Page(5);
        page.setCurrentPageNum(currentPage);
        List<User> users = invitationDao.selectCollaboratorsByPidPage(pId,page);
        return new ListObject(users,page);
    }

    public List<Reply> selectReplysById(int comId) {
        return replysDao.SelectReplysByComId(comId);
    }

    public InvitationDto findInvMessage(long pId,String myName){
        Invitation invitation=invitationDao.findMessage(pId,myName);
        String uAvater=invitation.getManager().getuAvater();
        String uUsername=invitation.getManager().getuUsername();
        String mAvater=invitation.getCollaborator().getuAvater();
        String pName=invitation.getProject().getpName();
        return (new InvitationDto(uAvater,uUsername,mAvater,pName));
    }

    @Transactional
    public ProjectStaticDto getProjectStatic(long pId) {
        Project project = projectDao.getStatic(pId);
        return project==null?null:new ProjectStaticDto(project);
    }
}
