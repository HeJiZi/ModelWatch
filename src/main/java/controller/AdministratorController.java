package controller;

import bean.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.AdminService;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/admin")
@RestController
public class AdministratorController {

    @Autowired
    AdminService adminService;

    @PostMapping("")
    public boolean AdminLogin(@RequestBody Administrator administrator, HttpServletRequest request){
        Administrator admin = adminService.Login(administrator.getaUsername(),administrator.getaPassword());
        if(admin !=null) {
            request.getSession().setAttribute("administrator",admin);
            return true;
        }
        else return false;
    }

    @GetMapping("/session")
    public Administrator getAdmin(HttpServletRequest request){
        return (Administrator) request.getSession().getAttribute("administrator");
    }

}
