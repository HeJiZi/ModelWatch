package controller;

import bean.User;
import dto.ListObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import dao.InvitationDao;
import service.InsertService;
import service.SelectService;
import service.DeleteService;


import java.util.List;

@RestController
@RequestMapping("/invitation")
public class InvitationController {

    @Autowired
    private InvitationDao InvitationDao;

    @Autowired
    private SelectService selectService;

    @Autowired
    private DeleteService deleteService;

    @Autowired
    private InsertService insertService;

    @PostMapping(value = "/addInvitation")
    public int addInvitation(@RequestParam("pid") int pid,
                             @RequestParam("uid") int uid){
        return InvitationDao.addInvitation(pid,uid);
    }

    @PostMapping(value = "/{pid}/{uUsername}")
    public int addInvitationByUNameAndPid(@PathVariable("pid") long pid,
                                          @PathVariable("uUsername") String uUsername){
       return insertService.addInvByUnameAndPid(pid,uUsername);
    }

    @DeleteMapping(value = "/{pid}/{uid}")
    public int deleteInvitation(@PathVariable("pid") long pid,
                                @PathVariable("uid") int uid){
        return deleteService.deleteInvitation(pid,uid);
    }

    @GetMapping(value = "/{pid}")
    public List<User> selectInvitation(@PathVariable int pid){
        return InvitationDao.selectInvitationByPid(pid);
    }

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ListObject getCollaborators(@RequestParam("pId") long pId,@RequestParam("currentPage") int currentPage){
        return selectService.selectCollaborators(pId,currentPage);
    }

}
