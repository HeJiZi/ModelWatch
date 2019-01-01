package controller;

import bean.Log;
import bean.Project;
import bean.User;
import bean.Model;
import dao.ProjectDao;
import dto.ListObject;
import dto.LogDto;
import dto.project.PjOverviewDto;
import dto.project.ProjectDataDto;
import dto.project.ProjectStaticDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import service.DeleteService;
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

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Long AddProject(@RequestParam("project") String project, HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
        if(user==null||!(request instanceof MultipartHttpServletRequest))
            return 0L;
        MultipartFile file=null;
        file=MyFileUtil.getFile(request);
        return manageService.createProject(user,project,file);

    }

    @Autowired
    UpdateService updateService;

    @ResponseBody
    @RequestMapping(value = "/proData",method = RequestMethod.POST)
    public boolean updateProject(@RequestParam("project") String project,HttpServletRequest request){
        //从前端获取要传入的值
        MultipartFile file=null;
        if (request instanceof MultipartHttpServletRequest) {
            file=MyFileUtil.getFile(request);
        }
        return updateService.UpdateProject(project,file);
    }

    @Autowired
    DeleteService deleteService;


    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public boolean deleteProject(String password,long pId){
        return deleteService.deleteProject(password,pId);
    }

    @Autowired
    SelectService selectService;

    @ResponseBody
    @RequestMapping(value = "/{pId}",method = RequestMethod.GET)
    public PjOverviewDto getProjectOverview(@PathVariable Long pId){
        return  selectService.selectProject(pId);
    }

    @ResponseBody
    @RequestMapping(value = "/data/{pId}",method = RequestMethod.GET)
    public ProjectDataDto getData(@PathVariable Long pId){
        return  selectService.selectProjectData(pId);
    }

    @ResponseBody
    @RequestMapping(value = "/log/{pId}",method = RequestMethod.GET)
    public ListObject getData(@PathVariable Long pId,
                        @RequestParam(value = "currentPage") int currentPage,
                        @RequestParam(value = "limit") int limit){
        return selectService.selectLog(pId,currentPage,limit);
        //return logList;
    }

    @ResponseBody
    @GetMapping(value = "/static/{pId}")
    public ProjectStaticDto getStatic(@PathVariable long pId){
        return selectService.getProjectStatic(pId);
    }



}
