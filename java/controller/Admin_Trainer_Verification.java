package controller;

import dto.Trainer_DTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin_Get_Details;
import model.Admin_Validate_Trainer;

public class Admin_Trainer_Verification extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        boolean trainerverified = (boolean)session.getAttribute("trainerverified");
        String trainerid = (String)session.getAttribute("trainerid");
        Admin_Validate_Trainer update = new Admin_Validate_Trainer(trainerid); 
        if(trainerverified)
        {
            if(update.invalidateTrainer())
                    System.out.println("Trainer is invalidated!");
            trainerverified = false;
        }
        else
        {
            if(update.validateTrainer())
                System.out.println("Trainer is validated!");
            trainerverified = true;
        }
        session.setAttribute("trainerverified", trainerverified);
        Admin_Get_Details details = new Admin_Get_Details();
         ArrayList <Trainer_DTO> verifiedTrainers = new ArrayList<>(details.verifiedTrainers());
         session.removeAttribute("verifiedTrainers");
         session.setAttribute("verifiedTrainers", verifiedTrainers);
         ArrayList <Trainer_DTO> unverifiedTrainers = new ArrayList<>(details.unverifiedTrainers());
         session.removeAttribute("unverifiedTrainers");
         session.setAttribute("unverifiedTrainers", unverifiedTrainers);
        response.sendRedirect("Admin_Trainer_Verification.jsp");
        return;
    }
}
