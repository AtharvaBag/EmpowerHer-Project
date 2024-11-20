package model;

import db.DBConnector;
import dto.Trainer_DTO;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainerProfile_Updation {
        private String trainerid;
        private Trainer_DTO updateProfile;
        public TrainerProfile_Updation(String trainerid, Trainer_DTO updateProfile)
        {
            this.trainerid = trainerid;
            this.updateProfile = updateProfile;
        }
        public boolean checkMailid()
        {
             if(updateProfile.getTraineremailid().contains("@") && (updateProfile.getTraineremailid().contains(".com") || updateProfile.getTraineremailid().contains(".org") || updateProfile.getTraineremailid().contains(".edu")))
            {
                return true;
            }
            System.out.println("Invalid mail id");
            return false;
        }
        public boolean checkContact()
        {
            if(updateProfile.getTrainercontact().length()==10)
                return true;
            return false;
        }
        public boolean checkAdharCardNumber()
        {
            if(updateProfile.getTraineradharnumber().length()==12)
                return true;
            return false;
        }
        public void updateProfile()
        {
            String trainername = updateProfile.getTrainerfirstname().trim().toUpperCase()+" "+updateProfile.getTrainerlastname().trim().toUpperCase();
            String query = "UPDATE trainer_table SET trainer_first_name='"+updateProfile.getTrainerfirstname()+"', trainer_middle_name='"+updateProfile.getTrainermiddlename()+"', "
                    + "trainer_last_name = '"+updateProfile.getTrainerlastname()+"', trainer_birth_date = '"+updateProfile.getTrainerdob()+"', trainer_address = '"+updateProfile.getTraineraddress()+"',"
                    + "trainer_city = '"+updateProfile.getTrainercity()+"', trainer_state = '"+updateProfile.getTrainerstate()+"', trainer_contact='"+updateProfile.getTrainercontact()+"', trainer_email_id = '"+updateProfile.getTraineremailid()+"', "
                    + "trainer_adhar_card_number = '"+updateProfile.getTraineradharnumber()+"',"
                    + "trainer_name='"+trainername+"' WHERE trainer_id = '"+trainerid+"'";
            
            String query2 = "UPDATE trainer_verification_table SET verified='2' WHERE trainer_id='"+trainerid+"'";
            
            try
            {
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
