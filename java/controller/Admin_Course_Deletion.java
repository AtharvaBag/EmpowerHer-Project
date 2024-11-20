package controller;

import dto.Course_DTO;
import dto.Trainer_DTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin_Delete_Course;
import model.Admin_Get_Details;

public class Admin_Course_Deletion extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        Admin_Delete_Course delete = new Admin_Delete_Course((String)session.getAttribute("courseid"));
        if(delete.deleteCourse())
        {
            System.out.println("Course Deleted Successfully!");
        }
        else
        {
            System.out.println("Unable to delete course");
        }
        Admin_Get_Details getDetails = new Admin_Get_Details();
        session.removeAttribute("verifiedCourseTrainer");
        session.removeAttribute("unverifiedCourseTrainer");
        Map <Course_DTO, Trainer_DTO> verifiedCourseTrainer = new HashMap<>(getDetails.verifiedCourse());
        session.setAttribute("verifiedCourseTrainer", verifiedCourseTrainer);
        Map <Course_DTO, Trainer_DTO> unverifiedCourseTrainer = new HashMap<>(getDetails.unverifiedCourse());
        session.setAttribute("unverifiedCourseTrainer", unverifiedCourseTrainer);
        response.sendRedirect("Admin_Home.jsp");
        return;
    }
}
