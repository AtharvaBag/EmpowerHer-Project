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
import model.Admin_Get_Details;
import model.Admin_Student_Update_Enrollment;

public class Admin_Cancel_Student_Deletion extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        Course_DTO courseDetails = (Course_DTO)session.getAttribute("courseDetails");
        Student_DTO studentDetails = (Student_DTO)session.getAttribute("studentDetails");
        Admin_Student_Update_Enrollment update = new Admin_Student_Update_Enrollment(studentDetails, courseDetails);
        if(update.reenrollStudent())
        {
            System.out.println("Reenrolled Student");
        }
        else
        {
            System.out.println("Unable to reenroll");
        }
        Admin_Get_Details details = new Admin_Get_Details(courseDetails);
        ArrayList <Course_DTO> deletetionRequestCourses = new ArrayList<>(details.coursesWithDeletionRequest());
        session.removeAttribute("deletetionRequestCourses");
        session.setAttribute("deletetionRequestCourses", deletetionRequestCourses);
        ArrayList <Student_DTO> deletionStudentsList = new ArrayList<>(details.getDeletionStudents());
        session.removeAttribute("deletionStudentsList");
        session.setAttribute("deletionStudentsList", deletionStudentsList);
        response.sendRedirect("Admin_Students_Deletion_List.jsp");
    }
}
