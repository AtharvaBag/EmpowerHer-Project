package model;

import db.DBConnector;
import java.sql.SQLException;
import java.sql.Statement;

public class Trainer_DeleteCourse {
    private String trainercourseid;
    public Trainer_DeleteCourse(String trainercourseid)
    {
        this.trainercourseid = trainercourseid;
    }
    
    public void deleteCourse()
    {
        try
        {
            String query = "DELETE FROM course_table WHERE course_id='"+trainercourseid+"'";
            String query2 = "DELETE FROM trainer_course_table WHERE course_id='"+trainercourseid+"'";
            System.out.println(query+"      "+query2);
            Statement st = DBConnector.getStatement();
            st.execute(query);
            st.execute(query2);
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
}
