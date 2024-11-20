package controller;

import dto.Course_DTO;
import dto.Student_DTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Trainer_Course_Students_List;

public class Trainer_Course_Enrolled_Students extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        Course_DTO registeredCourseDetails = (Course_DTO)session.getAttribute("registeredCourseDetails");
        Trainer_Course_Students_List getList = new Trainer_Course_Students_List(registeredCourseDetails.getCourseid());
        ArrayList <Student_DTO> deletedStudentsList = new ArrayList<>(getList.fetchDeletedStudents());
        ArrayList <Student_DTO> studentsList = new ArrayList<>(getList.fetchStudents());
        session.setAttribute("studentsList", studentsList);
        session.setAttribute("deletedStudentsList", deletedStudentsList);
        response.sendRedirect("Course_Student_List.jsp");
        //response.sendRedirect("tempjsp.jsp");
        return;
    }
}
