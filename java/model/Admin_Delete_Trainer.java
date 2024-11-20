package model;

import db.DBConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Admin_Delete_Trainer {

    private String trainerid;
    public Admin_Delete_Trainer(String trainerid)
    {
        this.trainerid = trainerid;
    }
    
    public boolean deleteTrainer()
    {
        try
        {
            String query = "DELETE FROM trainer_table WHERE trainer_id = '"+trainerid+"'";
            String query2 = "DELETE FROM trainer_verification_table WHERE trainer_id = '"+trainerid+"'";
            String query3 = "SELECT course_id FROM trainer_course_table  WHERE trainer_id = '"+trainerid+"'";
            System.out.println(query);
            System.out.println(query2);
            System.out.println(query3);
            Statement st = DBConnector.getStatement();
            int j = st.executeUpdate(query);
            st.executeUpdate(query2);
            ArrayList <String> courseList = new ArrayList<>();
            courseList.clear();
            ResultSet rs = st.executeQuery(query3);
            while(rs.next())
            {
                courseList.add(rs.getString(1));
            }
            if(!courseList.isEmpty())
            {
                for(int i=0; i<courseList.size(); i++)
                {
                    st.executeUpdate("DELETE FROM course_table WHERE course_id='"+courseList.get(i)+"'");
                    st.executeUpdate("DELETE FROM trainer_course_table WHERE course_id = '"+courseList.get(i)+"'");
                }
            }
            if(j>0)
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return false;
    }
}
