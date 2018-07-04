package controller;

import bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
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


}
