package controller;

import bean.Log;
import bean.Project;
import bean.User;
import bean.Model;
import dao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import service.ManageService;
import service.SelectService;
import service.UpdateService;
import util.MyFileUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//import jdk.incubator.http.HttpResponse;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ManageService manageService;

    @Autowired
    ProjectDao projectDao;

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.POST)
    public boolean UpdateData(@RequestParam("project") String project, HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
        if(user==null||!(request instanceof MultipartHttpServletRequest))
            return false;
        MultipartFile file=null;
        file=MyFileUtil.getFile(request);
        return manageService.createProject(user,project,file);

    }

    @Autowired
    UpdateService updateService;

    @RequestMapping(value = "/proData",method = RequestMethod.PUT)
    public void updateProject(HttpServletRequest request){
        //从前端获取要传入的值
        int pId= Integer.parseInt(request.getParameter("pId"));
        String des=request.getParameter("des");
        String bacg=request.getParameter("bacg");
        updateService.UpdateProject(pId,des);
    }

    @Autowired
    SelectService selectService;

    @ResponseBody
    @RequestMapping(value = "/data/{pId}",method = RequestMethod.GET)
    public Project getData(@PathVariable Long pId){
        return  selectService.selectProject(pId);
    }

    @ResponseBody
    @RequestMapping(value = "/diary/{pId}",method = RequestMethod.GET)
    public List<Log> getData(@PathVariable long pId){
        return  selectService.selectLog(pId);
    }

    @ResponseBody
    @RequestMapping(value = "/model/{pId}",method = RequestMethod.GET)
    public List<Model> getModel(@PathVariable long pId){
        return  selectService.selectModel(pId);
    }

    @ResponseBody
    @RequestMapping(value = "/user/{pId}",method = RequestMethod.GET)
    public Project getProjectUser(@PathVariable("pId") long pId){
        return projectDao.getProjectAndUserByPid(pId);
    }
}
