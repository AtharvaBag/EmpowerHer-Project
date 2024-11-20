package controller;

import dto.Student_DTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Student_Delete_Profile;

public class Student_Profile_Deletion extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        Student_DTO studentDetails = (Student_DTO)session.getAttribute("studentDetails");
        Student_Delete_Profile delete = new Student_Delete_Profile(studentDetails);
        delete.deleteProfile();
        boolean isStudentLoggedIn = false;
        session.removeAttribute("isStudentLoggedIn");
        session.setAttribute("isStudentLoggedIn", isStudentLoggedIn);
        response.sendRedirect("home.html");
        return;
    }
}
