package controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin_Login_Verification;

public class Admin_Login_Check extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String adminid = request.getParameter("adminid");
        String adminpassword = request.getParameter("adminpassword");
        boolean isAdminVerified = false;
        HttpSession session = request.getSession();
        session.setAttribute("isAdminVerified", isAdminVerified);
        Admin_Login_Verification verify = new Admin_Login_Verification(adminid, adminpassword);
        if(verify.verifyAdmin())
        {
            System.out.println("Admin Verified");
            session.removeAttribute("isAdminVerified");
            isAdminVerified = true;
            session.setAttribute("isAdminVerified", isAdminVerified);
            response.sendRedirect("Admin_Home.jsp");
            return;
        }
        else
        {
            System.out.println("Admin Login failed!");
            response.sendRedirect("home.html");
            return;
        }
    }
}
