package model;

import db.DBConnector;
import dto.Trainer_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainerProfile_Getter 
{
    private String trainername, trainerid;
    public TrainerProfile_Getter(String trainername, String trainerid)
    {
        this.trainerid = trainerid;
        this.trainername = trainername;
    }
    public Trainer_DTO fetchDetails(Trainer_DTO trainerDetails)
    {
        String query = "SELECT trainer_first_name, trainer_middle_name, trainer_last_name, trainer_birth_date, trainer_address, trainer_city, trainer_state, trainer_contact, trainer_email_id, trainer_adhar_card_number FROM trainer_table WHERE trainer_name='"+trainername+"' AND trainer_id='"+trainerid+"'";
        try
        {
            Statement st = DBConnector.getStatement();
            ResultSet rs = st.executeQuery(query);
           if(rs.next())
           {
               trainerDetails.setTrainerfirstname(rs.getString(1));
               trainerDetails.setTrainermiddlename(rs.getString(2));
               trainerDetails.setTrainerlastname(rs.getString(3));
               trainerDetails.setTrainerdob(rs.getString(4));
               trainerDetails.setTraineraddress(rs.getString(5));
               trainerDetails.setTrainercity(rs.getString(6));
               trainerDetails.setTrainerstate(rs.getString(7));
               trainerDetails.setTrainercontact(rs.getString(8));
               trainerDetails.setTraineremailid(rs.getString(9));
               trainerDetails.setTraineradharnumber(rs.getString(10));
           }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return trainerDetails;
    }
}
