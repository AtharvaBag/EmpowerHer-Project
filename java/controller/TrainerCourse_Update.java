package controller;

import dto.Course_DTO;
import model.TrainerCourse_Registration;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TrainerCourse_Updation;

public class TrainerCourse_Update extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Course_DTO updatedCourseDetails = new Course_DTO();
        
        updatedCourseDetails.setTrainerskill(request.getParameter("updatedcourseskill"));
        updatedCourseDetails.setCourseclassname(request.getParameter("updatedcourseclassname"));
        updatedCourseDetails.setCourseclasscontact(request.getParameter("updatedcourseclasscontact"));
        updatedCourseDetails.setCoursetitle(request.getParameter("updatedcoursetitle"));
        updatedCourseDetails.setCourseviewers(request.getParameter("updatedcourseviewers"));
        updatedCourseDetails.setCourseduration(request.getParameter("updatedcourseduration"));
        updatedCourseDetails.setCoursedays(request.getParameter("updatedcoursedays"));
        updatedCourseDetails.setCoursemode(request.getParameter("updatedcoursemode"));
        updatedCourseDetails.setCourseclassaddress(request.getParameter("updatedcourseclassaddress"));
        updatedCourseDetails.setCourseclasscity(request.getParameter("updatedcourseclasscity"));
        updatedCourseDetails.setCourseclassstate(request.getParameter("updatedcourseclassstate"));
        updatedCourseDetails.setCoursedescription(request.getParameter("updatedcoursedescription"));
        updatedCourseDetails.setCoursecertification(request.getParameter("updatedcoursecertificate"));
        updatedCourseDetails.setCoursefee(request.getParameter("updatedcoursefee"));
        
        HttpSession session = request.getSession(true);
        String trainercourseid = (String)session.getAttribute("trainercourseid");
        TrainerCourse_Registration checkUpdates = new TrainerCourse_Registration(updatedCourseDetails);
        System.out.println("Control Flow: TrainerCourse_Update controller");
        System.out.println(updatedCourseDetails.getCourseclassaddress()+" "+updatedCourseDetails.getCourseclassstate()+" "+updatedCourseDetails.getCourseclasscity() +" "+updatedCourseDetails.getCoursemode());
        if(checkUpdates.isDetailsValid())
        {
            TrainerCourse_Updation update = new TrainerCourse_Updation(updatedCourseDetails);
            update.updateCourse(trainercourseid);
            
            String isCourseVerified = (String)session.getAttribute("isCourseVerified");
            session.removeAttribute(isCourseVerified);
            isCourseVerified="2";
            session.setAttribute("isCourseVerified", isCourseVerified);
            response.sendRedirect("Trainer_Courses_List.jsp");
            return;
        }
        response.sendRedirect("Trainer_Course_View.jsp");
        return;
    }
}
