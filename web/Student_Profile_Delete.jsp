<%@page import="dto.Student_DTO"%>
<%
        boolean isStudentLoggedIn = false;
        if(session.getAttribute("isStudentLoggedIn") != null)
        {
            isStudentLoggedIn = (boolean)session.getAttribute("isStudentLoggedIn");
        }
        if(!isStudentLoggedIn || session.getAttribute("studentDetails") == null)
        {
            response.sendRedirect("student_login.html");
            return;
        }
        Student_DTO studentDetails = (Student_DTO)session.getAttribute("studentDetails");

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EmpowerHer | Student Profile Delete</title>
    </head>
    <body>
        <h1>Hello <%=studentDetails.getStudentname()%>!</h1>
        <p>Are you sure? You want to delete your profile. This will deregister you from all your courses!</p>
        <form action="Student_Profile_Deletion" method="post">
            <table>
                <tr>
                    <td><button type="button" value="No" onclick="location.href='Student_Home.jsp'">No</button></td>
                    <td><input type="submit" value="Yes"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
