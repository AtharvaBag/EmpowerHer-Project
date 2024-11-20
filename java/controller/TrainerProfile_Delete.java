package controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TrainerProfile_Deletion;

public class TrainerProfile_Delete extends HttpServlet {

       public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
       {
           response.sendRedirect("home.html");
           return;
       }
       public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
       {
           HttpSession session = request.getSession(true);
           TrainerProfile_Deletion delete = new TrainerProfile_Deletion((String)session.getAttribute("trainerid"));
           delete.deleteProfile();
           response.sendRedirect("home.html");
           return;
       }
}
