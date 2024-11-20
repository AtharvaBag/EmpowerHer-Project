package model;

import db.DBConnector;
import dto.Course_DTO;
import dto.Student_DTO;
import java.sql.SQLException;
import java.sql.Statement;

public class Enroll_Student {
    Course_DTO courseDetails;
    Student_DTO studentDetails;
    public Enroll_Student(Course_DTO courseDetails, Student_DTO studentDetails)
    {
        this.courseDetails = courseDetails;
        this.studentDetails = studentDetails;
    }
    
    public void enrollCourse()
    {
        try
        {
            String query = "INSERT INTO student_course_table (course_id, student_id, enrolled) VALUES ('"+courseDetails.getCourseid().trim()+"', '"+studentDetails.getStudentid().trim()+"', '1')";
            System.out.println(query);
            Statement st = DBConnector.getStatement();
            int j = st.executeUpdate(query);
            if(j>0)
            {
                System.out.println("Student student enrolled successfully!");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    
    public void enrollWishedCourse()
    {
        try
        {
            String query = "UPDATE student_course_table SET enrolled='1' WHERE student_id = '"+studentDetails.getStudentid()+"' AND course_id='"+courseDetails.getCourseid()+"'";
            System.out.println(query);
            Statement st = DBConnector.getStatement();
            int j = st.executeUpdate(query);
            if(j>0)
            {
                System.out.println("Student enrolled to wishlisted course successfully!");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    
    public void wishlistCourse()
    {
        try
        {
             String query = "INSERT INTO student_course_table (course_id, student_id, enrolled) VALUES ('"+courseDetails.getCourseid().trim()+"', '"+studentDetails.getStudentid().trim()+"', '0')";
            System.out.println(query);
            Statement st = DBConnector.getStatement();
            int j = st.executeUpdate(query);
            if(j>0)
            {
                System.out.println("Course added to widhlist successfully!");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
}

