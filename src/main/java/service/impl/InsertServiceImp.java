package service.impl;

import dao.InvitationDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.InsertService;

@Service
public class InsertServiceImp implements InsertService {

    @Autowired
    private InvitationDao invitationDao;

    public int addInvByUnameAndPid(Long pid,String uUsername){
        return invitationDao.addInvByNamePid(pid,uUsername);
    }
}
