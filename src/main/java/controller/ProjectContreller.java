package controller;

import bean.Project;
import bean.User;
import dao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.SelectService;
import service.UpdateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectContreller {
    @Autowired
    UpdateService updateService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public void updateProject(HttpServletRequest request){
       //从前端获取要传入的值
        int pId= Integer.parseInt(request.getParameter("pId"));
        String des=request.getParameter("des");
        updateService.UpdateProject(pId,des);
    }

    @Autowired
    SelectService selectService;

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public Project selectProject(){
        

        return null;
    }
}
