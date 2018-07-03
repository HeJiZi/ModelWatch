package dao;

import bean.Model;
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
}