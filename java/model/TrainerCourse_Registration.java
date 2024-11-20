package model;

import db.DBConnector;
import dto.Course_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainerCourse_Registration 
{
    private Course_DTO courseRegister;
    public TrainerCourse_Registration(Course_DTO courseRegister)
    {
        this.courseRegister = courseRegister;
    }
    
    public boolean isDetailsValid()
    {
        if(courseRegister.getCourseclasscontact().trim().equals("") || courseRegister.getCourseclasscontact().trim().length()!=10 || courseRegister.getTrainerskill().trim().equals("") || courseRegister.getCoursetitle().trim().equals("") || courseRegister.getCourseclassname().trim().equals("") || courseRegister.getCoursedays().trim().equals("") || courseRegister.getCoursedescription().trim().equals("") || courseRegister.getCourseduration().trim().equals("") ||   courseRegister.getCoursecertification().trim().equals("") || courseRegister.getCoursefee().trim().equals("") || courseRegister.getCoursemode().trim().equals(""))
        {
            return false;
        }
        if((courseRegister.getCoursemode().equalsIgnoreCase("Offline") || courseRegister.getCoursemode().equalsIgnoreCase("Hybrid")) && (courseRegister.getCourseclassaddress().trim().equals("") || courseRegister.getCourseclasscity().trim().equals("") || courseRegister.getCourseclassstate().trim().equals("")))
        {
             return false;
        }
        return true;
    }
    
    public void registerCourse(String trainerid)
    {
        if(courseRegister.getCoursedescription().trim().length()>100)
        {
            courseRegister.setCoursedescription(courseRegister.getCoursedescription().substring(0,100).trim());
        }
        
        String query = "INSERT INTO course_table (course_institute_name, course_title, course_viewers, course_duration, course_days_per_week, course_mode, course_institution_address, course_institution_city, course_institution_state, course_description, course_certification, course_annual_fee, course_skill_domain, course_institute_contact)"
                + "VALUES ('"+courseRegister.getCourseclassname().trim().toUpperCase()+"', '"+courseRegister.getCoursetitle().trim().toUpperCase()+"', '"+courseRegister.getCourseviewers().trim().toUpperCase()+"', '"+courseRegister.getCourseduration().trim().toUpperCase()+"', '"+courseRegister.getCoursedays().trim()+"',"
                + "'"+courseRegister.getCoursemode().trim().toUpperCase()+"', '"+courseRegister.getCourseclassaddress().trim().toUpperCase()+"', '"+courseRegister.getCourseclasscity().trim().toUpperCase()+"', '"+courseRegister.getCourseclassstate().trim().toUpperCase()+"',"
                + "'"+courseRegister.getCoursedescription().trim()+"', '"+courseRegister.getCoursecertification().trim()+"', '"+courseRegister.getCoursefee().trim()+"', '"+courseRegister.getTrainerskill().trim().toUpperCase()+"', '"+courseRegister.getCourseclasscontact().trim()+"')";

                System.out.println(query);
                        
                
        String query2 = "SELECT course_id FROM course_table WHERE course_title='"+courseRegister.getCoursetitle().trim().toUpperCase()+"' AND course_institute_contact='"+courseRegister.getCourseclasscontact().trim()+"'";
        System.out.println(query2);
        
        
        try
                {
                    int i=0;
                    Statement st = DBConnector.getStatement();
                    int j = st.executeUpdate(query);
                    if(j>0)
                    {
                        System.out.println("Corse Registered Successfully!");
                        ResultSet rs = st.executeQuery(query2);
                        if(rs.next())
                            courseRegister.setCourseid(rs.getString(1));
                        j = st.executeUpdate("INSERT INTO trainer_course_table (trainer_id, course_id, verified) VALUES('"+trainerid.trim()+"', '"+courseRegister.getCourseid().trim()+"', '0')");
                        if(j>0)
                             System.out.println("trainer_course_table updated");
                    }
                    
                }
                catch(SQLException e)
                {
                    System.out.println(e);
                }
    }
}
