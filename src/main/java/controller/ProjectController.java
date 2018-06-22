package controller;

import bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import service.ManageService;
import util.MyFileUtil;

import javax.servlet.http.HttpServletRequest;

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
}
