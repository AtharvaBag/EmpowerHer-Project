package controller;

import dto.Student_DTO;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Student_Data_Extract;

public class Student_Details extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        Student_DTO studentDetails = (Student_DTO)session.getAttribute("studentDetails");
        Student_Data_Extract getData = new Student_Data_Extract(studentDetails);
        getData.studentData();
        response.sendRedirect("Student_Profile.jsp");
        return;
    }
}
