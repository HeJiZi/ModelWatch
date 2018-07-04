package controller;

import bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import dao.invitationDao;

import java.util.List;

@RestController
@RequestMapping("/invitation")
public class InvitationController {

    @Autowired
    private invitationDao invitationDao;

    @PostMapping(value = "/addInvitation")
    public int addInvitation(@RequestParam("pid") int pid,
                             @RequestParam("uid") int uid){
        return invitationDao.addInvitation(pid,uid);
    }

    @PostMapping(value = "/{pid}/{uUsername}")
    public int addInvitationByUNameAndPid(@PathVariable("pid") int pid,
                                          @PathVariable("uUsername") String uUsername){
        return invitationDao.addInvitationByUNameAndPid(pid, uUsername);
    }

    @DeleteMapping(value = "/{pid}/{uid}")
    public int deleteInvitation(@PathVariable("pid") int pid,
                                @PathVariable("uid") int uid){
        return invitationDao.deleteInvitation(pid,uid);
    }

    @GetMapping(value = "/{pid}")
    public List<User> selectInvitation(@PathVariable int pid){
        return invitationDao.selectInvitationByPid(pid);
    }
}
