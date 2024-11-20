<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        String trainerskill = (String)session.getAttribute("trainerskill");
        if(trainerskill==null)
        {
            response.sendRedirect("trainer_login.html");
            return;
        }
            
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EmpowerHer | Trainer Course Registration</title>
    </head>
    <body>
        <h1>Trainer Course Registration</h1>
        <h2>Enter the following details related to your course: </h2>
        <form action="TrainerCourse_Register" method="post">
            <table>
             <%
            if(trainerskill.trim().equals("+ Add Your Own Skill"))
            {
        %>
                        <h3>Register your unique skill here: </h3>
        
                        <td>Name of your skill: </td>
                        <td><input type="text" name="newskill" id="newskill" required="required"></td>
        
        <%
            }
            else
            {
        %>
                        <h3>Your Selected Skill: <%=trainerskill%></h3>
        <%
            }
        %>
       
                <tr>
                    <td>Enter the name of your institute/class: </td>
                    <td><input type="text" name="courseclassname" id="courseclassname" required="required"></td>
                </tr>
                 <tr>
                    <td>Enter the contact number of your institute/class: </td>
                    <td><input type="tel" name="courseclasscontact" id="courseclasscontact" required="required" pattern="[0-9]{10}" placeholder="10 digit contact number"></td>
                </tr>
                   <tr>
                    <td>Enter the title of your course: </td>
                    <td><input type="text" name="coursetitle" id="coursetitle" required="required"></td>
                </tr>
                <tr>
                    <td>Select target students </td>
                    <td>
                    <input TYPE="Radio" name="courseviewers" value="Beginner"><Label for="Beginner"> Beginner Level Students</Label>
                    </br>
                    <INPUT TYPE="Radio" name="courseviewers" value="Intermediate"><Label for="Intermediate"> Intermediate Level Students</Label>
                    </br>
                    <INPUT TYPE="Radio" name="courseviewers" value="Advance"><Label for="Advance"> Advance Level Students</Label>
                    </td>
                </tr>
                <tr>
                    <td>Enter duration of course: </td>
                    <td><input type="text" name="courseduration" id="courseduration" required="required" placeholder="in months"></td>
                </tr>
                <tr>
                    <td>Enter number of days of classes: </td>
                    <td><input type="tel" name="coursedays" id="coursedays" required="required" placeholder="per week" pattern="[1-7]{1}"></td>
                </tr>
                <tr>
                    <td>Select mode of class: </td>
                    <td>
                    <input TYPE="Radio" name="coursemode" value="Online"><Label for="Online"> Online</Label>
                    </br>
                    <INPUT TYPE="Radio" name="coursemode" value="Offline"><Label for="Offline"> Offline</Label>
                    </br>
                    <INPUT TYPE="Radio" name="coursemode" value="Hybrid"><Label for="Hybrid"> Hybrid (Online + Offline)</Label>
                    </td>
                </tr>
                <tr>
                    <td>Enter address of your institute/class: </td>
                    <td><input type="text" name="courseclassaddress" id="courseclassaddress" placeholder="if available"></td>
                </tr>
                <tr>
                    <td>Enter city of your institute/class: </td>
                    <td><input type="text" name="courseclasscity" id="courseclasscity" placeholder="if available"></td>
                </tr>
                <tr>
                    <td>Enter state of your institute/class: </td>
                    <td><input type="text" name="courseclassstate" id="courseclassstate" placeholder="if available"></td>
                </tr>
                <tr>
                    <td>Enter description of your course: </td>
                    <td><textarea name="coursedescription" id="coursedescription" rows="2" cols="20" placeholder="in about 100 words"></textarea></td>
                </tr>
                <tr>
                    <td>Provides course completion certificate? </td>
                    <td>
                     <INPUT TYPE="Radio" name="coursecertificate" value="1"><Label for="1"> Yes</Label>
                    </br>
                    <INPUT TYPE="Radio" name="coursecertificate" value="0"><Label for="0"> No</Label>
                    </td>
                </tr>
                <tr>
                    <td>Total fees of course: </td>
                    <td><input type="text" name="coursefee" id="coursefee" placeholder="in INR"></td>
                </tr>
                <tr>
                    <td><input type="reset" value="Reset"></td>
                <td><input type="submit" value="Register"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
