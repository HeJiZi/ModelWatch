package controller;

import bean.Project;
import bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import service.ManageService;
import service.SelectService;
import service.UpdateService;
import service.impl.SelectServiceImpl;
import service.impl.UpdateServiceImp;
import util.MyFileUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ManageService manageService;

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


}
