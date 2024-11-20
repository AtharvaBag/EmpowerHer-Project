package controller;

import dto.Student_DTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Request_Deletion;
import model.Trainer_Course_Students_List;

public class Request_For_Deletion extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
            String studentid = request.getParameter("studentid");
            HttpSession session = request.getSession();
            ArrayList <Student_DTO> studentsList = (ArrayList)session.getAttribute("studentsList");
            ArrayList <Student_DTO> deletedStudentsList = (ArrayList)session.getAttribute("deletedStudentsList");
            String trainercourseid = (String)session.getAttribute("trainercourseid");
            Request_Deletion requestToDelete = new Request_Deletion(trainercourseid, studentid);
            if(requestToDelete.requestDeletion())
            {
                System.out.println("Deletion Request successful!");
                session.removeAttribute("studentsList");
                session.removeAttribute("deletedStudentsList");
                Trainer_Course_Students_List getNewList = new Trainer_Course_Students_List(trainercourseid);
                studentsList = new ArrayList<>(getNewList.fetchStudents());
                deletedStudentsList = new ArrayList<>(getNewList.fetchDeletedStudents());
                session.setAttribute("studentsList", studentsList);
                session.setAttribute("deletedStudentsList", deletedStudentsList);
            }
            else
            {
                System.out.println("Deletion Request unsuccessful!");
            }
            response.sendRedirect("Course_Student_List.jsp");
            
            return;
    }
}
