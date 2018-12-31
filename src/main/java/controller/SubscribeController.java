package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.DeleteService;
import service.InsertService;

@RestController
@RequestMapping("/subscribe")
public class SubscribeController {

    @Autowired
    InsertService insertService;

    @Autowired
    DeleteService deleteService;

    @PostMapping(value = "/{pId}")
    public boolean addSubscribe(@PathVariable("pId") long pId){
        return insertService.addSubscribe(pId);
    }

    @DeleteMapping(value = "/{pId}")
    public boolean deleteSubscribe(@PathVariable("pId") long pId){
        return deleteService.deleteSubscribe(pId);
    }

}
