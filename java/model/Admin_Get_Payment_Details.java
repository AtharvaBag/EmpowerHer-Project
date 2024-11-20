package model;

import db.DBConnector;
import dto.Payment_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Admin_Get_Payment_Details {

    private String courseid;
    private String studentid;
    public Admin_Get_Payment_Details(String courseid, String studentid)
    {
        this.courseid = courseid;
        this.studentid = studentid;
    }
    
    public boolean isInputValid()
    {
        if(courseid.length()==0 && studentid.length()==0)
        {
            return false;
        }
        return true;
    }
    
    public ArrayList allPayments()
    {
        ArrayList <Payment_DTO> paymentDetailsList = new ArrayList<>();
        String query = "SELECT course_id, student_id, payment_mode, course_fee, payment_date_time FROM student_payment_table WHERE ";
        if(courseid.length()!=0 && studentid.length()!=0)
        {
            query = query + "course_id = '"+courseid+"' AND student_id = '"+studentid+"'";
        }
        else if(courseid.length()!=0 && studentid.length()==0)
        {
            query = query + "course_id = '"+courseid+"'";
        }
        else if(studentid.length()!=0 && courseid.length()==0)
        {
            query = query + "student_id = '"+studentid+"'";
        }
        System.out.println(query);
        try
        {
            Statement st = DBConnector.getStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                Payment_DTO paymentDetails = new Payment_DTO();
                paymentDetails.setCourseid(rs.getString(1));
                paymentDetails.setStudentid(rs.getString(2));
                paymentDetails.setPaymentmode(rs.getString(3));
                paymentDetails.setCoursefee(rs.getString(4));
                paymentDetails.setCurrentdatetime(rs.getString(5));
                paymentDetailsList.add(paymentDetails);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return paymentDetailsList;
    }
}
