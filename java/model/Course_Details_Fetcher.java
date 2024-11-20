package model;

import db.DBConnector;
import dto.Course_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Course_Details_Fetcher {
    
    private Course_DTO courseDetails;
    public Course_Details_Fetcher(Course_DTO courseDetails)
    {
        this.courseDetails = courseDetails;
    }
    
    public Course_DTO getDetails()
    {
        try
        {
            String query = "SELECT course_institute_name, course_title, course_viewers, course_duration, "
                    + "course_days_per_week, course_mode, course_institution_address, course_institution_city, "
                    + "course_institution_state, course_description, course_certification, course_annual_fee, "
                    + "course_skill_domain, course_institute_contact FROM course_table WHERE course_id='"+courseDetails.getCourseid().trim()+"' ";
         
            System.out.println(query);
         
            Statement st = DBConnector.getStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next())
            {
                courseDetails.setCourseclassname(rs.getString(1));
                courseDetails.setCoursetitle(rs.getString(2));
                courseDetails.setCourseviewers(rs.getString(3));
                courseDetails.setCourseduration(rs.getString(4));
                courseDetails.setCoursedays(rs.getString(5));
                courseDetails.setCoursemode(rs.getString(6));
                courseDetails.setCourseclassaddress(rs.getString(7));
                courseDetails.setCourseclasscity(rs.getString(8));
                courseDetails.setCourseclassstate(rs.getString(9));
                courseDetails.setCoursedescription(rs.getString(10));
                courseDetails.setCoursecertification(rs.getString(11));
                courseDetails.setCoursefee(rs.getString(12));
                courseDetails.setTrainerskill(rs.getString(13));
                courseDetails.setCourseclasscontact(rs.getString(14));
                
                System.out.println(courseDetails.getCourseviewers());
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return courseDetails;
    }
}
