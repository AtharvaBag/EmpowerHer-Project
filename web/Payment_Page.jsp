<%@page import="java.util.ArrayList"%>
<%@page import="dto.Course_DTO"%>
<%@page import="dto.Student_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        Student_DTO studentDetails = (Student_DTO)session.getAttribute("studentDetails");
        Course_DTO courseDetails = (Course_DTO)session.getAttribute("courseDetails");
        System.out.println(courseDetails.getCourseid());
        System.out.println(studentDetails.getStudentid());
        
        if(courseDetails == null || studentDetails == null)
        {
            System.out.println("Redirecting from Payment_Page");
            response.sendRedirect("home.html");
            return;
        }
        
        
        %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EmpowerHer | Payment Page</title>
    </head>
    <body>
        <h1>Dear <%=studentDetails.getStudentname().toUpperCase()%></h1>
        <%
            boolean flag = false;
            if(session.getAttribute("studentEnrolledCourseDetails") != null)
            {
                ArrayList <Course_DTO> studentEnrolledCourseDetails = new ArrayList<>((ArrayList) session.getAttribute("studentEnrolledCourseDetails"));
                for(int i=0; i<studentEnrolledCourseDetails.size(); i++)
                {
                     if(studentEnrolledCourseDetails.get(i).getCourseid().equals(courseDetails.getCourseid()))
                    {
                        flag = true;
                        break;
                    }
                }
            }
            if(flag)
            {
                %>
                <H2>You are already enrolled in this course, <%=courseDetails.getCoursetitle().trim() %></H2>
                <%
            }
            
        else 
        {
%>
      
         <table>
            <tr>
                <td>Selected Course Name: </td>
                <td><%=courseDetails.getCoursetitle()%></td>
            </tr>
            <tr>
                <td>Total Course Fee: </td>
                <td>Rs. <%=courseDetails.getCoursefee().trim()%></td>
            </tr>
        </table>
       <h2>Select a payment mode!</h2>
     
     <table>  
            <td>
            <table>
            <form action="Enroll_To_Course" method="POST">
            <tr>
                <td><input TYPE="Radio" name="paymentmode"  value="creditcard" required><Label for="creditcard"> Credit Card</Label></td>
            </tr>
            <tr>
               <td>Enter your Credit Card number: </td>
               <td><input type="tel"pattern="[0-9]{16}" placeholder="16 digit number" required><td>
            </tr> 
            <tr>
               <td>Enter your Credit Card CVV: </td>
               <td><input type="tel"pattern="[0-9]{3}" placeholder="3 digit number" required><td>
            </tr>
            <tr>
               <td>Enter your Credit Card PIN: </td>
               <td><input type="password"pattern="[0-9]{4}" required><td>
            </tr>
            <tr>
                <td></td>
               <td><input type="Submit" value="Confirm Payment"></td>
            </tr>
            </form>
                <form method="POST" action="Enroll_To_Course">
                <tr>
                    <td><input TYPE="Radio" name="paymentmode" value="netbanking" required><Label for="netbanking"> Net Banking</Label></td>
                </tr>
                <tr>
                    <td>Enter your Bank Account number: </td>
                    <td><input type="tel"pattern="[0-9]{16}" required placeholder="16 digit account number"><td>
                </tr> 
                <tr>
                    <td>Enter your Bank name: </td>
                    <td><input type="text" required><td>
                 </tr>
                 <tr>
                     <td>Enter your user id: </td>
                     <td><input type="text" required><td>
                    </tr>
                    <tr>
                         <td>Enter your password: </td>
                         <td><input type="password" required><td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Confirm Payment"></td>
                    </tr>
                  </form>
                 <form action="Enroll_To_Course" method="POST">
                <tr>
                    <td><input TYPE="Radio" name="paymentmode" value="upipayment" required><Label for="upipayment"> UPI Payment</Label></td>
                </tr>   
                <tr>
                    <td>Enter your UPI Id: </td>
                    <td><input type="text" required><td>
                </tr> 
                <tr>
                    <td>Enter your UPI pin: </td>
                    <td><input type="password"required><td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="Submit" value="Confirm Payment"></td>
                </tr>
                </from>
                </table>
                    </td>
             </table>
       <%
       }
       %>
    </body>
</html>
