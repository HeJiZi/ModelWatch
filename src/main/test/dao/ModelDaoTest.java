package dao;

import bean.Model;
import bean.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(value =SpringJUnit4ClassRunner.class)

@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ModelDaoTest {

    @Autowired
    ModelDao modelDao;

    @Test
    public void getModelsByMarkUId() {
    }

    @Test
    public void getAllModels() {
        List<Model> modelList = modelDao.getAllModels();
        System.out.println(modelList.toString());
    }

    @Test
    public void addModel(){
        Model model = new Model();
        Project project = new Project();
        project.setpId(1);
        model.setProject(project);
        model.setmName("中国");
        model.setmPreview("/resources/images/5.jpeg");
        System.out.println(modelDao.addModel(model));
    }
}