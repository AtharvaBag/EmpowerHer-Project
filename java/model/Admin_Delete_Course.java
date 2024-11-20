package model;

import db.DBConnector;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin_Delete_Course {

    private String courseid;
    public Admin_Delete_Course(String courseid)
    {
        this.courseid = courseid;
    }
    
    public boolean deleteCourse()
    {
        try
        {
            String query = "DELETE FROM course_table WHERE course_id = '"+courseid+"'";
            String query2 = "DELETE FROM trainer_course_table WHERE course_id = '"+courseid+"'";
            String query3 = "DELETE FROM student_course_table WHERE course_id = '"+courseid+"'";
            
            System.out.println(query+"\n"+query2+"\n"+query3);
            Statement st = DBConnector.getStatement();
            int i = st.executeUpdate(query);
            int j = st.executeUpdate(query2);
            st.executeUpdate(query3);
            if(i!=0 && j!=0)
                return true;
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return false;
    }
}
