package controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TrainerLogin_Authenticator;

public class TrainerLogin_Checker extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String trainerid = request.getParameter("trainerid").trim();
        String trainerpassword = request.getParameter("trainerpassword");
        String trainerVerificationStatus = null;
        String trainername = null;
        
        TrainerLogin_Authenticator check = new TrainerLogin_Authenticator(trainerid, trainerpassword);
        
        System.out.println(trainerid+" "+trainerpassword);
        
        if(check.isValid())
        {
            HttpSession session = request.getSession();
            session.setAttribute("trainerid", trainerid);
            trainername = check.getTrainername(trainerid);
            session.setAttribute("trainername", trainername);
            trainerVerificationStatus = check.getTrainerVerfificationStatus(trainerid);
            session.setAttribute("trainerVerificationStatus", trainerVerificationStatus);
            response.sendRedirect("Trainer_Home_Page.jsp");
            return;
        }
        response.sendRedirect("home.html");
        return;
    }
}
