package model;

import db.DBConnector;
import dto.Course_DTO;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainerCourse_Updation 
{
    private Course_DTO updatedCourseDetails;
    public TrainerCourse_Updation(Course_DTO updatedCourseDetails)
    {
        this.updatedCourseDetails = updatedCourseDetails;
    }
    
    public void updateCourse(String trainercourseid)
    {
        System.out.println("Control Flow: updateCourse");
        try
        {
            String query = "UPDATE course_table SET course_institute_name = '"+updatedCourseDetails.getCourseclassname().trim().toUpperCase()
                    +"', course_title = '"+updatedCourseDetails.getCoursetitle().trim().toUpperCase()+"', course_viewers='"+ updatedCourseDetails.getCourseviewers().trim().toUpperCase()+
                    "', course_duration='"+ updatedCourseDetails.getCourseduration().trim()+"', course_days_per_week='"+ updatedCourseDetails.getCoursedays().trim()+
                    "', course_mode='"+ updatedCourseDetails.getCoursemode().trim().toUpperCase()+"', course_institution_address='"+ updatedCourseDetails.getCourseclassaddress().trim().toUpperCase()+
                    "', course_institution_city='"+ updatedCourseDetails.getCourseclasscity().trim().toUpperCase()+"' ,"
                    + "course_institution_state='"+ updatedCourseDetails.getCourseclassstate().trim().toUpperCase()+"', course_description='"+ updatedCourseDetails.getCoursedescription().trim() +"', "
                    + "course_certification='"+updatedCourseDetails.getCoursecertification().trim()+"', course_annual_fee = '"+ updatedCourseDetails.getCoursefee().trim()+"', course_skill_domain='"+ updatedCourseDetails.getTrainerskill().trim().toUpperCase()+"'"
                    + ", course_institute_contact='"+ updatedCourseDetails.getCourseclasscontact().trim()+"' WHERE course_id='"+trainercourseid+"'";
            
            String query2 = "UPDATE trainer_course_table SET verified='2' WHERE course_id = '"+trainercourseid+"' ";
           
            System.out.println(query);
            System.out.println(query2);
            
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
