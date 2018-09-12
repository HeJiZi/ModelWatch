package controller;

import bean.Administrator;
import bean.Comment;
import bean.Model;
import bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.AdminService;
import service.SelectService;

import java.util.List;

@RestController
@RequestMapping("/managers")
public class ManagerController {

    @Autowired
    AdminService adminService;

    @Autowired
    SelectService selectService;

    @GetMapping("/users")
    public List<User> GetUsers(){
        return adminService.GetUsers();
    }

    @GetMapping("/users/{content}")
    public List<User> FilterUsers(@PathVariable String content){
        return selectService.selectUser(content);
    }

    @GetMapping("/models")
    public List<Model> GetModels(){
        return adminService.GetModels();
    }

    @GetMapping("/comments")
    public List<Comment> GetComments(){
        return adminService.GetComments();
    }

    @PostMapping("/user")
    public boolean CheckUser(@RequestBody User user){
        adminService.CheckUser(user);
        return true;
    }

    @PostMapping("/model")
    public boolean CheckModel(@RequestBody Model model){
        adminService.CheckModel(model);
        return true;
    }

    @PostMapping("/comment")
    public boolean CheckComment(@RequestBody Comment comment){
        adminService.CheckComment(comment);
        return true;
    }

    @PostMapping("/password")
    public boolean UpdatePass(@RequestBody Administrator administrator){
        return adminService.UpdatePassword(administrator);
    }

    @PostMapping("/admin")
    public boolean AddAdmin(@RequestBody Administrator administrator){
        return  adminService.Register(administrator.getaUsername(),administrator.getaPassword());
    }

}
