package model;

import db.DBConnector;
import dto.Course_DTO;
import dto.Student_DTO;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin_Student_Update_Enrollment {

    private Course_DTO courseDetails;
    private Student_DTO studentDetails;
    public Admin_Student_Update_Enrollment(Student_DTO studentDetails, Course_DTO courseDetails)
    {
        this.courseDetails = courseDetails;
        this.studentDetails = studentDetails;
    }
    
    public boolean removeStudent()
    {
        String query = "DELETE FROM student_course_table WHERE student_id = '"+studentDetails.getStudentid()+"' AND course_id = '"+courseDetails.getCourseid()+"'";
        System.out.println(query);
        try
        {
            Statement st = DBConnector.getStatement();
            int i = st.executeUpdate(query);
            if(i>0)
            {
                System.out.println("Student deleted from course");
                return true;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean reenrollStudent()
    {
        String query = "UPDATE student_course_table SET enrolled='1' WHERE student_id='"+studentDetails.getStudentid()+"' AND course_id='"+courseDetails.getCourseid()+"'";
        System.out.println(query);
        try
        {
            Statement st = DBConnector.getStatement();
            int i = st.executeUpdate(query);
            if(i>0)
            {
                System.out.println("Student reenrolled to course successfully");
                return true;
            }
        }
       catch(SQLException e)
       {
           System.out.println(e);
       }
        return false;
   }
}
