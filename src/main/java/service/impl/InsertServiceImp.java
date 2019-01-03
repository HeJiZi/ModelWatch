package service.impl;

import bean.User;
import dao.InvitationDao;
import dao.SubscribeDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.InsertService;
import dao.UserDao;
import util.SendMailUtil;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class InsertServiceImp implements InsertService {

    @Autowired
    private InvitationDao invitationDao;

    @Autowired
    private SubscribeDao subscribeDao;

    @Autowired
    HttpSession session;

    public int addCollaborator(Long pId,String myName) {
        int s=1;
        int n=invitationDao.addInvByNamePid(pId,myName);
        if(n==1){
            List<User> users=invitationDao.getMailByName(myName);
            List<User> managers=invitationDao.getUserName(pId);
            String uName=managers.get(0).getuUsername();
            String uMail=users.get(0).getuEmail();
            if(uMail!=null) {
                s = SendMailUtil.SendMail("您收到了一封来自" + uName + "的邀请邮件", uMail,
                        "<div style=\"background-color:#f2f2f2;margin:-14px -19px;\">\n" +
                                "<div style=\"padding:20px 300px 100px 100px;\">\n" +
                                "        <div style=\"font-family:'Times New Roman', Times, serif';font-size:20px;\">亲爱的小伙伴：</div>\n" +
                                "        <div style=\"text-indent:2em;fontsize:15px;\">\n" +
                                "            " + myName + ",你好,我是邀请者" + uName + ",鉴于你的项目管理记录良好，在此诚挚的邀请你共同管理我的项目。\n" +
                                "            <br>详情请请点击下面的邀请链接:\n" +
                                "            <br>&nbsp;&nbsp;&nbsp;<a href=\"http://127.0.0.1:8070/inv#/" + pId + "/" + myName + "\">点这里</a>\n" +
                                "            <br>期待与你的合作！\n" +
                                "        </div>\n" +
                                "        <div style=\"font-size:18px;\">   \n" +
                                "            诚挚的邀请<br>\n" +
                                "            " + uName + "\n" +
                                "        </div>\n" +
                                "    </div>\n" +
                                "</div>");
            }
        }
        return s;
    }

    public boolean addSubscribe(long pId) {
        User user = (User) session.getAttribute("user");
        int uId =user.getuId();
        return subscribeDao.addSubscribe(pId,uId)!=0;
    }
}
