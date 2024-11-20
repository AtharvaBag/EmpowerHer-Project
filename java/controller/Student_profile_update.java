package controller;

import dto.Student_DTO;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Student_Data_Update;

public class Student_profile_update extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        Student_DTO studentDetails = (Student_DTO)session.getAttribute("studentDetails");
        Student_DTO updatedDetails = new Student_DTO();
        updatedDetails.setStudentname(request.getParameter("studentname"));
        updatedDetails.setStudentcontact(request.getParameter("studentcontact"));
        updatedDetails.setStudentmailid(request.getParameter("studentmailid"));
        updatedDetails.setStudentid(studentDetails.getStudentid());
        
        Student_Data_Update update = new Student_Data_Update(updatedDetails);
        update.updateProfile();
 
        studentDetails.setStudentcontact(updatedDetails.getStudentcontact().trim());
        studentDetails.setStudentname(updatedDetails.getStudentname().trim().toUpperCase());
        studentDetails.setStudentmailid(updatedDetails.getStudentmailid().trim());
 
        response.sendRedirect("Student_Home.jsp");
        return;
    }
}
