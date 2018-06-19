package service.impl;

import bean.Project;
import bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.SelectService;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SelectServiceImplTest {

    @Autowired
    SelectService selectService;
    @Test
    public void getUserProjects() {
        List<Project> projects=selectService.getUserProjects("1");
        for (Project pro :
                projects) {
            System.out.println(pro);
        }
    }

    @Test
    public void getSubProjects() {
    }

    @Test
    public void getMarkModels() {
    }
}