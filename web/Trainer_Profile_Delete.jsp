<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        String trainerid = (String)session.getAttribute("trainerid");
        if(trainerid == null)
        {
            response.sendRedirect("trainer_login.html");
            return;
        }
        String trainername = (String)session.getAttribute("trainername");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Trainer Profile | EmpowerHer</title>
    </head>
    <body>
        <h1>Hello <%=trainername%>!</h1>
        <h3>Are you sure you want to delete your profile?</h3>
        <form action="TrainerProfile_Delete" method="post">
            <tr>
                <td><input type="button" value="No" onclick="location.href='Trainer_Home_Page.jsp'"></td>
                <td><input type="Submit" value="Yes"></td>
            </tr>
        </form>
    </body>
</html>
