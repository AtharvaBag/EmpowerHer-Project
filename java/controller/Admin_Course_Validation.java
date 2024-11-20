package controller;

import dto.Course_DTO;
import dto.Trainer_DTO;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin_Get_Details;
import model.Admin_Validate_Course;

public class Admin_Course_Validation extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        boolean courseverified = (boolean)session.getAttribute("courseverified");
        Course_DTO courseDetails = (Course_DTO)session.getAttribute("courseDetails");
        Admin_Validate_Course update = new Admin_Validate_Course(courseDetails.getCourseid());
        
        if(courseverified)
        {
           boolean result = update.invalidateCourse();
           if(result)
           {
               System.out.println("Course Invalidated!");
           }
           session.removeAttribute("courseverified");
           session.setAttribute("courseverified", false);
        }
        else
        {
            if(!(boolean)session.getAttribute("trainerverified"))
            {
                response.sendRedirect("Admin_Course_Verification.jsp");
                return;
            }
            boolean result = update.validateCourse();
            if(result)
            {
                System.out.println("Course validated!");
            }
           session.removeAttribute("courseverified");
           session.setAttribute("courseverified", true);
        }
        Admin_Get_Details getDetails = new Admin_Get_Details();
        session.removeAttribute("verifiedCourseTrainer");
        session.removeAttribute("unverifiedCourseTrainer");
        Map <Course_DTO, Trainer_DTO> verifiedCourseTrainer = new HashMap<>(getDetails.verifiedCourse());
        session.setAttribute("verifiedCourseTrainer", verifiedCourseTrainer);
        Map <Course_DTO, Trainer_DTO> unverifiedCourseTrainer = new HashMap<>(getDetails.unverifiedCourse());
        session.setAttribute("unverifiedCourseTrainer", unverifiedCourseTrainer);
        response.sendRedirect("Admin_Course_Verification.jsp");
        return;
    }
}
