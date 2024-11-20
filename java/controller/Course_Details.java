package controller;

import dto.Course_DTO;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Course_Details_Fetcher;

public class Course_Details extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Course_DTO courseDetails = new Course_DTO();
        courseDetails.setCourseid(request.getParameter("inputcourseid"));
        HttpSession session = request.getSession();
        session.setAttribute("courseDetails", courseDetails);
        Course_Details_Fetcher details = new Course_Details_Fetcher(courseDetails);
        courseDetails = details.getDetails();
        response.sendRedirect("Course_Enrollment_Page.jsp");
        //response.sendRedirect("tempjsp.jsp");
        return;
    }            
}
