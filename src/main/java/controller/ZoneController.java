package controller;

import bean.Model;
import bean.Project;
import bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import service.SelectService;
import service.UserService;
import util.MyFileUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/zone")
public class ZoneController {

    @Autowired
    SelectService selectService;

    @Autowired
    UserService userService;



    @ResponseBody
    @RequestMapping(value = "/JoinPros/{uId}",method = RequestMethod.GET)
    public  List<Project> joinPro(@PathVariable String uId){
        return selectService.getUserProjects(uId);
    }

    @ResponseBody
    @RequestMapping(value = "/SubPros/{uId}",method = RequestMethod.GET)
    public  List<Project> subPros(@PathVariable String uId){
        return selectService.getSubProjects(uId);
    }

    @ResponseBody
    @RequestMapping(value = "/MarkModels/{uId}",method = RequestMethod.GET)
    public  List<Model> markModels(@PathVariable String uId){
        return selectService.getMarkModels(uId);
    }

    @ResponseBody
    @RequestMapping(value = "/data/{uId}",method = RequestMethod.GET)
    public User getData(@PathVariable String uId){
        return  selectService.getUserData(uId);
    }



    @ResponseBody
    @RequestMapping(value = "/data",method = RequestMethod.PUT)
    public boolean UpdateData(@RequestParam("user") String user, HttpServletRequest request){
        MultipartFile file=null;
        if (request instanceof MultipartHttpServletRequest) {
            file=MyFileUtil.getFile(request);
        }

        userService.updateUserProfile(user,file);

        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/pass",method = RequestMethod.PUT)
    public boolean UpdatePassword(@RequestBody User user){
        userService.updateUserPass(user);
        return true;
    }
}
