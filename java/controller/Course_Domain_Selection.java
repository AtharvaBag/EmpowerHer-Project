package controller;

import dto.Course_DTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CheckInput;

public class Course_Domain_Selection extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        System.out.println(request.getParameter("searchquery"));
        CheckInput courseDetails = new CheckInput(request.getParameter("searchquery").trim().toLowerCase());
        ArrayList <String> verifiedCoursesList = new ArrayList<> (courseDetails.getCourses());
        ArrayList <Course_DTO> courseBasicDetails = new ArrayList<> (courseDetails.getCourseDetails());
        HttpSession session = request.getSession();
        session.setAttribute("courseBasicDetails", courseBasicDetails);
        session.setAttribute("searchquery", request.getParameter("searchquery").trim().toLowerCase());
        response.sendRedirect("CoursesPage.jsp");
    }
}
