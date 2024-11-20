package controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TrainerCourse_Selector extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String trainerskill = request.getParameter("skill");
        System.out.println(trainerskill);
        HttpSession session = request.getSession();
        session.setAttribute("trainerskill", trainerskill);
        if(!(trainerskill == null))
        {
            response.sendRedirect("Trainer_Course_Registration.jsp");
            return;
        }
        response.sendRedirect("Trainer_Home_Page.jsp");
        return;
    }
}
