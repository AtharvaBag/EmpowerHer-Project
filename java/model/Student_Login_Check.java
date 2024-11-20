package model;

import db.DBConnector;
import dto.Course_DTO;
import dto.Student_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Student_Login_Check {
    
    private Student_DTO studentLoginDetails;
    private static ArrayList <Course_DTO> studentEnrolledCourseDetails = new ArrayList<>();
    private static ArrayList <Course_DTO> studentWishlistCourseDetails = new ArrayList<>();
    public Student_Login_Check(Student_DTO studentLoginDetails)
    {
        this.studentLoginDetails = studentLoginDetails;
    }
    
    public boolean studentAuthentication()
    {
        if(studentLoginDetails.getStudentcontact().trim().equals("") || studentLoginDetails.getStudentpassword().trim().equals(""))
        {
            return false;
        }
        try
        {
            Statement st = DBConnector.getStatement();
            String table_password = new String();
            ResultSet rs = st.executeQuery("SELECT student_password  FROM student_table WHERE student_contact='"+studentLoginDetails.getStudentcontact().trim()+"'");
            if(rs.next())
            {
                table_password = rs.getString(1);
            }
            if(table_password.equals(studentLoginDetails.getStudentpassword()))
            {
                rs = st.executeQuery("SELECT student_name, student_id FROM student_table WHERE student_contact='"+studentLoginDetails.getStudentcontact().trim()+"'");
                if(rs.next())
                {
                    studentLoginDetails.setStudentname(rs.getString(1));
                    studentLoginDetails.setStudentid(rs.getString(2));
                }
                return true;
            }
            
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    public ArrayList enrolledCourses()
    {
        studentEnrolledCourseDetails.clear();
        try
        {
            String query = "SELECT course_id FROM student_course_table WHERE student_id='"+studentLoginDetails.getStudentid().trim()+"' AND enrolled='1'";
            Statement st = DBConnector.getStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                Course_DTO enrolledCourseDetails = new Course_DTO();
                enrolledCourseDetails.setCourseid(rs.getString(1).trim());
                studentEnrolledCourseDetails.add(enrolledCourseDetails);
            }
            
            if(!studentEnrolledCourseDetails.isEmpty())
            {
                for(int i=0; i<studentEnrolledCourseDetails.size(); i++)
                {
                    String query2 = "SELECT course_title FROM course_table WHERE course_id='"+studentEnrolledCourseDetails.get(i).getCourseid()+"'";
                    rs = st.executeQuery(query2);
                    if(rs.next())
                    {
                        studentEnrolledCourseDetails.get(i).setCoursetitle(rs.getString(1));
                    }
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return studentEnrolledCourseDetails;
    }
    
    public ArrayList wishlistCourses()
    {
        studentWishlistCourseDetails.clear();
        try
        {
             String query = "SELECT course_id FROM student_course_table WHERE student_id='"+studentLoginDetails.getStudentid().trim()+"' AND enrolled='0'";
             Statement st = DBConnector.getStatement();
             ResultSet rs = st.executeQuery(query);
             while(rs.next())
             {
                 Course_DTO wishlistCourseDetails = new Course_DTO();
                 wishlistCourseDetails.setCourseid(rs.getString(1).trim());
                 studentWishlistCourseDetails.add(wishlistCourseDetails);
             }
             
             if(!studentWishlistCourseDetails.isEmpty())
             {
                 for(int i=0; i<studentWishlistCourseDetails.size(); i++)
                 {
                     String query2 = "SELECT course_title FROM course_table WHERE course_id='"+studentWishlistCourseDetails.get(i).getCourseid()+"'";
                     rs = st.executeQuery(query2);
                     if(rs.next())
                     {
                         studentWishlistCourseDetails.get(i).setCoursetitle(rs.getString(1));
                     }
                 }
             }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return studentWishlistCourseDetails;
    }
}
