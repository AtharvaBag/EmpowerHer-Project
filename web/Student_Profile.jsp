<%@page import="dto.Student_DTO"%>
<%
        boolean isStudentLoggedId = false;
         Student_DTO studentDetails = null;
        if(session.getAttribute("isStudentLoggedIn") != null)
        {
            isStudentLoggedId = (boolean)session.getAttribute("isStudentLoggedIn");
             studentDetails = (Student_DTO)session.getAttribute("studentDetails");
        }
        if(!isStudentLoggedId || studentDetails==null)
        {
            response.sendRedirect("student_login.html");
            return;
        }
       
        
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EmpowerHer | Student Profile</title>
    </head>
    <body>
        <h1>Student Details</h1>
        <!--<h2>Student Id: <%=studentDetails.getStudentid()%></h2>-->
        <table>
            <form action="Student_profile_update" method="POST">
                <tr>
                    <td>Your Name: </td>
                    <td><input type="text" value="<%=studentDetails.getStudentname()%>" name="studentname" id="studentname"></td>
                </tr>
                <tr>
                    <td>Contact Number: </td>
                    <td><input type="text" value="<%=studentDetails.getStudentcontact()%>" name="studentcontact" id="studentcontact"></td>
                </tr>
                <tr>
                    <td>Email Id: </td>
                    <td><input type="text" value="<%=studentDetails.getStudentmailid()%>" name="studentmailid" id="studentmailid"></td>
                </tr>
                <tr>
                    <td><input type="reset" value="Reset"></td>
                    <td><input type="Submit" value="Update Profile"></td>
                </tr>
            </form>
        </table>
                <a href="Student_Profile_Delete.jsp">Delete Profile</a>
    </body>
</html>
