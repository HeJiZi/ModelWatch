package controller;

import dto.LogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.DeleteService;
import service.SelectService;
import service.UpdateService;
import util.DtoListUtil;

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
    public List searchLog(@RequestParam(value = "beginTime",required = false) String beginTime,
                        @RequestParam(value = "endTime", required = false) String endTime,
                        @RequestParam(value = "uUsername", required = false) String uUsername,
                        @RequestParam(value = "mName",required = false) String mName,
                          @RequestParam(value = "pId") Long pId,
                          @RequestParam(value = "currentPage") int currentPage,
                          @RequestParam(value = "limit") int limit){
        return DtoListUtil.transelateList(selectService.filterLog(beginTime, endTime, uUsername, mName,pId,currentPage,limit),LogDto.class);
    }

    @Autowired
    UpdateService updateService;

    @ResponseBody
    @RequestMapping(value = "/{lId}",method = RequestMethod.PUT)
    public boolean updateLogContext(HttpServletRequest request,@PathVariable Long lId,
                                    @RequestParam(value = "lContext") String lContext){
        try {
            request.setCharacterEncoding("utf-8");

        }catch (Exception e){
            e.printStackTrace();
        }
        return updateService.updateLogContext(lId,lContext);
    }

    @Autowired
    DeleteService deleteService;

    @ResponseBody
    @RequestMapping(value = "/{lId}",method = RequestMethod.DELETE)
    public boolean deleteLog(@PathVariable Long lId){
        return deleteService.DeleteLog(lId);
    }
}
