package dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value =SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ProjectDaoTest {

    @Autowired
    ProjectDao projectDao;


    @Test
    public void getProjectStatic() throws Exception {
        System.out.println(projectDao.getStatic(2).getPjHeatStatic());
    }

    @Test
    public void getProjectById() {
        System.out.println(projectDao.getProjectById(2,1));
    }

    @Test
    public void getProjectsByCreateUid() {
    }

    @Test
    public void getSubProjects() {
    }

    @Test
    public void addProject() {
    }

    @Test
    public void updateProjectData() {
    }

    @Test
    public void alterProjectData() {
    }

    @Test
    public void deleteProject() {
        System.out.println(projectDao.deleteProject(9));
    }
}