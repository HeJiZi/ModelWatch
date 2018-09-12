package controller;

import dao.SubscribeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscribe")
public class SubscribeController {

    @Autowired
    SubscribeDao subscribeDao;

    @PostMapping(value = "/{pid}/{uid}")
    public int addSubscribe(@PathVariable("pid") int pid,
                            @PathVariable("uid") int uid){
        return subscribeDao.addSubscribe(pid, uid);
    }

    @DeleteMapping(value = "/{pid}/{uid}")
    public int deleteSubscribe(@PathVariable("pid") int pid,
                               @PathVariable("uid") int uid){
        return subscribeDao.deleteSubscribe(pid, uid);
    }

    @GetMapping(value = "/{pid}/{uid}")
    public int selectSubscribe(@PathVariable("pid") int pid,
                               @PathVariable("uid") int uid){
        return subscribeDao.selectSubscribe(pid, uid);
    }
}
