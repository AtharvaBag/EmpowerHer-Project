package controller;

import dto.Trainer_DTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TrainerProfile_Updation;

public class TrainerProfile_Update extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Trainer_DTO updateProfile = new Trainer_DTO();
        
        if(request.getParameter("trainerfirstname").trim().equals("") || request.getParameter("trainerlastname").trim().equals("") || request.getParameter("trainerdob").equals("")
                || request.getParameter("traineraddress").trim().equals("") || request.getParameter("trainercity").trim().equals("") || request.getParameter("trainerstate").trim().equals("")
                || request.getParameter("trainercontact").trim().equals("") || request.getParameter("traineremailid").trim().equals("") || request.getParameter("traineradharnumber").trim().equals(""))
        {
            response.sendRedirect("Trainer_Profile.jsp");
            return;
        }
        
        updateProfile.setTrainerfirstname(request.getParameter("trainerfirstname").trim().toUpperCase());
        updateProfile.setTrainermiddlename(request.getParameter("trainermiddlename").trim().toUpperCase());
        updateProfile.setTrainerlastname( request.getParameter("trainerlastname").trim().toUpperCase());
        updateProfile.setTrainerdob(request.getParameter("trainerdob"));
        updateProfile.setTraineraddress(request.getParameter("traineraddress").trim().toUpperCase());
        updateProfile.setTrainercity(request.getParameter("trainercity").trim().toUpperCase());
        updateProfile.setTrainerstate(request.getParameter("trainerstate").trim().toUpperCase());
        updateProfile.setTrainercontact(request.getParameter("trainercontact").trim());
        updateProfile.setTraineremailid(request.getParameter("traineremailid").trim());
        updateProfile.setTraineradharnumber(request.getParameter("traineradharnumber").trim());
        
        HttpSession session = request.getSession(true);
        String trainerid = (String)session.getAttribute("trainerid");
        TrainerProfile_Updation update = new TrainerProfile_Updation(trainerid, updateProfile);
        if(update.checkMailid() && update.checkContact() && update.checkAdharCardNumber())
        {
            update.updateProfile();
            System.out.println("Profile Updated!");
            String trainername = updateProfile.getTrainerfirstname()+" "+updateProfile.getTrainerlastname();
            session.setAttribute("trainername", trainername);
            String trainerVerificationStatus = (String)session.getAttribute("trainerVerificationStatus");
            session.removeAttribute("trainerVerificationStatus");
            trainerVerificationStatus="2";
            session.setAttribute("trainerVerificationStatus", trainerVerificationStatus);
            response.sendRedirect("Trainer_Home_Page.jsp");
            return;
        }
        response.sendRedirect("Trainer_Profile.jsp");
        return;
    }
}
