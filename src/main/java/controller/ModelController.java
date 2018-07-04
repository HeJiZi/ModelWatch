package controller;

import bean.Model;
import bean.User;
import dao.ModelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import service.SelectService;
import service.UpdateService;
import util.MyFileUtil;
import service.ManageService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ModelController {
    @Autowired
    ModelDao modelDao;

    @Autowired
    SelectService selectService;

    @Autowired
    UpdateService updateService;

    @Autowired
    ManageService manageService;

    @GetMapping(value = "/model")
    public List<Model> getAllModel(){
        return modelDao.getAllModels();
    }


    @GetMapping(value = "/model/{mId}")
    public Model getModel(@PathVariable String mId){
       return selectService.selectModelByMId(mId);
    }

    @PostMapping(value = "/upmodel")
    public boolean upDateModel(@RequestParam("model") String model, HttpServletRequest request) {
        MultipartFile[] files = null;
        if (request instanceof MultipartHttpServletRequest) {
            files = MyFileUtil.getFiles(request);
        }
        User user = (User) request.getSession().getAttribute("user");
        return updateService.updateModel(user, model, files);
    }
    @PostMapping(value = "/model")
    public long addModel(HttpServletRequest request, @RequestBody Model model){
        User user=(User)request.getSession().getAttribute("user");
        manageService.addModel(user, model, model.getProject().getpId());
        return model.getmId();
    }
}
