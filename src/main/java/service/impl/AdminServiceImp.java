package service.impl;

import bean.Administrator;
import bean.Comment;
import bean.Model;
import bean.User;
import dao.AdministratorDao;
import dao.CommentsDao;
import dao.ModelDao;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AdminService;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    UserDao userDao;

    @Autowired
    AdministratorDao administratorDao;

    @Autowired
    CommentsDao commentsDao;

    @Autowired
    ModelDao modelDao;

    public void CheckUser(User user) {
        userDao.updateState(user);
    }

    public Administrator Login(String username, String password) {
        Administrator admin = new Administrator();
        admin.setaUsername(username);
        admin.setaPassword(password);
        return administratorDao.SelectAdminByUsernameAndPassword(admin);
    }

    public boolean UpdatePassword(Administrator administrator) {
        administratorDao.UpdatePassword(administrator);
        return true;
    }

    public boolean Register(String username, String password) {
        Administrator admin = new Administrator();
        admin.setaUsername(username);
        admin.setaPassword(password);
        return  administratorDao.AddAdministrator(admin) == 1 ? true : false;
    }

    public void CheckComment(Comment comment) {
        if(comment.getComState() == 0) commentsDao.DeleteComment(comment.getComId());
        else commentsDao.UpdateCommentState(comment);
    }

    public void CheckModel(Model model) {
        if(model.getmState() == 0) modelDao.deleteModel(model.getmId());
        else modelDao.updateModelState(model);
    }

    public List<Comment> GetComments() {
        return commentsDao.SelectComments(0);
    }

    public List<User> GetUsers() {
        return userDao.selectUsers();
    }

    public List<Model> GetModels() {
        return modelDao.GetUnCheckModels();
    }
}
