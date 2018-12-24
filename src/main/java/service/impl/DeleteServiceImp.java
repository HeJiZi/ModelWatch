package service.impl;

import dao.InvitationDao;
import dao.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteServiceImp implements service.DeleteService {
    @Autowired
    LogDao logDao;
    public boolean DeleteLog(Long lId) {
        return (logDao.deleteLog(lId)==1);
    }
    @Autowired
    InvitationDao invitationDao;
    public int deleteInvitation(long pId, int uId){
        return invitationDao.deleteInvitation(pId,uId);
    }
    public int deleteWait(Long pId,String uName){
        return invitationDao.deleteWaitItem(pId,uName);
    }
}
