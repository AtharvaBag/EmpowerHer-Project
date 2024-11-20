package model;

import db.DBConnector;
import dto.Course_DTO;
import dto.Payment_DTO;
import dto.Student_DTO;
import java.sql.SQLException;
import java.sql.Statement;

public class Validate_Payment {
    
    private Payment_DTO paymentDetails;
    private Course_DTO courseDetails;
    private Student_DTO studentDetails;
    
    public Validate_Payment(Payment_DTO paymentDetails, Course_DTO courseDetails, Student_DTO studentDetails)
    {
        this.paymentDetails= paymentDetails;
        this.courseDetails = courseDetails;
        this.studentDetails = studentDetails;
    }
    
    public boolean completeTransaction()
    {
        try
        {
            String query = "INSERT INTO student_payment_table (course_id, student_id, payment_mode, course_fee, payment_date_time) VALUES ('"+courseDetails.getCourseid()+"', '"+studentDetails.getStudentid()+"', '"+paymentDetails.getPaymentmode().toUpperCase()+"', '"+courseDetails.getCoursefee()+"', '"+paymentDetails.getCurrentdatetime()+"')";
            System.out.println(query);
            Statement st = DBConnector.getStatement();
            int i = st.executeUpdate(query);
            if(i>0)
            {
                System.out.println("Transaction Successful");
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
