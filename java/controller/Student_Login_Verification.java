package controller;

import dto.Course_DTO;
import dto.Student_DTO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.Student_Login_Check;

public class Student_Login_Verification extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Student_DTO studentLoginDetails = new Student_DTO();
        studentLoginDetails.setStudentcontact(request.getParameter("studentcontact"));
        studentLoginDetails.setStudentpassword(request.getParameter("studentpassword"));
        
        Student_Login_Check check = new Student_Login_Check(studentLoginDetails);
        
        if(check.studentAuthentication())
        {
            HttpSession session = request.getSession();
            session.setAttribute("studentDetails", studentLoginDetails);
            boolean isStudentLoggedIn = true;
            session.setAttribute("isStudentLoggedIn", isStudentLoggedIn);
            
            ArrayList <Course_DTO> studentEnrolledCourseDetails = new ArrayList<>(check.enrolledCourses()) ;
            session.setAttribute("studentEnrolledCourseDetails", studentEnrolledCourseDetails);
            
            ArrayList <Course_DTO> studentWishlistCourseDetails = new ArrayList<>(check.wishlistCourses());
            session.setAttribute("studentWishlistCourseDetails", studentWishlistCourseDetails);
            
            response.sendRedirect("Student_Home.jsp");
            //response.sendRedirect("tempjsp.jsp");
            return;
        }
        response.sendRedirect("home.html");
        return;
    }
}