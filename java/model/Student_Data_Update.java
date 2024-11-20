package model;

import db.DBConnector;
import dto.Student_DTO;
import java.sql.SQLException;
import java.sql.Statement;

public class Student_Data_Update {

    private Student_DTO updatedDetails;
    public Student_Data_Update(Student_DTO updatedDetails)
    {
        this.updatedDetails = updatedDetails;
    }
    
    public void updateProfile()
    {
        try
        {
            String query = "UPDATE student_table SET student_name = '"+updatedDetails.getStudentname().trim().toUpperCase() +"', student_contact = '"+updatedDetails.getStudentcontact().trim()+"', student_mailid = '"+updatedDetails.getStudentmailid().trim()+"' WHERE student_id = '"+updatedDetails.getStudentid()+"'";
            System.out.println(query);
            Statement st = DBConnector.getStatement();
            st.executeUpdate(query);
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
}
