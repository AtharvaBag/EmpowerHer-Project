package model;

import db.DBConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainerLogin_Authenticator 
{
    private static String trainerid, trainerpassword;
    public static String trainername = null, trainerVerificationStatus=null;
    public TrainerLogin_Authenticator(String trainerid, String trainerpassword)
    {
        this.trainerid = trainerid;
        this.trainerpassword = trainerpassword;
    }
    public boolean isValid()
    {
        String query = "SELECT trainer_name, trainer_password FROM trainer_table WHERE trainer_id = '"+trainerid+"'";
        
       String table_trainer_name=null, table_trainer_password=null;
        try
        {
            Statement st = DBConnector.getStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next())
            {
                table_trainer_name = rs.getString(1).trim();
                table_trainer_password = rs.getString(2).trim();
            }
            System.out.println("Table Data: "+ table_trainer_name + " "+ table_trainer_password);
            if(table_trainer_password==null || table_trainer_name==null)
            {
                System.out.println("Login failed! No data found");
                return false;
            }
            if(table_trainer_password.equals(trainerpassword))
            {
                System.out.println("Login Successful!");
                trainername = table_trainer_name;
                rs = st.executeQuery("SELECT verified FROM trainer_verification_table WHERE trainer_id = '"+trainerid+"'");
                if(rs.next())
                {
                    trainerVerificationStatus = rs.getString(1);
                    System.out.println(trainerVerificationStatus);
                }
                return true;
            }
            else
            {
                System.out.println("Login Failed! Incorrect Password");
                return false;
            }
         }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        System.out.println("Login Failed!");
        return false;
    }
    public String getTrainername(String trainerid)
    {
        return trainername;
    }
    public String getTrainerVerfificationStatus(String trainerid)
    {
        return trainerVerificationStatus;
    }
}
