package controller;

import bean.Model;
import bean.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.SelectService;

import java.util.List;


@Controller
@RequestMapping("/zone")
public class ZoneController {

    @Autowired
    SelectService selectService;

    @ResponseBody
    @RequestMapping(value = "/JoinPros/{uId}",method = RequestMethod.GET)
    public  List<Project> JoinPro(@PathVariable String uId){
        return selectService.getUserProjects(uId);
    }

    @ResponseBody
    @RequestMapping(value = "/SubPros/{uId}",method = RequestMethod.GET)
    public  List<Project> SubPros(@PathVariable String uId){
        return selectService.getSubProjects(uId);
    }

    @ResponseBody
    @RequestMapping(value = "/MarkModels/{uId}",method = RequestMethod.GET)
    public  List<Model> MarkModels(@PathVariable String uId){
        return selectService.getMarkModels(uId);
    }
}
