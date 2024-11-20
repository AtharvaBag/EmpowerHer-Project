package model;

import db.DBConnector;
import dto.Course_DTO;
import dto.Student_DTO;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Remove_Wishlisted_Course {

    private Course_DTO courseDetails;
    private Student_DTO studentDetails;
    
    public Remove_Wishlisted_Course(Course_DTO courseDetails, Student_DTO studentDetails)
    {
        this.courseDetails = courseDetails;
        this.studentDetails = studentDetails;
    }
    
    public boolean removeCourse()
    {
        try
        {
            String query = "DELETE FROM student_course_table WHERE student_id = '"+studentDetails.getStudentid()+"' AND course_id = '"+courseDetails.getCourseid()+"'";
            System.out.println(query);
            Statement st = DBConnector.getStatement();
            int j = st.executeUpdate(query);
            if(j>0)
            {
                return true;
            }
            return false;
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return false;
    }
}
