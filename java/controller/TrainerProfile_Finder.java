package controller;

import dto.Trainer_DTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TrainerProfile_Getter;
public class TrainerProfile_Finder extends HttpServlet {

    public void doGet(HttpServletRequest resquest, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession(true);
        String trainername = (String)session.getAttribute("trainername");
        String trainerid = (String)session.getAttribute("trainerid");
        
        TrainerProfile_Getter getProfile = new TrainerProfile_Getter(trainername, trainerid);
        Trainer_DTO trainerDetails = new Trainer_DTO();
        
        trainerDetails = getProfile.fetchDetails(trainerDetails);
        session.setAttribute("trainerDetails",trainerDetails);
        response.sendRedirect("Trainer_Profile.jsp");
        return;
    }
}
