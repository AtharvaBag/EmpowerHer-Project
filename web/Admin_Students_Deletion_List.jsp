<%@page import="java.util.ArrayList"%>
<%@page import="dto.Student_DTO"%>
<%@page import="dto.Course_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
            if(session.getAttribute("isAdminVerified")==null || session.getAttribute("deletionStudentsList")==null || session.getAttribute("courseDetails")==null)
            {
                response.sendRedirect("home.html");
                return;
            }
            if(!(boolean)session.getAttribute("isAdminVerified"))
            {
                response.sendRedirect("home.html");
                return;
            }
            Course_DTO courseDetails = (Course_DTO)session.getAttribute("courseDetails");
            ArrayList <Student_DTO> deletionStudentsList = new ArrayList<>((ArrayList)session.getAttribute("deletionStudentsList"));
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EmpowerHer | Admin Students Deletion List</title>
    </head>
    <body>
        <h2>Course Name: <%=courseDetails.getCoursetitle()%></h2>
        <h2>Course Id: <%=courseDetails.getCourseid()%></h2>
        
        <table>
            <tr>
                <td>Student Id:</td>
                <td>Student Name:</td>
                <td>Action</td>
            </tr>
            <%
            if(!deletionStudentsList.isEmpty())
            {
                for(int i=0; i<deletionStudentsList.size(); i++)
                {
            %>
                <form action="Admin_Deletion_Student_Details" method="POST">
                        <tr>
                            <td><input type="text" value="<%=deletionStudentsList.get(i).getStudentid()%>" name="studentid" readonly></td>
                            <td><input type="text" value="<%=deletionStudentsList.get(i).getStudentname() %>" name="studentname" readonly></td>
                            <td><input type="Submit" value="Student Details"></td>
                        </tr>
                </form>
            <%
                }
            }
            else
            {
            %>
            <tr>
                <td><input type="text" value="No Data Found" readonly></td>
                <td><input type="text" value="No Data Found" readonly></td>
                <td></td>
            </tr>
            <%
            }
            %>
        </table>
        
    </body>
</html>
