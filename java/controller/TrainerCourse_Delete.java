package controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Trainer_DeleteCourse;

public class TrainerCourse_Delete extends HttpServlet {

     public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
     {
         response.sendRedirect("home.html");
         return;
     }
     
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
     {
         HttpSession session = request.getSession(true);
         String trainercourseid = (String)session.getAttribute("trainercourseid");
         String trainercoursename = (String)session.getAttribute("trainercoursename");
         if(trainercourseid==null || trainercoursename==null)
         {
             response.sendRedirect("home.html");
             return;
         }
         Trainer_DeleteCourse delete = new Trainer_DeleteCourse(trainercourseid);
         delete.deleteCourse();
         session.removeAttribute(trainercourseid);
         response.sendRedirect("Trainer_Courses_List.jsp");
         return;
     }
}
