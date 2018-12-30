package controller;

import bean.Comment;
import bean.Model;
import bean.Reply;
import bean.User;
import dao.ModelDao;
import dto.CommentDto;
import dto.ListObject;
import dto.ReplyDto;
import entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import service.SelectService;
import service.UpdateService;
import service.UserService;
import util.DtoListUtil;
import util.MyFileUtil;
import service.ManageService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ModelController {
    @Autowired
    ModelDao modelDao;

    @Autowired
    SelectService selectService;

    @Autowired
    UpdateService updateService;

    @Autowired
    ManageService manageService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/model")
    public List<Model> getAllModel(){
        return modelDao.getAllModels();
    }


    @GetMapping(value = "/model/{mId}")
    public Model getModel(@PathVariable String mId){
       return selectService.selectModelByMId(mId);
    }

    @PostMapping(value = "/upmodel")
    public boolean upDateModel(@RequestParam("model") String model,@RequestParam("lContext")String lContext, HttpServletRequest request) {
        MultipartFile[] files = null;
        if (request instanceof MultipartHttpServletRequest) {
            files = MyFileUtil.getFiles(request);
        }
        User user = (User) request.getSession().getAttribute("user");
        return updateService.updateModel(user, model, files,lContext);
    }
    @PostMapping(value = "/model")
    public long addModel(HttpServletRequest request, @RequestBody Model model){
        User user=(User)request.getSession().getAttribute("user");
        manageService.addModel(user, model, model.getProject().getpId());
        return model.getmId();
    }

    @GetMapping(value = "/model/{mId}/comments/{curPage}")
    public ListObject getComments(@PathVariable String mId, @PathVariable int curPage){
        Page page =new Page(5);
        page.setCurrentPageNum(curPage);
        List<CommentDto> comments = DtoListUtil.transelateList(selectService.getCommentsInModel(mId, page), CommentDto.class);
        ListObject lo = new ListObject(comments, page);
        return lo;
    }

    @RequestMapping(value = "comment/add")
    public boolean addComment(@RequestBody Comment comment){
        return userService.addComment(comment);
    }

    @RequestMapping(value = "reply/add")
    public boolean addReply(@RequestBody Reply reply){
        return userService.addReply(reply);
    }

    @RequestMapping(value = "reply/show/new/{comId}")
    public List<ReplyDto> showNewReply(@PathVariable int comId) {
        List<Reply> replys = selectService.selectReplysById(comId);
        if (replys.size() == 0) {
            return new ArrayList<ReplyDto>();
        } else {
            return DtoListUtil.transelateList(replys, ReplyDto.class);
        }
    }

    @PostMapping(value = "model/comidea/{comId}/{idea}")
    public boolean updateCommentIdeaNum(@PathVariable int comId, @PathVariable int idea) {
        return updateService.updateCommentIdeaNum(comId, idea);
    }

    @PostMapping(value = "model/repidea/{repId}/{idea}")
    public boolean updateReplyIdeaNum(@PathVariable int repId, @PathVariable int idea) {
        return updateService.updateReplyIdeaNum(repId, idea);
    }

}
