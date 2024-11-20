package controller;

import dto.Course_DTO;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TrainerCourse_Details_Extracter;

public class Trainer_Get_Course_Details extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession(true);
        String trainerid = (String)session.getAttribute("trainerid");
        if(trainerid == null)
        {
            response.sendRedirect("home.html");
            return;
        }
        String trainercourseid = request.getParameter("trainercourseid");
        String trainercoursename = request.getParameter("trainercoursename");
        String isCourseVerified = "0";
        session.setAttribute("isCourseVerified",isCourseVerified);
        session.setAttribute("trainercourseid", trainercourseid);
        session.setAttribute("trainercoursename",trainercoursename);
        System.out.println(trainercourseid+", "+trainercoursename);
        
        TrainerCourse_Details_Extracter getCourse = new TrainerCourse_Details_Extracter(trainerid, trainercourseid);
        Course_DTO registeredCourseDetails = getCourse.getCourseDetails();
        session.setAttribute("registeredCourseDetails", registeredCourseDetails);
        if(registeredCourseDetails != null)
        {
            response.sendRedirect("Trainer_Course_View.jsp");
            return;
        }
        response.sendRedirect("Trainier_Home_Page.jsp");
        return;
    }
}
