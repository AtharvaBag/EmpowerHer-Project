package controller;

import dto.Course_DTO;
import dto.Student_DTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Enroll_Student;

public class Wishlist_Course extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        Course_DTO courseDetails = (Course_DTO)session.getAttribute("courseDetails");
        Student_DTO studentDetails = (Student_DTO)session.getAttribute("studentDetails");
        
        boolean flag = false;
            if(session.getAttribute("studentWishlistCourseDetails") != null)
            {
                ArrayList <Course_DTO> studentWishlistCourseDetails = new ArrayList<>((ArrayList) session.getAttribute("studentWishlistCourseDetails"));
                for(int i=0; i<studentWishlistCourseDetails.size(); i++)
                {
                     if(studentWishlistCourseDetails.get(i).getCourseid().equals(courseDetails.getCourseid()))
                    {
                        flag = true;
                        break;
                    }
                }
            }
            if(flag)
            {
                response.sendRedirect("Course_Enrollment_Page.jsp");
                boolean wishlisted = true;
                session.setAttribute("wishlisted", wishlisted);
                return;
            }
        
        
        Enroll_Student wishlist = new Enroll_Student(courseDetails, studentDetails);
        wishlist.wishlistCourse();
        
        ArrayList <Course_DTO> studentWishlistCourseDetails = new ArrayList<>((ArrayList)session.getAttribute("studentWishlistCourseDetails"));
        studentWishlistCourseDetails.add(courseDetails);
        session.setAttribute("studentWishlistCourseDetails", studentWishlistCourseDetails);
        
        response.sendRedirect("Student_Home.jsp");
        return;
    }
}
