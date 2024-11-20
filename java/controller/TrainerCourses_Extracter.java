package controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TrainerCourses_Fetcher;

public class TrainerCourses_Extracter extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        String trainerid = (String)session.getAttribute("trainerid");
        if(trainerid==null)
        {
            response.sendRedirect("Trainer_Home_Page.jsp");
            return;
        }
        TrainerCourses_Fetcher courses = new TrainerCourses_Fetcher(trainerid);
        session.setAttribute("courses", courses);
        response.sendRedirect("Trainer_Courses_List.jsp");
        return;
    }
}
