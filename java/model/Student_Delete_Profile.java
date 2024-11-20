package model;

import db.DBConnector;
import dto.Student_DTO;
import java.sql.SQLException;
import java.sql.Statement;

public class Student_Delete_Profile {

    private Student_DTO studentDetails;
    public Student_Delete_Profile(Student_DTO studentDetails)
    {
        this.studentDetails = studentDetails;
    }
    
    public void deleteProfile()
    {
        try
        {
            String query = "DELETE FROM student_table WHERE student_id = '"+studentDetails.getStudentid()+"'";
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
