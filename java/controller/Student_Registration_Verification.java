package controller;

import dto.Course_DTO;
import dto.Student_DTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Student_Registration_Checker;

public class Student_Registration_Verification extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Student_DTO studentDetails = new Student_DTO();
        
        studentDetails.setStudentname(request.getParameter("studentname"));
        studentDetails.setStudentcontact(request.getParameter("studentcontact"));
        studentDetails.setStudentmailid(request.getParameter("studentmailid"));
        studentDetails.setStudentpassword(request.getParameter("studentpassword"));
        studentDetails.setStudentconfirmpassword(request.getParameter("studentconfirmpassword"));
        
        Student_Registration_Checker check = new Student_Registration_Checker(studentDetails);
        if(check.areDetailsValid() && check.isContactCorrect() && check.isMailIdCorrect() && check.isPasswordCorrect())
        {
            check.registerStudent();
            boolean isStudentLoggedIn = true;
            HttpSession session = request.getSession();
            session.setAttribute("isStudentLoggedIn", isStudentLoggedIn);
            session.setAttribute("studentDetails", studentDetails);
            ArrayList <Course_DTO> studentEnrolledCourseDetails = new ArrayList<>();
            ArrayList <Course_DTO> studentWishlistCourseDetails = new ArrayList<>();
            studentEnrolledCourseDetails.clear();
            studentWishlistCourseDetails.clear();
            session.setAttribute("studentEnrolledCourseDetails", studentEnrolledCourseDetails);
            session.setAttribute("studentWishlistCourseDetails", studentWishlistCourseDetails);
            response.sendRedirect("Student_Home.jsp");
            return;
        }
        response.sendRedirect("home.html");
        return;
    }
}
