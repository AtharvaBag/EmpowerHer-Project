package model;

import db.DBConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TrainerProfile_Deletion {
    private String trainerid;
    public TrainerProfile_Deletion(String trainerid)
    {
        this.trainerid = trainerid;
    }
    public void deleteProfile()
    {
        String query = "DELETE FROM trainer_table WHERE trainer_id = '"+trainerid+"'";
        String query2 = "DELETE FROM trainer_verification_table WHERE trainer_id = '"+trainerid+"'";
        String query3 = "SELECT course_id FROM trainer_course_table WHERE trainer_id = '"+trainerid+"'";
        String query4 = "DELETE FROM trainer_course_table WHERE trainer_id = '"+trainerid+"'";
        List <String> courseIdList = new ArrayList<>();
        try
        {
            Statement st = DBConnector.getStatement();
            ResultSet rs = st.executeQuery(query3);
            while(rs.next())
            {
                courseIdList.add(rs.getString(1));
            }
            System.out.println("Deleted courses: "+courseIdList);
            for(int i=0; i<courseIdList.size(); i++)
            {
                st.execute("DELETE FROM course_table WHERE course_id = '"+courseIdList.get(i)+"'");
            }
            courseIdList.clear();
            st.execute(query);
            st.execute(query2);
            st.execute(query4);
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
}
