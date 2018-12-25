package controller;

import bean.User;
import dto.ListDto;
import dto.ListObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserControler {

    @Autowired
    UserService userService;


    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public boolean Login(HttpServletRequest request){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        User user=userService.login(username,password);
        request.getSession().setAttribute("user",user);
        return user==null?false:true;
    }

    @ResponseBody
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public boolean Register(HttpServletRequest request){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        return  userService.register(username,password);
    }

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public User getUser(HttpServletRequest request){
        return (User) request.getSession().getAttribute("user");
    }

    @ResponseBody
    @RequestMapping(value = "/data/{uId}",method = RequestMethod.GET)
    public User getData(@PathVariable int uId){
        return  userService.selectUser(uId);
    }

    @ResponseBody
    @RequestMapping(value = "/{uName}",method= RequestMethod.GET)
    public ListDto getUsers(@PathVariable("uName") String uName){
        return  userService.selectByName(uName);
    }

    @ResponseBody
    @RequestMapping(value="/findMail/{uName}",method = RequestMethod.GET)
    public ListDto getUmail(@PathVariable("uName") String uName){
        return userService.getUmailByName(uName);
    }

}
