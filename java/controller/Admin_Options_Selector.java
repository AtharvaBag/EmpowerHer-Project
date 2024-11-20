package controller;

import dto.Course_DTO;
import dto.Trainer_DTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin_Get_Details;

public class Admin_Options_Selector extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        boolean isAdminVerified = false;
        
        if(session.getAttribute("isAdminVerified") != null)
        {
            isAdminVerified = (boolean)session.getAttribute("isAdminVerified");
            if(!isAdminVerified)
            {
                response.sendRedirect("home.html");
                return;
            }
            
            boolean trainerverificationlist = false;
            boolean courseverificationlist = false;
            boolean studentdeletionrequests = false;
            
            String admin_option = request.getParameter("adminoption");
            System.out.println(admin_option);
            
            Admin_Get_Details details = new Admin_Get_Details();
            
            switch(admin_option)
            {
                case("Teacher Verification List"):
                trainerverificationlist = true;
                courseverificationlist = false;
                studentdeletionrequests = false;
                session.setAttribute("courseverificationlist", courseverificationlist);
                session.setAttribute("trainerverificationlist", trainerverificationlist);
                session.setAttribute("studentdeletionrequests", studentdeletionrequests);
                ArrayList <Trainer_DTO> verifiedTrainers = new ArrayList<>(details.verifiedTrainers());
                session.setAttribute("verifiedTrainers", verifiedTrainers);
                ArrayList <Trainer_DTO> unverifiedTrainers = new ArrayList<>(details.unverifiedTrainers());
                session.setAttribute("unverifiedTrainers", unverifiedTrainers);
                break;
                    
                case("Course Verification List"):
                courseverificationlist = true;
                trainerverificationlist = false;
                studentdeletionrequests = false;
                session.setAttribute("courseverificationlist", courseverificationlist);
                session.setAttribute("trainerverificationlist", trainerverificationlist);
                session.setAttribute("studentdeletionrequests", studentdeletionrequests);
                Map <Course_DTO, Trainer_DTO> verifiedCourseTrainer = new HashMap<>(details.verifiedCourse());
                session.setAttribute("verifiedCourseTrainer", verifiedCourseTrainer);
                Map <Course_DTO, Trainer_DTO> unverifiedCourseTrainer = new HashMap<>(details.unverifiedCourse());
                session.setAttribute("unverifiedCourseTrainer", unverifiedCourseTrainer);
               break;
               
                case("Student Deletion Requests"):
                courseverificationlist = false;
                trainerverificationlist = false;
                studentdeletionrequests = true;
                session.setAttribute("courseverificationlist", courseverificationlist);
                session.setAttribute("trainerverificationlist", trainerverificationlist);
                session.setAttribute("studentdeletionrequests", studentdeletionrequests);
                ArrayList <Course_DTO> deletetionRequestCourses = new ArrayList<>(details.coursesWithDeletionRequest());
                session.setAttribute("deletetionRequestCourses", deletetionRequestCourses);
                break;
               
                default:
                   System.out.println("Invalid Input");
            }
            
            response.sendRedirect("Admin_Home.jsp");
            
        }
       else
        {
             response.sendRedirect("home.html");
             return;
        }
    }
}
