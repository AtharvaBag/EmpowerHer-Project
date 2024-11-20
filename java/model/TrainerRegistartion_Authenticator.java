package model;

import db.DBConnector;
import dto.Trainer_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainerRegistartion_Authenticator 
{
    private String trainerpassword, trainerconfirmpassword, traineremailid, traineradharnumber;
    public TrainerRegistartion_Authenticator(String trainerpassword, String trainerconfirmpassword, String traineremailid, String traineradharnumber)
    {
        this.trainerpassword = trainerpassword;
        this.trainerconfirmpassword = trainerconfirmpassword;
        this.traineremailid = traineremailid;
        this.traineradharnumber = traineradharnumber;
    }
    public boolean isTrainerPasswordValid()
    {
        if(trainerpassword.equals(trainerconfirmpassword) && trainerpassword.length()>=8)
        {
            return true;
        }
        System.out.println("Invalid password");
        return false;
    }
    
    public boolean isMailIdValid()
    {
        if(traineremailid.contains("@") && (traineremailid.contains(".com") || traineremailid.contains(".org") || traineremailid.contains(".edu")))
        {
            return true;
        }
        System.out.println("Invalid mail id");
        return false;
    }
    
    public boolean isAdharNumberValid()
    {
        if(traineradharnumber.length()==12)
        {
            return true;
        }
        System.out.println("Invalid Adhar Card Number");
        return false;
    }
    public void registerTrainer(Trainer_DTO trainerDetails)
    {
        String trainername = trainerDetails.getTrainerfirstname().toUpperCase().trim() + " " + trainerDetails.getTrainerlastname().toUpperCase().trim();
        String query = "INSERT INTO trainer_table (trainer_first_name, trainer_middle_name, trainer_last_name, trainer_name, trainer_birth_date, trainer_address, trainer_city, trainer_state, trainer_contact, trainer_email_id, trainer_adhar_card_number, trainer_password) "
                + "VALUES('"+trainerDetails.getTrainerfirstname().toUpperCase()+"', '"+trainerDetails.getTrainermiddlename().toUpperCase()+"', '"+trainerDetails.getTrainerlastname().toUpperCase()+"', '"+trainername+"','"+trainerDetails.getTrainerdob()+"', '"+trainerDetails.getTraineraddress().toUpperCase()+"',"
                + "'"+trainerDetails.getTrainercity().toUpperCase()+"', '"+trainerDetails.getTrainerstate().toUpperCase()+"', '"+trainerDetails.getTrainercontact()+"', '"+trainerDetails.getTraineremailid()+"', '"+trainerDetails.getTraineradharnumber()+"', '"+trainerDetails.getTrainerpassword()+"')";
        
        System.out.println(query);
        
        System.out.println("SELECT trainer_id FROM trainer_table WHERE trainer_name = '"+trainerDetails.getTrainername()+"' AND trainer_contact = '"+trainerDetails.getTrainercontact()+"'");
        
        try
        {
            Statement st = DBConnector.getStatement();
            int i = st.executeUpdate(query);
            if(i>0)
            {
                System.out.println("Trainer Registered Succesfully!");
                ResultSet rs = st.executeQuery("SELECT trainer_id FROM trainer_table WHERE trainer_name = '"+trainerDetails.getTrainername()+"' AND trainer_contact = '"+trainerDetails.getTrainercontact()+"'");
               if(rs.next())
                {
                    trainerDetails.setTrainerid(rs.getString(1));
                    System.out.println(trainerDetails.getTrainerid());
                   st.executeUpdate("INSERT INTO trainer_verification_table (trainer_id, verified) VALUES('"+trainerDetails.getTrainerid()+"', '0')");
                   trainerDetails.setTrainerverificationstatus("0");
                }
            }
            else
            {
                System.out.println("Trainer Registration Failed!");
            }
            
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return;
    }
}
