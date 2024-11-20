/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import db.DBConnector;
import dto.Course_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Atharva Bagdare
 */
public class Student_Course_Details_Fetcher {
 
    private String enrolledcourseid;
    public Student_Course_Details_Fetcher(String enrolledcourseid)
    {
        this.enrolledcourseid = enrolledcourseid;
    }
    
    public Course_DTO courseDetails()
    {
        Course_DTO details = new Course_DTO();
        
        try
        {
            String query = "SELECT course_institute_name, course_title, course_viewers, course_duration, course_days_per_week, course_mode, course_institution_address, "
                    + "course_institution_city, course_institution_state, course_description, course_certification, course_annual_fee, course_skill_domain, course_institute_contact FROM course_table WHERE course_id = '"+enrolledcourseid.trim()+"';";

               Statement st = DBConnector.getStatement();
               ResultSet rs = st.executeQuery(query);
               while(rs.next())
               {
                   details.setCourseclassname(rs.getString(1));
                   details.setCoursetitle(rs.getString(2));
                   details.setCourseviewers(rs.getString(3));
                   details.setCourseduration(rs.getString(4));
                   details.setCoursedays(rs.getString(5));
                   details.setCoursemode(rs.getString(6));
                   details.setCourseclassaddress(rs.getString(7));
                   details.setCourseclasscity(rs.getString(8));
                   details.setCourseclassstate(rs.getString(9));
                   details.setCoursedescription(rs.getString(10));
                   details.setCoursecertification(rs.getString(11));
                   details.setCoursefee(rs.getString(12));
                   details.setTrainerskill(rs.getString(13));
                   details.setCourseclasscontact(rs.getString(14));
               }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return details;
    }
}
