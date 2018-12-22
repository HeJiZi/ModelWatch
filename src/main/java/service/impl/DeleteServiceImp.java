package service.impl;

import dao.InvitationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.DeleteService;

@Service
public class DeleteServiceImp implements DeleteService {

    @Autowired
    private InvitationDao invitationDao;


    public int DeleteLog(Long lId) {
        return 0;
    }

    public int deleteInvitation(long pId, int uId){
        return invitationDao.deleteInvitation(pId,uId);
    }
}
