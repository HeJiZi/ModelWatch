package web;

import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/createproject")
public class createProject extends HttpServlet {
//    //    LoginService loginService;
//    LoginService loginService=new LoginServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //获得提交的信息
//        String username=request.getParameter("username");
//        String password=request.getParameter("password");
//
//        //获得注册结果
//        User user=loginService.userLogin(username,password);
//        if(user!=null){
//            request.getSession().setAttribute("user",user);
//            PrintWriter pw=response.getWriter();
//
//            //这里展示json调用,ajax可用以下api
//            pw.write(JSONObject.fromObject(user).toString());
//        }
//        else {
//            //这里是对SignUpServlet中注释的演示
//            response.sendRedirect("signup");
       }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}