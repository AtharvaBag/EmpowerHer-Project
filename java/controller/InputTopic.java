package controller;

import dto.Course_DTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CheckInput;

public class InputTopic extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String inputcourse = request.getParameter("inputcourse");
        inputcourse = inputcourse.trim();
        CheckInput check = new CheckInput(inputcourse.trim().toUpperCase());
        
        if(check.isValid())
        {
            ArrayList <String> verifiedCoursesList = check.getCourses();
            HttpSession session = request.getSession(true);
            session.setAttribute("verifiedCoursesList", verifiedCoursesList);
            String searchquery = request.getParameter("inputcourse");
            session.setAttribute("searchquery", searchquery);
            ArrayList <Course_DTO> courseBasicDetails = check.getCourseDetails();
            for(int i=0; i<courseBasicDetails.size(); i++)
            {
                Course_DTO details = courseBasicDetails.get(i);
                System.out.println(details.getCourseclassname() + " ");
            }
            inputcourse = check.domainSelector();
            session.setAttribute("inputcourse", inputcourse);
            session.setAttribute("courseBasicDetails", courseBasicDetails);
            response.sendRedirect("CoursesPage.jsp");
            //response.sendRedirect("tempjsp.jsp");
            return;
        }
        
        response.sendRedirect("home.html");
        return;
    }
}
