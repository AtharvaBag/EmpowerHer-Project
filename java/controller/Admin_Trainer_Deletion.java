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
import model.Admin_Delete_Trainer;
import model.Admin_Get_Details;

public class Admin_Trainer_Deletion extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        Admin_Delete_Trainer delete = new Admin_Delete_Trainer((String)session.getAttribute("trainerid"));
        if(delete.deleteTrainer())
        {
            System.out.println("Trainer Deleted Successfully!");
        }
        else
        {
            System.out.println("Unable to delete trainer");
            response.sendRedirect("Admin_Trainer_Verification.jsp");
            return;
        }
        Admin_Get_Details details = new Admin_Get_Details();
         ArrayList <Trainer_DTO> verifiedTrainers = new ArrayList<>(details.verifiedTrainers());
         session.removeAttribute("verifiedTrainers");
         session.setAttribute("verifiedTrainers", verifiedTrainers);
         ArrayList <Trainer_DTO> unverifiedTrainers = new ArrayList<>(details.unverifiedTrainers());
         session.removeAttribute("unverifiedTrainers");
         session.setAttribute("unverifiedTrainers", unverifiedTrainers);
        response.sendRedirect("Admin_Home.jsp");
        return;
    }
}
