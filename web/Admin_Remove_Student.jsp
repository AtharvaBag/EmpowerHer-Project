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
        <title>EmpowerHer | Admin Remove Student</title>
    </head>
    <body>
        <h2>Hello Admin!</h2>
        <p>Are you sure you want to remove student, <%=studentDetails.getStudentname()%> (Student ID: <%=studentDetails.getStudentid()%>) from course <%=courseDetails.getCoursetitle()%> (Course ID: <%=courseDetails.getCourseid()%>))</p>
        <table>
            <form method="POST" action="Admin_Remove_Student">
                <tr>
                    <td><input type="button" value="No" onclick="location.href='Admin_Students_Deletion_List.jsp'"></td>
                    <td><input type="submit" value="Yes"></td>
                </tr>
            </form>
        </table>
    </body>
</html>
