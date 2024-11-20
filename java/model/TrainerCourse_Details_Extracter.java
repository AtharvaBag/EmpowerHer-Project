package model;

import db.DBConnector;
import dto.Course_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainerCourse_Details_Extracter {

    private String trainerid, trainercourseid;
    public TrainerCourse_Details_Extracter(String trainerid, String trainercourseid)
    {
        this.trainerid = trainerid;
        this.trainercourseid = trainercourseid;
    }
    public Course_DTO getCourseDetails()
    {
        Course_DTO registeredCourseDetails = new Course_DTO();
        registeredCourseDetails.setCourseid(trainercourseid);
        try
        {
            String query = "SELECT course_institute_name, course_title, course_viewers, course_duration, course_days_per_week, course_mode, course_institution_address, "
                    + "course_institution_city, course_institution_state, course_description, course_certification, course_annual_fee, course_skill_domain, course_institute_contact FROM course_table WHERE course_id = '"+trainercourseid+"';";
            System.out.println(query);
            Statement st = DBConnector.getStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                registeredCourseDetails.setCourseclassname(rs.getString(1));
                registeredCourseDetails.setCoursetitle(rs.getString(2));
                registeredCourseDetails.setCourseviewers(rs.getString(3));
                registeredCourseDetails.setCourseduration(rs.getString(4));
                registeredCourseDetails.setCoursedays(rs.getString(5));
                registeredCourseDetails.setCoursemode(rs.getString(6));
                registeredCourseDetails.setCourseclassaddress(rs.getString(7));
                registeredCourseDetails.setCourseclasscity(rs.getString(8));
                registeredCourseDetails.setCourseclassstate(rs.getString(9));
                registeredCourseDetails.setCoursedescription(rs.getString(10));
                registeredCourseDetails.setCoursecertification(rs.getString(11));
                registeredCourseDetails.setCoursefee(rs.getString(12));
                registeredCourseDetails.setTrainerskill(rs.getString(13));
                registeredCourseDetails.setCourseclasscontact(rs.getString(14));
            }
            
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        
        
        return registeredCourseDetails;
    }
}
