package web;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/project")
public class ProjectServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
    }
}
