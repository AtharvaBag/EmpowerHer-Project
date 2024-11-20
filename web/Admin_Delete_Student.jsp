<%@page import="dto.Course_DTO"%>
<%@page import="dto.Student_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        if(session.getAttribute("isAdminVerified")==null || session.getAttribute("studentDetails")==null || session.getAttribute("courseDetails")==null)
        {
            response.sendRedirect("home.html");
            return;
        }
        if(!(boolean)session.getAttribute("isAdminVerified"))
        {
            response.sendRedirect("home.html");
            return;
        }
        Student_DTO studentDetails = (Student_DTO)session.getAttribute("studentDetails");
        Course_DTO courseDetails = (Course_DTO)session.getAttribute("courseDetails");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EmpowerHer | Admin Delete Student</title>
    </head>
    <body>
        <h2>Student Id: <%=studentDetails.getStudentid()%></h2>
        <h2>Enrolled in <%=courseDetails.getCoursetitle()%> (Course ID: <%=courseDetails.getCourseid()%>)</h2>
        <table>
            <form action="Admin_Cancel_Student_Deletion" method="POST">
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
                    <td><input type="Submit" value="Cancel Deletion"></td>
                    <td><input type="Button" value="Remove From Course" onclick="location.href='Admin_Remove_Student.jsp'"></td>
                </tr>
            </form>
        </table>
    </body>
</html>
