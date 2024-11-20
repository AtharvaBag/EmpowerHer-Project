package controller;

import dto.Course_DTO;
import dto.Student_DTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin_Get_Details;

public class Admin_Delete_Students_List extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Course_DTO courseDetails = new Course_DTO();
        courseDetails.setCourseid(request.getParameter("courseid"));
        courseDetails.setCoursetitle(request.getParameter("coursename"));
        HttpSession session = request.getSession();
        session.setAttribute("courseDetails", courseDetails);
        Admin_Get_Details details = new Admin_Get_Details(courseDetails);
        ArrayList <Student_DTO> deletionStudentsList = new ArrayList<>(details.getDeletionStudents());
        session.setAttribute("deletionStudentsList", deletionStudentsList);
        response.sendRedirect("Admin_Students_Deletion_List.jsp");
        return;
    }
}
