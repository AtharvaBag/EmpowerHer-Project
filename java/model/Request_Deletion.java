package model;

import db.DBConnector;
import java.sql.SQLException;
import java.sql.Statement;

public class Request_Deletion {
    
    private String trainercourseid;
    private String studentid;
    public Request_Deletion(String trainercourseid, String studentid)
    {
        this.trainercourseid = trainercourseid;
        this.studentid = studentid;
    }
    
    public boolean requestDeletion()
    {
        try
        {
            String query = "UPDATE student_course_table SET enrolled='2' WHERE student_id = '"+studentid+"' AND course_id = '"+trainercourseid+"'";
            System.out.println(query);
            Statement st = DBConnector.getStatement();
            int i = st.executeUpdate(query);
            if(i>0)
            {
                return true;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean cancelStudentDeletion()
    {
        try
        {
            String query = "UPDATE student_course_table SET enrolled='1' WHERE student_id = '"+studentid+"' AND course_id = '"+trainercourseid+"'";
            System.out.println(query);
            Statement st = DBConnector.getStatement();
            int i = st.executeUpdate(query);
            if(i>0)
            {
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
