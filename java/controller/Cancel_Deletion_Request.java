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

public class Cancel_Deletion_Request extends HttpServlet {
 
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect("home.html");
        return;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String deletedstudentid = request.getParameter("deletedstudentid");
        HttpSession session = request.getSession();
        ArrayList <Student_DTO> studentsList = (ArrayList)session.getAttribute("studentsList");
        ArrayList <Student_DTO> deletedStudentsList = (ArrayList)session.getAttribute("deletedStudentsList");
        String trainercourseid = (String)session.getAttribute("trainercourseid");
        Request_Deletion cancelDeletion = new Request_Deletion(trainercourseid, deletedstudentid);
        if(cancelDeletion.cancelStudentDeletion())
        {
            System.out.println("Student Renrolled Successfully");
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
            System.out.println("Unable to process deletion request!");
        }
        response.sendRedirect("Course_Student_List.jsp");
        
        return;
    }
}
