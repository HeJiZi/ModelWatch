package controller;

import bean.User;
import dto.ListObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import dao.InvitationDao;
import service.InsertService;
import service.SelectService;
import service.DeleteService;
import service.UpdateService;
import util.SendMailUtil;


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

    @PostMapping(value = "/{pid}/{uUsername}")
    public int addInvitationByUNameAndPid(@PathVariable("pid") long pid,
                                          @PathVariable("uUsername") String uUsername) {
        return insertService.addInvByUnameAndPid(pid, uUsername);
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
    @PostMapping(value="/sendMail")
    public int sendLink(@RequestParam("name") String name,@RequestParam("uMail") String uMail,@RequestParam("pId") Long pId){
        int n=SendMailUtil.SendMail("您收到了一封来自"+name+"的邀请邮件",uMail,"<h1>这是标题</h1><a href=\"http://127.0.0.1:8070/inv#/"+name+"/"+pId+"\">hhhh</a><h1>");
        return n;
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
}
