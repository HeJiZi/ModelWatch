package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.SelectService;
import service.UpdateService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    SelectService selectService;

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public List searchLog(@RequestParam(value = "beginTime",required = false) Timestamp beginTime,
                        @RequestParam(value = "endTime", required = false) Timestamp endTime,
                        @RequestParam(value = "uUsername", required = false) String uUsername,
                        @RequestParam(value = "mName",required = false) String mName){
        return selectService.filterLog(beginTime, endTime, uUsername, mName);
    }

    @Autowired
    UpdateService updateService;

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.POST)
    public boolean updateLogContext(HttpServletRequest request,@RequestParam(value = "lId") Long lId,
                                    @RequestParam(value = "lContext") String lContext){
        try {
            request.setCharacterEncoding("utf-8");

        }catch (Exception e){
            e.printStackTrace();
        }
        return updateService.updateLogContext(lId,lContext);
    }
}
