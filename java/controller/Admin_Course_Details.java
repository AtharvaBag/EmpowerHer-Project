package controller;

import dto.Course_DTO;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin_Get_Details;
import model.Course_Details_Fetcher;

public class Admin_Course_Details extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        boolean courseverified = false;
        boolean trainerverified = false;
        if(request.getParameter("verifiedtrainername")!=null && request.getParameter("verifiedtrainerid")!=null && request.getParameter("verifiedcourseid")!=null && request.getParameter("verifiedcoursename")!=null)
        {
            courseverified = true;
            session.setAttribute("courseverified", courseverified);
            session.setAttribute("trainername",request.getParameter("verifiedtrainername"));
            session.setAttribute("trainerid",request.getParameter("verifiedtrainerid"));
            session.setAttribute("coursename",request.getParameter("verifiedcoursename"));
            session.setAttribute("courseid",request.getParameter("verifiedcourseid"));
            Admin_Get_Details trainerStatus = new Admin_Get_Details((String)session.getAttribute("trainerid"));
            trainerverified = trainerStatus.isTrainerVerified();
            session.setAttribute("trainerverified", trainerverified);
        }
        else if(request.getParameter("unverifiedtrainername")!=null && request.getParameter("unverifiedtrainerid")!=null && request.getParameter("unverifiedcourseid")!=null && request.getParameter("unverifiedcoursename")!=null)
       {
            courseverified = false;
            session.setAttribute("courseverified", courseverified);
            session.setAttribute("trainername",request.getParameter("unverifiedtrainername"));
            session.setAttribute("trainerid",request.getParameter("unverifiedtrainerid"));
            session.setAttribute("coursename",request.getParameter("unverifiedcoursename"));
            session.setAttribute("courseid",request.getParameter("unverifiedcourseid"));
            Admin_Get_Details trainerStatus = new Admin_Get_Details((String)session.getAttribute("trainerid"));
            trainerverified = trainerStatus.isTrainerVerified();
            session.setAttribute("trainerverified", trainerverified);
        }
        
        Course_DTO courseDetails = new Course_DTO();
        courseDetails.setCourseid((String)session.getAttribute("courseid"));
        Course_Details_Fetcher details = new Course_Details_Fetcher(courseDetails);
        courseDetails = details.getDetails();
        session.setAttribute("courseDetails", courseDetails);
        response.sendRedirect("Admin_Course_Verification.jsp");
        return;
    }
}
