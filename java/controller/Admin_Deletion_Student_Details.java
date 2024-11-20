package controller;

import dto.Student_DTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Student_Data_Extract;

public class Admin_Deletion_Student_Details extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Student_DTO studentDetails = new Student_DTO();
        studentDetails.setStudentid(request.getParameter("studentid"));
        studentDetails.setStudentname(request.getParameter("studentname"));
        Student_Data_Extract getData = new Student_Data_Extract(studentDetails.getStudentid(), studentDetails);
        studentDetails = getData.getStudentData();
        HttpSession session = request.getSession();
        session.setAttribute("studentDetails", studentDetails);
        response.sendRedirect("Admin_Delete_Student.jsp");
        return;
    }
            
}
