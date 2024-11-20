package controller;

import dto.Course_DTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Filtered_Courses;

public class Apply_Search_Filters extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        
        HttpSession session = request.getSession();
        
        Course_DTO filteredCourseChoice = new Course_DTO();
        
        System.out.println("From Apply_Search_Filter: "+request.getParameter("coursemodefilter"));
        
        if(request.getParameter("coursemodefilter") != null)
        {
            filteredCourseChoice.setCoursemode(request.getParameter("coursemodefilter"));
        }
        else
        {
             filteredCourseChoice.setCoursemode("");
        }
        if(request.getParameter("coursefeesfilter") != null)
        {
            filteredCourseChoice.setCoursefee(request.getParameter("coursefeesfilter"));
        }
        else
        {
             filteredCourseChoice.setCoursefee("");
        }
        if(request.getParameter("coursedurationfilter") != null)
        {
            filteredCourseChoice.setCourseduration(request.getParameter("coursedurationfilter"));
        }
        else
        {
             filteredCourseChoice.setCourseduration("");
        }
        if(request.getParameter("courselevelfilter") != null)
        {
            filteredCourseChoice.setCourseviewers(request.getParameter("courselevelfilter"));
        }
        else
        {
             filteredCourseChoice.setCourseviewers("");
        }
        if(request.getParameter("coursecertificatefilter") != null)
        {
            filteredCourseChoice.setCoursecertification(request.getParameter("coursecertificatefilter"));
        }
        else
        {
             filteredCourseChoice.setCoursecertification("");
        }
        
        
        Filtered_Courses filter = new Filtered_Courses(filteredCourseChoice,  (String)session.getAttribute("searchquery"));
        
        if(filter.noData())
        {
            System.out.println("No Filters Applied");
            response.sendRedirect("CoursesPage.jsp");
            return;
        }
        
        boolean filtersApplied = true;
        session.setAttribute("filtersApplied", filtersApplied);
        ArrayList <Course_DTO> courseBasicDetails = filter.getCourses();
        session.setAttribute("courseBasicDetails", courseBasicDetails);
        response.sendRedirect("CoursesPage.jsp");
        return;
    }
}
