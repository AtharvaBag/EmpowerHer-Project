package controller;

import dto.Payment_DTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin_Get_Payment_Details;

public class Check_Payment_Input extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Admin_Get_Payment_Details details = new Admin_Get_Payment_Details(request.getParameter("courseid"), request.getParameter("studentid"));
        if(!details.isInputValid())
        {
            System.out.println("No Input Found");
            response.sendRedirect("Admin_Payment_Details.jsp");
            return;
        }
        ArrayList <Payment_DTO> paymentDetailsList = new ArrayList<>((ArrayList)details.allPayments()); 
        HttpSession session = request.getSession();
        session.setAttribute("paymentDetailsList", paymentDetailsList);
        session.removeAttribute("showpayments");
        session.setAttribute("showpayments", true);
        response.sendRedirect("Admin_Payment_Details.jsp");
        return;
    }
}
