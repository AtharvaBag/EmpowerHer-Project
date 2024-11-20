package controller;

import dto.Course_DTO;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TrainerCourse_Registration;

public class TrainerCourse_Register extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Course_DTO courseRegister = new Course_DTO();
        String isCourseVerified="0";
        courseRegister.setCoursetitle(request.getParameter("coursetitle"));
        courseRegister.setCourseclassname( request.getParameter("courseclassname"));
        courseRegister.setCourseclasscontact(request.getParameter("courseclasscontact"));
        courseRegister.setCourseviewers(request.getParameter("courseviewers"));
        courseRegister.setCourseduration(request.getParameter("courseduration"));
        courseRegister.setCoursedays(request.getParameter("coursedays"));
        courseRegister.setCoursemode(request.getParameter("coursemode"));
        courseRegister.setCourseclassaddress(request.getParameter("courseclassaddress"));
        courseRegister.setCourseclasscity(request.getParameter("courseclasscity"));
        courseRegister.setCourseclassstate(request.getParameter("courseclassstate"));
        courseRegister.setCoursedescription(request.getParameter("coursedescription"));
        courseRegister.setCoursecertification(request.getParameter("coursecertificate"));
        courseRegister.setCoursefee(request.getParameter("coursefee"));
       
        
        HttpSession session = request.getSession(true);
        String trainerskill = (String)session.getAttribute("trainerskill");
        if(trainerskill.equalsIgnoreCase("+ Add Your Own Skill"))
        {
            trainerskill = request.getParameter("newskill");
        }
        courseRegister.setTrainerskill(trainerskill);
        session.setAttribute("courseRegister", courseRegister);
        session.setAttribute("isCourseVerified", isCourseVerified);
        
        TrainerCourse_Registration check = new TrainerCourse_Registration(courseRegister);
        if(check.isDetailsValid())
        {
            check.registerCourse((String)session.getAttribute("trainerid"));
            System.out.println("Course Registered, course id: "+courseRegister.getCourseid());
            
            response.sendRedirect("Trainer_Home_Page.jsp");
            return;
        }
        response.sendRedirect("Trainer_Course_Registration.jsp");
        return;
    }
}
