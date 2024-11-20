package controller;

import dto.Trainer_DTO;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TrainerProfile_Getter;

public class Admin_Trainer_Details extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        if(request.getParameter("verifiedtrainerid")!=null && request.getParameter("verifiedtrainername")!=null)
        {
            boolean trainerverified = true;
            session.setAttribute("trainerverified", trainerverified);
            session.setAttribute("trainerid", request.getParameter("verifiedtrainerid"));
            session.setAttribute("trainername", request.getParameter("verifiedtrainername"));
        }
        else if(request.getParameter("unverifiedtrainerid")!=null && request.getParameter("unverifiedtrainername")!=null)
        {
            boolean trainerverified = false;
            session.setAttribute("trainerverified", trainerverified);
            session.setAttribute("trainerid", request.getParameter("unverifiedtrainerid"));
            session.setAttribute("trainername", request.getParameter("unverifiedtrainername"));
        }
        
        TrainerProfile_Getter getDetails = new TrainerProfile_Getter((String)session.getAttribute("trainername"), (String)session.getAttribute("trainerid"));
        Trainer_DTO trainerDetails = new Trainer_DTO();
        trainerDetails.setTrainerid((String)session.getAttribute("trainerid"));
        trainerDetails.setTrainername((String)session.getAttribute("trainername"));
        trainerDetails = getDetails.fetchDetails(trainerDetails);
        session.setAttribute("trainerDetails", trainerDetails);
        
        response.sendRedirect("Admin_Trainer_Verification.jsp");
        return;
    }
}
