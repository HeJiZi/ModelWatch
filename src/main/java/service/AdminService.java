package service;

import bean.Administrator;
import bean.Comment;
import bean.Model;
import bean.User;

import java.util.List;

public interface AdminService {
    void CheckUser(User user);

    Administrator Login(String username, String password);

    boolean UpdatePassword(Administrator administrator);

    boolean Register(String username,String password);

    void CheckComment(Comment comment);

    void CheckModel(Model model);

    List<Comment> GetComments();

    List<User> GetUsers();

    List<Model> GetModels();

}
