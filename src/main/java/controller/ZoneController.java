package controller;

import bean.Model;
import bean.Project;
import bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.SelectService;

import java.util.List;


@Controller
@RequestMapping("/zone")
public class ZoneController {

    @Autowired
    SelectService selectService;

    @ResponseBody
    //设置想要跳转的父路径
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
    @RequestMapping(value = "/data",method = RequestMethod.POST)
    public User UpdateData(@RequestBody User user){
        System.out.println(user);
        return user;
    }
}
