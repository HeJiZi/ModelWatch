package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.MarkService;

@Controller
@RequestMapping("/mark")
public class MarkController {

    @Autowired
    MarkService markService;

    @ResponseBody
    @PostMapping(value = "/addMark")
    public int addMark(@RequestParam("mid") int mid,
                           @RequestParam("uid") int uid){
        return markService.addMark(mid,uid);
    }

    @ResponseBody
    @PostMapping(value = "/deleteMark")
    public int deleteMark(@RequestParam("mid") int mid,
                       @RequestParam("uid") int uid){
        return markService.deleteMark(mid,uid);
    }

    @ResponseBody
    @PostMapping(value = "/selectMark")
    public int selectMark(@RequestParam("mid") int mid,
                          @RequestParam("uid") int uid){
        return markService.selectMark(mid,uid);
    }
}
