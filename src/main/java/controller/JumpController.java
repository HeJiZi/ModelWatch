package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class JumpController {
    @RequestMapping(value = "/sign",method = RequestMethod.GET)
    public String jumpToSign(){
        return "signup";
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String jumpToIndex(){
        return "show";
    }

    @RequestMapping(value = "/crePMenu",method = RequestMethod.GET)
    public String jumptoCreate(){
        return "proCreate";
    }

    @RequestMapping(value = "/zone/{uId}",method = RequestMethod.GET)
    public String jumpToZone(@PathVariable int uId){
        return "personalZone";
    }

    @RequestMapping(value = "/project/{pId}",method = RequestMethod.GET)
    public String toProject(){
        return "project";
    }

    @RequestMapping(value = "/profile/{uId}",method = RequestMethod.GET)
    public String toProfile(){
        return "profile";
    }

    @RequestMapping(value = "/proData/{pId}",method = RequestMethod.GET)
    public String toChange(){
        return "change";
    }

    @RequestMapping(value = "/model/dis/{mId}",method = RequestMethod.GET)
    public String toDisplay(){
        return "display";
    }

    @RequestMapping(value = "/model/edi/{mId}",method = RequestMethod.GET)
    public String toEditor(){
        return "editor";
    }

}
