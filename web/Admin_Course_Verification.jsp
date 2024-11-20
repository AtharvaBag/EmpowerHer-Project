<%@page import="dto.Course_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        if(session.getAttribute("courseDetails")==null || session.getAttribute("courseverified")==null)
        {
            response.sendRedirect("home.html");
        }
        boolean trainerverified = false;
        if(session.getAttribute("trainerverified")!=null)
        {
            trainerverified = (boolean)session.getAttribute("trainerverified");
        }
        Course_DTO courseDetails =  (Course_DTO)session.getAttribute("courseDetails");
        boolean courseverified = (boolean)session.getAttribute("courseverified");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EmpowerHer | Admin Course Details</title>
    </head>
    <body>
        <h2>Trainer Name: <%=(String)session.getAttribute("trainername")%> (Trainer ID: <%=(String)session.getAttribute("trainerid")%>)</h2>
        <%
            if(courseverified)
            {
        %>
        <h3>Course is verified!</h3>
        <%
            }
            else
            {
        %>
        <h3>Course is not verified!</h3>
        <%
            }
        %>
        <h3>Course ID: <%=(String)session.getAttribute("courseid")%></h3>
        <a href="Admin_Home.jsp">Home</a>
        <table>
            <form method="post" action="Admin_Course_Validation">
            <tr>
                <td>Skill Domain: </td>
                <td><input type="text" value="<%=courseDetails.getTrainerskill()%>" name="courseskill" id="courseskill" readonly></td>
            </tr>
            <tr>
                <td>Name of class/institute: </td>
                <td><input type="text" value="<%=courseDetails.getCourseclassname()%>" name="courseclassname" id="courseclassname" readonly></td>
            </tr>
            <tr>
                <td>Contact number of class/institute: </td>
                <td><input type="text" value="<%=courseDetails.getCourseclasscontact()%>" name="courseclasscontact" id="courseclasscontact" readonly></td>
            </tr>
            <tr>
                <td>Title of course: </td>
                <td><input type="text" value="<%=courseDetails.getCoursetitle() %>" name="coursetitle" id="coursetitle" readonly></td>
            </tr>
             <tr>
                    <td>Course Level: </td>
                    <td>
                    <input TYPE="Radio" name="courseviewers" value="Beginner" disabled="true"<%if(courseDetails.getCourseviewers().equalsIgnoreCase("beginner")){ %> checked="true"<%}%>><Label for="Beginner" > Beginner Level Students</Label>
                    </br>
                    <INPUT TYPE="Radio" name="courseviewers" value="Intermediate" disabled="true"<%if(courseDetails.getCourseviewers().equalsIgnoreCase("intermediate")){ %> checked="true" <%}%>><Label for="Intermediate" > Intermediate Level Students</Label>
                    </br>
                    <INPUT TYPE="Radio" name="courseviewers" value="Advance" disabled="true"<%if(courseDetails.getCourseviewers().equalsIgnoreCase("advance")){ %> checked="true" <%}%>><Label for="Advance" > Advance Level Students</Label>
                    </td>
            </tr>
            <tr>
                <td>Course Duration (in months): </td>
                <td><input type="text" value="<%=courseDetails.getCourseduration() %>" name="courseduration" id="courseduration" placeholder="in months" readonly></td>
            </tr>
            <tr>
                <td>Number of class days (per week): </td>
                <td><input type="text" value="<%=courseDetails.getCoursedays() %>" name="coursedays" id="coursedays" readonly></td>
            </tr>
             <tr>
                   <td>Mode of class: </td>
                   <td>
                   <input TYPE="Radio" name="coursemode" value="Online" disabled="true"<%if(courseDetails.getCoursemode().equalsIgnoreCase("Online")){ %> checked="true" <%}%>><Label for="Online"> Online</Label>
                   </br>
                   <INPUT TYPE="Radio" name="coursemode" value="Offline" disabled="true"<%if(courseDetails.getCoursemode().equalsIgnoreCase("Offline")){ %> checked="true" <%}%>><Label for="Offline"> Offline</Label>
                   </br>
                   <INPUT TYPE="Radio" name="coursemode" value="Hybrid" disabled="true"<%if(courseDetails.getCoursemode().equalsIgnoreCase("Hybrid")){ %> checked="true" <%}%>><Label for="Hybrid"> Hybrid (Online + Offline)</Label>
                   </td>
             </tr>
             
             <%
                    if(!courseDetails.getCoursemode().equalsIgnoreCase("Online"))
                    {
             %>
             <tr>
                    <td>Address of institute/class: </td>
                    <td><input type="text" name="courseclassaddress" id="courseclassaddress" placeholder="if available" value="<%=courseDetails.getCourseclassaddress() %>"></td>
                </tr>
                <tr>
                    <td>City of institute/class: </td>
                    <td><input type="text" name="courseclasscity" id="courseclasscity" placeholder="if available" value="<%=courseDetails.getCourseclasscity() %>"></td>
                </tr>
                <tr>
                    <td>State of institute/class: </td>
                    <td><input type="text" name="updatedcourseclassstate" id="updatedcourseclassstate" placeholder="if available" value="<%=courseDetails.getCourseclassstate() %>"></td>
                </tr>
                <%
                }
                %>
                <tr>
                    <td>Course Description: </td>
                    <td><textarea name="coursedescription" id="coursedescription" rows="2" cols="20" placeholder="in about 100 words" readonly><%=courseDetails.getCoursedescription() %></textarea></td>
                </tr>
                <tr>
                    <td>Provides course completion certificate? </td>
                    <td>
                        <INPUT TYPE="Radio" name="coursecertificate" value="1" disabled="true" <%if(courseDetails.getCoursecertification().equals("1")){ %> checked="true" <%}%>><Label for="1"> Yes</Label>
                    </br>
                    <INPUT TYPE="Radio" name="coursecertificate" value="0" disabled="true" <%if(courseDetails.getCoursecertification().equals("0")){ %> checked="true" <%}%>><Label for="0"> No</Label>
                    </td>
                </tr>
                <tr>
                    <td>Total fees of course: </td>
                    <td><input type="text" name="coursefee" id="coursefee" placeholder="in INR" value="<%=courseDetails.getCoursefee() %>" readonly></td>
                </tr>
                <tr>
                    <td><input type="button" value="Delete Course" onclick="location.href='Admin_Delete_Course.jsp'">
                <%
                    if(courseverified)
                    {
                %>        
                    <td><input type="Submit" value="Invalidate Course"></td>
                <%
                    }
                    else
                    {
                %>
                    <td><input type="Submit" value="Validate Course" <%if(!trainerverified){%> disabled="true"<%}%>></td>
               <%
                     }
               %>
                </tr>
                <%
                if(!trainerverified)
                {
                %>
                <td colspan="2">Trainer is not verified, please first verify the trainer to validate this course!</td>
                <%
                }
                %>
    </body>
</html>
