package controller;

import bean.Model;
import bean.User;
import dao.ModelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ManageService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ModelController {
    @Autowired
    ModelDao modelDao;

    @Autowired
    ManageService manageService;

    @GetMapping(value = "/model")
    public List<Model> getAllModel(){
        return modelDao.getAllModels();
    }

    @PostMapping(value = "/model")
    public Boolean addModel(HttpServletRequest request, @RequestBody Model model){
        User user=(User)request.getSession().getAttribute("user");
        return manageService.addModel(user, model, model.getProject().getpId());
    }
}
