package dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(value =SpringJUnit4ClassRunner.class)

@ContextConfiguration("classpath:spring/spring-dao.xml")
public class InvitationDaoTest {

    @Autowired
    InvitationDao invitationDao;

    @Test
    public void addInvitation() {
        System.out.println(invitationDao.addInvitation(1,2));
        invitationDao.addInvitation(1,3);
    }

    @Test
    public void deleteInvitation() {
        System.out.println(invitationDao.deleteInvitation(1,2));
    }

    @Test
    public void selectInvitationByPid() {
        System.out.println(invitationDao.selectInvitationByPid(1));
    }

    @Test
    public void addInvitationByUNameAndPid(){
        System.out.println(invitationDao.addInvitationByUNameAndPid(1,"Pig"));
    }
}