package controller;

import dto.Trainer_DTO;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TrainerRegistartion_Authenticator;

public class TrainerRegistration_Checker extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String trainerVerificationStatus = "0";
        String trainerfirstname = request.getParameter("trainerfirstname");
        String trainermiddlename = request.getParameter("trainermiddlename");
        String trainerlastname = request.getParameter("trainerlastname");
        String trainerdob = request.getParameter("trainerdob");
        String traineraddress = request.getParameter("traineraddress");
        String trainercity = request.getParameter("trainercity");
        String trainerstate = request.getParameter("trainerstate");
        String trainercontact = request.getParameter("trainercontact");
        String traineremailid = request.getParameter("traineremailid");
        String traineradharnumber = request.getParameter("traineradharnumber");
        String trainerpassword = request.getParameter("trainerpassword");
        String trainerconfirmpassword = request.getParameter("trainerconfirmpassword");
        String trainername = request.getParameter("trainerfirstname") + " " + request.getParameter("trainerlastname");
       
        System.out.println(trainername);
        
        if(trainerfirstname.trim().equals("") || trainerlastname.trim().equals("") || trainerdob.equals("") || traineraddress.trim().equals("") || trainercity.trim().equals("") || trainerstate.equals("") || trainercontact.equals("") || traineremailid.equals("") || traineradharnumber.equals("") || trainerpassword.equals("") || trainerconfirmpassword.equals(""))
        {
            response.sendRedirect("trainer_registration.html");
            return;
        }
        HttpSession session = request.getSession(true);
        TrainerRegistartion_Authenticator check = new TrainerRegistartion_Authenticator(trainerpassword, trainerconfirmpassword, traineremailid, traineradharnumber);
        
        if(check.isTrainerPasswordValid() && check.isMailIdValid() && check.isAdharNumberValid())
        {
             session.setAttribute("trainername", trainername.trim());
             Trainer_DTO trainerDetails = new Trainer_DTO();
            
             trainerDetails.setTrainerfirstname(trainerfirstname);
             trainerDetails.setTrainermiddlename(trainermiddlename);
             trainerDetails.setTrainerlastname(trainerlastname);
             trainerDetails.setTrainername(trainername);
             trainerDetails.setTrainerdob(trainerdob);
             trainerDetails.setTraineraddress(traineraddress);
             trainerDetails.setTrainercity(trainercity);
             trainerDetails.setTrainerstate(trainerstate);
             trainerDetails.setTrainercontact(trainercontact);
             trainerDetails.setTraineremailid(traineremailid);
             trainerDetails.setTrainerpassword(trainerpassword);
             trainerDetails.setTraineradharnumber(traineradharnumber);
             check.registerTrainer(trainerDetails);
             session.setAttribute("trainerDetails",trainerDetails);
             session.setAttribute("trainerid", trainerDetails.getTrainerid());
             session.setAttribute("trainerVerificationStatus",  trainerVerificationStatus);
             response.sendRedirect("Trainer_Home_Page.jsp");
             return;
        }
        else 
        {
             response.sendRedirect("trainer_registration.html");
             return;
        }
    }
}