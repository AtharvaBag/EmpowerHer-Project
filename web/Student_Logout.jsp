<%
        
        boolean isStudentLoggedIn = false;
               if(session.getAttribute("isStudentLoggedIn") != null)
               {
                   isStudentLoggedIn = (boolean)session.getAttribute("isStudentLoggedIn");
               }
               if(!isStudentLoggedIn)
               {
                   response.sendRedirect("student_login.html");
                   return;
               }

               isStudentLoggedIn = false;
               session.invalidate();
               response.sendRedirect("home.html");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
