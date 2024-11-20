<%@page import="dto.Course_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        if(!(session.getAttribute("courseid")!=null && session.getAttribute("isAdminVerified")!=null && session.getAttribute("courseDetails")!=null))
        {
            response.sendRedirect("home.html");
            return;
        };
        if(!(boolean)session.getAttribute("isAdminVerified"))
        {
             response.sendRedirect("home.html");
             return;
        }
        String courseid = (String)session.getAttribute("courseid");
        Course_DTO courseDetails = (Course_DTO)session.getAttribute("courseDetails");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EmpowerHer | Admin Delete Course</title>
    </head>
    <body>
        <h1>Dear Admin!</h1>
        <p>Are you sure you want to delete course, <%=courseDetails.getCoursetitle().toUpperCase()%> (Course ID: <%=courseid%>)</p>
        <table>
            <form action="Admin_Course_Deletion" method="POST">
                <tr>
                    <td><input type="button" onclick="location.href='Admin_Course_Verification.jsp'" value="No"></td>
                    <td><input type="submit" value="Yes"></td>
                </tr>
            </form>
        </table>
    </body>
</html>
