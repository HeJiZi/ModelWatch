package controller;

import bean.User;
import dto.InvitationDto;
import dto.ListObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import dao.InvitationDao;
import service.*;


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

    @Autowired
    private UpdateService updateService;


    @PostMapping(value = "/addInvitation")
    public int addInvitation(@RequestParam("pid") int pid,
                             @RequestParam("uid") int uid) {
        return InvitationDao.addInvitation(pid, uid);
    }

    @DeleteMapping(value = "/{pid}/{uid}")
    public int deleteInvitation(@PathVariable("pid") long pid,
                                @PathVariable("uid") int uid) {
        return deleteService.deleteInvitation(pid, uid);
    }

    @GetMapping(value = "/{pid}")
    public List<User> selectInvitation(@PathVariable int pid) {
        return InvitationDao.selectInvitationByPid(pid);
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ListObject getCollaborators(@RequestParam("pId") long pId, @RequestParam("currentPage") int currentPage) {
        return selectService.selectCollaborators(pId, currentPage);
    }

    @ResponseBody
    @RequestMapping(value = "/updateState/{pId}/{uName}",method = RequestMethod.GET)
    public int updateInvState(@PathVariable("pId") Long pId,@PathVariable("uName") String uName){
        return updateService.changeInvState(pId,uName);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteWaitState/{pId}/{uName}",method = RequestMethod.GET)
    public int deleteWaitState(@PathVariable("pId") Long pId,@PathVariable("uName") String uName){
        return deleteService.deleteWait(pId,uName);
    }

    @ResponseBody
    @RequestMapping(value = "/findMessage/{pId}/{myName}",method = RequestMethod.GET)
    public InvitationDto findInvMessage(@PathVariable("pId") Long pId,@PathVariable("myName") String myName){
        return selectService.findInvMessage(pId,myName);
    }

    @ResponseBody
    @RequestMapping(value = "/addCollaborators/{pId}/{myName}",method = RequestMethod.GET)
    public int addCollaborator(@PathVariable("pId") Long pId,@PathVariable("myName") String myName){
        return insertService.addCollaborator(pId,myName);
    }
}
