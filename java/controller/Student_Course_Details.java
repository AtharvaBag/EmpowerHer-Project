package controller;

import dto.Course_DTO;
import dto.Student_DTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Student_Course_Details_Fetcher;

public class Student_Course_Details extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
         HttpSession session = request.getSession(true);
        String studentcourseid = new String();
        boolean isStudentEnrolled = false;
        boolean isStudentWishlisted = false;
        if(request.getParameter("enrolledcourseid") == null)
        {
            studentcourseid = (String)request.getParameter("wishlistedcourseid");
            isStudentWishlisted = true;
        }
        else
        {
            studentcourseid = (String)request.getParameter("enrolledcourseid");
            isStudentEnrolled = true;
        }
        
        session.setAttribute("isStudentEnrolled", isStudentEnrolled);
        session.setAttribute("isStudentWishlisted", isStudentWishlisted);
        Student_Course_Details_Fetcher getDetails = new Student_Course_Details_Fetcher(studentcourseid.trim());
        Course_DTO courseDetails = getDetails.courseDetails();
        courseDetails.setCourseid(studentcourseid);
        session.setAttribute("courseDetails", courseDetails);
        session.setAttribute("studentcourseid", studentcourseid);
        response.sendRedirect("Student_Course.jsp");
        return;
    }
}
