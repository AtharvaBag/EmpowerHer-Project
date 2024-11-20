package model;

import db.DBConnector;
import dto.Student_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Student_Data_Extract {

    private Student_DTO studentDetails;
    private String studentid;
    public Student_Data_Extract(Student_DTO studentDetails)
    {
        this.studentDetails = studentDetails;
    }
    
    public Student_Data_Extract(String studentid, Student_DTO studentDetails)
    {
        this.studentid = studentid;
        this.studentDetails = studentDetails;
    }
    
    public void studentData()
    {
        try
        {
            String query = "SELECT student_id, student_mailid FROM student_table WHERE student_contact = '"+studentDetails.getStudentcontact().trim()+"'";
            Statement st = DBConnector.getStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next())
            {
                studentDetails.setStudentid(rs.getString(1));
                studentDetails.setStudentmailid(rs.getString(2));
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    
    
    public Student_DTO getStudentData()
    {
        try
        {
            String query = "SELECT student_name, student_contact, student_mailid FROM student_table WHERE student_id = '"+studentid+"'";
            studentDetails.setStudentid(studentid);
            Statement st = DBConnector.getStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next())
            {
                studentDetails.setStudentname(rs.getString(1));
                studentDetails.setStudentcontact(rs.getString(2));
                studentDetails.setStudentmailid(rs.getString(3));
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return studentDetails;
    }
}
