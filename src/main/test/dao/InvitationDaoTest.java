package dao;

import entity.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value =SpringJUnit4ClassRunner.class)

@ContextConfiguration("classpath:spring/spring-dao.xml")
public class InvitationDaoTest {

    @Autowired
    InvitationDao InvitationDao;

    @Test
    public void addInvitation() {
        System.out.println(InvitationDao.addInvitation(1,2));
        InvitationDao.addInvitation(1,3);
    }

    @Test
    public void deleteInvitation() {
        System.out.println(InvitationDao.deleteInvitation(1,2));
    }

    @Test
    public void selectInvitationByPid() {
        System.out.println(InvitationDao.selectInvitationByPid(1));
    }

    @Test
    public void addInvitationByUNameAndPid(){
        System.out.println(InvitationDao.addInvitationByUNameAndPid(1,"Pig"));
    }

    @Test
    public void selectCollaboratorsByPidPage(){
        System.out.println(InvitationDao.selectCollaboratorsByPidPage(1,new Page(1)));
    }

}