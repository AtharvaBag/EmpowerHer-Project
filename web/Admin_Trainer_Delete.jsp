<%@page import="dto.Trainer_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
         if(session.getAttribute("trainerDetails")==null)
            {
                response.sendRedirect("home.html");
                return;
            }
           Trainer_DTO trainerDetails = (Trainer_DTO)session.getAttribute("trainerDetails");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EmpowerHer | Admin Trainer Deletion</title>
    </head>
    <body>
        <h2>Hello Admin!</h2>
        <p>Are you sure, you want to delete profile of <%=trainerDetails.getTrainername().trim()%> (Trainer ID: <%=trainerDetails.getTrainerid()%>), this will also delete all her courses from database!</p>
        <table>
            <form action="Admin_Trainer_Deletion" method="POST">
                <tr>
                    <td><input type="button" value="No" onclick="location.href='Admin_Trainer_Verification.jsp'"></td>
                    <td><input type="submit" value="Yes"></td>
                </tr>
            </form>
        </table>
    </body>
</html>
