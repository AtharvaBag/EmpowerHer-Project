package model;

import db.DBConnector;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin_Validate_Trainer {

    private String trainerid;
    public Admin_Validate_Trainer(String trainerid)
    {
        this.trainerid = trainerid;
    }
    
    public boolean invalidateTrainer()
    {
        try
        {
            String query = "UPDATE trainer_verification_table SET verified = '0' WHERE trainer_id = '"+trainerid+"'";
            System.out.println(query);
            Statement st = DBConnector.getStatement();
            int i = st.executeUpdate(query);
            if(i>0)
            {
                System.out.println("Trainer Invalidated in database!");
                return true;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean validateTrainer()
    {
        try
        {
             String query = "UPDATE trainer_verification_table SET verified = '1' WHERE trainer_id = '"+trainerid+"'";
            System.out.println(query);
            Statement st = DBConnector.getStatement();
            int i = st.executeUpdate(query);
            if(i>0)
            {
                System.out.println("Trainer Validated in database!");
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
