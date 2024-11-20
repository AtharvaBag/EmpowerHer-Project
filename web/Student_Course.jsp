<%@page import="dto.Payment_DTO"%>
<%@page import="dto.Course_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        Course_DTO courseDetails = (Course_DTO)session.getAttribute("courseDetails");
        String studentcourseid = (String)session.getAttribute("studentcourseid");
        boolean isStudentEnrolled = (boolean)session.getAttribute("isStudentEnrolled");
        if(courseDetails == null || studentcourseid == null)
        {
            response.sendRedirect("home.html");
            return;
        }
         boolean showPaymentDetailsToStudent = false;
         Payment_DTO paymentDetails = new Payment_DTO();
        if(session.getAttribute("showPaymentDetailsToStudent")!=null)
        {
           showPaymentDetailsToStudent = (boolean)session.getAttribute("showPaymentDetailsToStudent");
           paymentDetails = (Payment_DTO)session.getAttribute("paymentDetails");
        }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EmpowerHer | Course Details</title>
    </head>
    <body>
        <h2>Details of course: <%=courseDetails.getCoursetitle().trim()%></h2>
        
        <table>
            <form method="post" action="Payment_Page.jsp">
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
        <%
            if(isStudentEnrolled)
            {
        %>
        
            <td><input type="button" value="Ok" onclick="location.href='Student_Home.jsp'"></td>
        <%
            }
            else
            {
        %>
                              <td><input type="Submit" value="Enroll Now!"></td>
        <%
        }
        %>
                </tr>
              </form>
          <%
              if(!isStudentEnrolled)
              {
          %>
            <form action="Remove_From_Wishlist" method="POST">
                <td><input type="submit" value="Remove From Wishlist"></td>
            </form>
            <%
            }
            %>
        </table>
        <%
            if(isStudentEnrolled)
            {
        %>
        <form method="POST" action="Student_Payment_Details">
               <input type="submit" value="Payment Details">
           </form>
           <%
            }
           if(showPaymentDetailsToStudent  && isStudentEnrolled)
           {
           %>
           <table>
               <tr>
                   <td>Payment Mode:</td>
                   <td><input type="text" value="<%=paymentDetails.getPaymentmode()%>" readonly></td>
               </tr>
               <tr>
                   <td>Transaction Date and Time:</td>
                   <td><input type="text" value="<%=paymentDetails.getCurrentdatetime()%>" readonly></td>
               </tr>
               <tr>
                   <td>Amount Paid:</td>
                   <td><input type="text" value="<%=paymentDetails.getCoursefee()%>/-" readonly></td>
               </tr>
           </table>
           <%
           }
           %>
    </body>
</html>
