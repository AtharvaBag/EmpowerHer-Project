package controller;

import dto.Payment_DTO;
import dto.Student_DTO;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Payment_Details;

public class Student_Payment_Details extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        Student_DTO studentDetails = (Student_DTO)session.getAttribute("studentDetails");
        Payment_Details details = new Payment_Details((String)session.getAttribute("studentcourseid"), studentDetails.getStudentid());
        Payment_DTO paymentDetails = details.getPaymentDetails();
        session.setAttribute("paymentDetails", paymentDetails);
        session.setAttribute("showPaymentDetailsToStudent", true);
        response.sendRedirect("Student_Course.jsp");
        return;
    }
}
