<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
            String trainername = (String)session.getAttribute("trainername");
            String trainerid = (String)session.getAttribute("trainerid");
            String trainercourseid = (String)session.getAttribute("trainercourseid");
            String trainercoursename = (String)session.getAttribute("trainercoursename");
            
            if(trainername==null || trainerid==null || trainercourseid==null || trainercoursename==null)
            {
                response.sendRedirect("trainer_login.html");
                return;
            }
            
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EmpowerHer | Delete Course</title>
    </head>
    <body>
        <h1>Hello <%=trainername.toUpperCase()%>!</h1>
        <p>Are you sure you want to delete your course, <%=trainercoursename.toUpperCase()%> (Course Id: <%=trainercourseid%>)</p>
        <table>
            <form method="post" action="TrainerCourse_Delete">
                <tr>
                    <td><input type="button" value="No" onclick="location.href='Trainer_Home_Page.jsp'"></td>
                    <td><input type="submit" value="Yes"></td>
                </tr>
            </form>
        </table>
    </body>
</html>
