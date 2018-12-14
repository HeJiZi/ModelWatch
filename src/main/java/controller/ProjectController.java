package controller;

import bean.Log;
import bean.Project;
import bean.User;
import bean.Model;
import dao.ProjectDao;
import dto.ListObject;
import dto.LogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import service.ManageService;
import service.SelectService;
import service.UpdateService;
import util.DtoListUtil;
import util.MyFileUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

    @ResponseBody
    @RequestMapping(value = "/proData",method = RequestMethod.POST)
    public boolean updateProject(HttpServletRequest request,@RequestParam("project") String project){
        //从前端获取要传入的值
        MultipartFile file=null;
        if (request instanceof MultipartHttpServletRequest) {
            file=MyFileUtil.getFile(request);
        }
        updateService.UpdateProject(project,file);
        return true;
    }

    @Autowired
    SelectService selectService;

    @ResponseBody
    @RequestMapping(value = "/data/{pId}",method = RequestMethod.GET)
    public Project getData(@PathVariable Long pId){
        return  selectService.selectProject(pId);
    }

    @ResponseBody
    @RequestMapping(value = "/log/{pId}",method = RequestMethod.GET)
    public List getData(@PathVariable long pId){
        List<Log> logList =  selectService.selectLog(pId);
        return (List<LogDto>)DtoListUtil.transelateList(logList,LogDto.class);
        //return logList;
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
