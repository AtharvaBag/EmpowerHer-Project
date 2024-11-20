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
import model.Remove_Wishlisted_Course;
import model.Student_Login_Check;

public class Remove_From_Wishlist extends HttpServlet {
    
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
        String studentcourseid = (String)session.getAttribute("studentcourseid");
        ArrayList <Course_DTO> studentWishlistCourseDetails = new ArrayList<>((ArrayList)session.getAttribute("studentWishlistCourseDetails"));
        if(courseDetails == null || studentcourseid == null || studentDetails == null)
        {
            response.sendRedirect("home.html");
            return;
        }
        
        Remove_Wishlisted_Course remove = new Remove_Wishlisted_Course(courseDetails, studentDetails);
    
        if(remove.removeCourse())
        {
            System.out.println("Course removed successfully");
            studentWishlistCourseDetails.clear();
            Student_Login_Check getWishList= new Student_Login_Check(studentDetails);
            studentWishlistCourseDetails = new ArrayList<>(getWishList.wishlistCourses());
            session.setAttribute("studentWishlistCourseDetails", studentWishlistCourseDetails);
        }
        
        response.sendRedirect("Student_Home.jsp");
        return;
    }
}
