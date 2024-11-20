package controller;

import dto.Course_DTO;
import dto.Payment_DTO;
import dto.Student_DTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Enroll_Student;
import model.Student_Login_Check;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.Validate_Payment;

public class Enroll_To_Course extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatted = current.format(formatter);
        System.out.println(formatted);
        
        Payment_DTO paymentDetails = new Payment_DTO();
        
        HttpSession session = request.getSession();
        Course_DTO courseDetails = (Course_DTO)session.getAttribute("courseDetails");
        Student_DTO studentDetails = (Student_DTO)session.getAttribute("studentDetails");
        
        paymentDetails.setCourseid(courseDetails.getCourseid());
        paymentDetails.setStudentid(studentDetails.getStudentid());
        paymentDetails.setCoursefee(courseDetails.getCoursefee());
        paymentDetails.setPaymentmode( request.getParameter("paymentmode").trim().toUpperCase());
        paymentDetails.setCurrentdatetime(formatted.trim().toUpperCase());
        
        Student_Login_Check getLists = new Student_Login_Check(studentDetails);
        
        Enroll_Student enroll = new Enroll_Student(courseDetails, studentDetails);
        boolean isStudentWishlisted = false;
         if(session.getAttribute("isStudentWishlisted")!= null)
         {
             isStudentWishlisted = (boolean)session.getAttribute("isStudentWishlisted");
         }
        if(!isStudentWishlisted)
        {
            Validate_Payment validate = new Validate_Payment(paymentDetails, courseDetails, studentDetails);
            if(validate.completeTransaction())
            {
                enroll.enrollCourse();        
            }
            else
            {
             response.sendRedirect("Student_Home.jsp");
             return;
            }
        }
        else
        {
            Validate_Payment validate = new Validate_Payment(paymentDetails, courseDetails, studentDetails);
            if(validate.completeTransaction())
            {
                enroll.enrollWishedCourse();
            }
            else
            {
             response.sendRedirect("Student_Home.jsp");
             return;
            }
            ArrayList <Course_DTO> studentWishlistCourseDetails = new ArrayList<>((ArrayList)session.getAttribute("studentWishlistCourseDetails"));
            studentWishlistCourseDetails.clear();
            studentWishlistCourseDetails = new ArrayList<>(getLists.wishlistCourses());
            session.setAttribute("studentWishlistCourseDetails", studentWishlistCourseDetails);
        }
            ArrayList <Course_DTO> studentEnrolledCourseDetails = new ArrayList<>((ArrayList)session.getAttribute("studentEnrolledCourseDetails"));
            studentEnrolledCourseDetails.clear();
            studentEnrolledCourseDetails = new ArrayList<>(getLists.enrolledCourses());
            session.setAttribute("studentEnrolledCourseDetails", studentEnrolledCourseDetails);
        response.sendRedirect("Student_Home.jsp");
        return;
    }
}
