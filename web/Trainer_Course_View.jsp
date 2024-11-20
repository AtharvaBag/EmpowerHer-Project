<%@page import="dto.Course_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        String isCourseVerified = (String)session.getAttribute("isCourseVerified");
        Course_DTO registeredCourseDetails = (Course_DTO)session.getAttribute("registeredCourseDetails");
        if(registeredCourseDetails==null)
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
        <title>EmpowerHer | Trainer Course Details</title>
    </head>
    <body>
        <h1>Hello <%=trainername.toUpperCase()%>!</h1>
        <h2>Your Course Details</h2>
        <h2>Course ID: <%=registeredCourseDetails.getCourseid()%></h2>
        <h3>Verification Status: <% if(isCourseVerified=="1") {%> Verified and Registered <% } else if(isCourseVerified=="0") { %> Registered, under verification process <%}%> </h3>
        
        <a href="Trainer_Course_Delete.jsp">Delete Course</a>
        
        <form method="Post" action="Trainer_Course_Enrolled_Students">
            <input type="submit" value="Enrolled Students"></input>
        </form>
        
        <br>
        <br>
        
        <table>
            <form method="post" action="TrainerCourse_Update">
            <tr>
                <td>Skill Domain: </td>
                <td><input type="text" value="<%=registeredCourseDetails.getTrainerskill()%>" name="updatedcourseskill" id="updatedcourseskill"></td>
            </tr>
            <tr>
                <td>Name of class/institute: </td>
                <td><input type="text" value="<%=registeredCourseDetails.getCourseclassname()%>" name="updatedcourseclassname" id="updatedcourseclassname"></td>
            </tr>
            <tr>
                <td>Contact number of class/institute: </td>
                <td><input type="text" value="<%=registeredCourseDetails.getCourseclasscontact()%>" name="updatedcourseclasscontact" id="updatedcourseclasscontact"></td>
            </tr>
            <tr>
                <td>Title of course: </td>
                <td><input type="text" value="<%=registeredCourseDetails.getCoursetitle() %>" name="updatedcoursetitle" id="updatedcoursetitle"></td>
            </tr>
             <tr>
                    <td>Target Students: </td>
                    <td>
                    <input TYPE="Radio" name="updatedcourseviewers" value="Beginner" <%if(registeredCourseDetails.getCourseviewers().equalsIgnoreCase("beginner")){ %> checked="true" <%}%>><Label for="Beginner" > Beginner Level Students</Label>
                    </br>
                    <INPUT TYPE="Radio" name="updatedcourseviewers" value="Intermediate" <%if(registeredCourseDetails.getCourseviewers().equalsIgnoreCase("intermediate")){ %> checked="true" <%}%>><Label for="Intermediate" > Intermediate Level Students</Label>
                    </br>
                    <INPUT TYPE="Radio" name="updatedcourseviewers" value="Advance" <%if(registeredCourseDetails.getCourseviewers().equalsIgnoreCase("advance")){ %> checked="true" <%}%>><Label for="Advance" > Advance Level Students</Label>
                    </td>
            </tr>
            <tr>
                <td>Course Duration (in months): </td>
                <td><input type="text" value="<%=registeredCourseDetails.getCourseduration() %>" name="updatedcourseduration" id="updatedcourseduration" placeholder="in months"></td>
            </tr>
            <tr>
                <td>Number of class days (per week): </td>
                <td><input type="text" value="<%=registeredCourseDetails.getCoursedays() %>" name="updatedcoursedays" id="updatedcoursedays"></td>
            </tr>
             <tr>
                   <td>Select mode of class: </td>
                   <td>
                   <input TYPE="Radio" name="updatedcoursemode" value="Online" <%if(registeredCourseDetails.getCoursemode().equalsIgnoreCase("Online")){ %> checked="true" <%}%>><Label for="Online"> Online</Label>
                   </br>
                   <INPUT TYPE="Radio" name="updatedcoursemode" value="Offline" <%if(registeredCourseDetails.getCoursemode().equalsIgnoreCase("Offline")){ %> checked="true" <%}%>><Label for="Offline"> Offline</Label>
                   </br>
                   <INPUT TYPE="Radio" name="updatedcoursemode" value="Hybrid" <%if(registeredCourseDetails.getCoursemode().equalsIgnoreCase("Hybrid")){ %> checked="true" <%}%>><Label for="Hybrid"> Hybrid (Online + Offline)</Label>
                   </td>
             </tr>
             <tr>
                    <td>Address of your institute/class: </td>
                    <td><input type="text" name="updatedcourseclassaddress" id="updatedcourseclassaddress" placeholder="if available" value="<%=registeredCourseDetails.getCourseclassaddress() %>"></td>
                </tr>
                <tr>
                    <td>City of your institute/class: </td>
                    <td><input type="text" name="updatedcourseclasscity" id="updatedcourseclasscity" placeholder="if available" value="<%=registeredCourseDetails.getCourseclasscity() %>"></td>
                </tr>
                <tr>
                    <td>State of your institute/class: </td>
                    <td><input type="text" name="updatedcourseclassstate" id="updatedcourseclassstate" placeholder="if available" value="<%=registeredCourseDetails.getCourseclassstate() %>"></td>
                </tr>
                <tr>
                    <td>Description of your course: </td>
                    <td><textarea name="updatedcoursedescription" id="updatedcoursedescription" rows="2" cols="20" placeholder="in about 100 words"><%=registeredCourseDetails.getCoursedescription() %></textarea></td>
                </tr>
                <tr>
                    <td>Provides course completion certificate? </td>
                    <td>
                        <INPUT TYPE="Radio" name="updatedcoursecertificate" value="1" <%if(registeredCourseDetails.getCoursecertification().equals("1")){ %> checked="true" <%}%>><Label for="1"> Yes</Label>
                    </br>
                    <INPUT TYPE="Radio" name="updatedcoursecertificate" value="0" <%if(registeredCourseDetails.getCoursecertification().equals("0")){ %> checked="true" <%}%>><Label for="0"> No</Label>
                    </td>
                </tr>
                <tr>
                    <td>Total fees of course: </td>
                    <td><input type="text" name="updatedcoursefee" id="updatedcoursefee" placeholder="in INR" value="<%=registeredCourseDetails.getCoursefee() %>"></td>
                </tr>
                <tr>
                    <td><input type="reset" value="Reset"></td>
                <td><input type="submit" value="Update"></td>
                </tr>
            </form>
        </table>
        <%
            if(isCourseVerified=="1")
            {
        %>
        <h3>*If you will update course details, then you will loose verified status and re-verification process will start!</h3>
        <%
            }
        %>
    </body>
</html>
