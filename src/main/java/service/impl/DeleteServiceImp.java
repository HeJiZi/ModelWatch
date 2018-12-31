package service.impl;

import bean.Project;
import bean.User;
import dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
public class DeleteServiceImp implements service.DeleteService {
    @Autowired
    LogDao logDao;
    public boolean deleteLog(Long lId) {
        return (logDao.deleteLog(lId)==1);
    }
    @Autowired
    InvitationDao invitationDao;

    @Autowired
    SubscribeDao subscribeDao;

    @Autowired
    ProjectDao projectDao;

    @Autowired
    UserDao userDao;

    @Autowired
    HttpSession session;

    public int deleteInvitation(long pId, int uId){
        return invitationDao.deleteInvitation(pId,uId);
    }
    public int deleteWait(Long pId,String uName){
        return invitationDao.deleteWaitItem(pId,uName);
    }

    public boolean deleteSubscribe(long pId) {
        User user = (User) session.getAttribute("user");
        return subscribeDao.deleteSubscribe(pId,user.getuId())!=0;
    }

    @Transactional
    public boolean deleteProject(String password, long pId) {
        User user = (User) session.getAttribute("user");
        if(user==null) return false;
        Project project =projectDao.getProjectData(pId);
        if(password.equals(project.getUser().getuPassword())&& user.getuId() == project.getUser().getuId()){
            projectDao.deleteProject(pId);
            User nu = userDao.selectUserByUid(user.getuId());
            user.setuModelNum(nu.getuModelNum());
            user.setuProjectNum(nu.getuProjectNum());
            return true;
        }
        return false;
    }
}
