package controller;

import bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControler {

    @Autowired
    UserService userService;


    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public boolean Login(@RequestBody @Validated  User user1, HttpServletRequest request){
        User user=userService.login(user1.getuUsername(),user1.getuPassword());
        request.getSession().setAttribute("user",user);
        return user==null?false:true;
    }

    @ResponseBody
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public boolean Register(@RequestBody @Validated  User user){
        return  userService.register(user.getuUsername(),user.getuPassword());
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
    public List getUsers(@PathVariable("uName") String uName){
        return  userService.selectByName(uName);
    }

}
