package dao;

import bean.Log;
import bean.Model;
import bean.Project;
import bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RunWith(value =SpringJUnit4ClassRunner.class)

@ContextConfiguration("classpath:spring/spring-dao.xml")
public class LogDaoTest {

    @Autowired
    LogDao logDao;

    @Test
    public void addLog() {
        Log log = new Log();
        log.setlId(new Long(1));
        log.setlContext("adfsadf");
        User user = new User();
        user.setuId(1);
        log.setUser(user);
        Project project = new Project();
        project.setpId(new Long(1));
        log.setProject(project);
        Model model = new Model();
        model.setmId(new Long(1));
        log.setModel(model);
        System.out.println(logDao.addLog(log));
    }

    @Test
    public void deleteLog(){
        System.out.println(logDao.deleteLog(new Long(41)));
    }

    @Test
    public void selectLog(){
        System.out.println(logDao.filterLog(null,null,"HeJiZi",null,new Long(1)).get(0).getlContext());

    }

}