package controller;

import bean.Model;
import dao.ModelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ModelController {
    @Autowired
    ModelDao modelDao;

    @GetMapping(value = "/model")
    public List<Model> getAllModel(){
        return modelDao.getAllModels();
    }
}
