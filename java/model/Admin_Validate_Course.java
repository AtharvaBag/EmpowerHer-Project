package model;

import db.DBConnector;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin_Validate_Course {

    private String courseid;
    public Admin_Validate_Course(String courseid)
    {
        this.courseid = courseid;
    }
    
    public boolean validateCourse()
    {
        try
        {
            String query = "UPDATE trainer_course_table SET verified = '1'  WHERE course_id = '"+courseid+"'";
            System.out.println(query);
            Statement st = DBConnector.getStatement();
            int i = st.executeUpdate(query);
            if(i>0)
            {
                System.out.println("Course Validated in database!");
                return true;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean invalidateCourse()
    {
        try
        {
            String query = "UPDATE trainer_course_table SET verified = '0'  WHERE course_id = '"+courseid+"'";
            System.out.println(query);
            Statement st = DBConnector.getStatement();
            int i = st.executeUpdate(query);
            if(i>0)
            {
                System.out.println("Course invalidated in database!");
                return true;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return false;
    }
}
