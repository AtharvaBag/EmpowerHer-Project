package model;

import db.DBConnector;
import dto.Student_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Student_Registration_Checker {

    private Student_DTO studentDetails;
    public Student_Registration_Checker(Student_DTO studentDetails)
    {
        this.studentDetails = studentDetails;
    }
    
    public boolean areDetailsValid()
    {
        if(studentDetails.getStudentname().trim().equals("") || studentDetails.getStudentcontact().trim().equals("") || studentDetails.getStudentmailid().equals("") || studentDetails.getStudentpassword().trim().equals("") || studentDetails.getStudentconfirmpassword().trim().equals(""))
        {
                return false;
        }
        return true;
    }
    
    public boolean isContactCorrect()
    {
        if(studentDetails.getStudentcontact().trim().length() != 10)
        {
            return false;
        }
        return true;
    }
    
    public boolean isMailIdCorrect()
    {
         if(studentDetails.getStudentmailid().contains("@") && (studentDetails.getStudentmailid().contains(".com") || studentDetails.getStudentmailid().contains(".org") || studentDetails.getStudentmailid().contains(".edu")))
        {
            return true;
        }
        System.out.println("Invalid mail id");
        return false;
    }
    
    public boolean isPasswordCorrect()
    {
        if(studentDetails.getStudentpassword().equals(studentDetails.getStudentconfirmpassword()) && studentDetails.getStudentpassword().length()>=8)
        {
            return true;
        }
        System.out.println("Password Correct");
        return false;
    }
    
    public void registerStudent()
    {
        try
        {
            String query = "INSERT INTO student_table (student_name, student_mailid, student_contact, student_password) VALUES ('"+studentDetails.getStudentname().trim().toUpperCase()+"', '"+studentDetails.getStudentmailid().trim()+"', '"+studentDetails.getStudentcontact().trim()+"', '"+studentDetails.getStudentpassword().trim()+"')";
            System.out.println(query);
            Statement st = DBConnector.getStatement();
            st.executeUpdate(query);
            String query2 = "SELECT student_id FROM student_table WHERE student_name = '"+studentDetails.getStudentname().trim()+"' AND student_contact='"+studentDetails.getStudentcontact().trim()+"'";
            System.out.println(query2);
            ResultSet rs = st.executeQuery(query2);
            if(rs.next())
            {
                studentDetails.setStudentid(rs.getString(1));
            }
            System.out.println("Student registered, id: "+studentDetails.getStudentid());
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
}
