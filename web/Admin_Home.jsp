<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="dto.Course_DTO"%>
<%@page import="dto.Trainer_DTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
       if(session.getAttribute("isAdminVerified") != null)
       {
            boolean isAdminVerified = (boolean)session.getAttribute("isAdminVerified");
            if(!isAdminVerified)
            {
                response.sendRedirect("home.html");
                return;
            }
        }
       else
       {
                response.sendRedirect("home.html");
                return;
       }
       boolean trainerverificationlist = false;
       boolean courseverificationlist = false;
       boolean studentdeletionrequests = false;
       
       if(session.getAttribute("trainerverificationlist") != null)
       {
           trainerverificationlist = (boolean)session.getAttribute("trainerverificationlist");
       }
       if(session.getAttribute("courseverificationlist") != null)
       {
           courseverificationlist = (boolean)session.getAttribute("courseverificationlist");
       }
       if(session.getAttribute("studentdeletionrequests") != null)
       {
           studentdeletionrequests = (boolean)session.getAttribute("studentdeletionrequests");
       }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EmpowerHer | Admin Page</title>
    </head>
    <body>
        <h1>Welcome Admin!</h1>
        <a href="Admin_Logout.jsp">Logout</a>
        <a href="Admin_Payment_Details.jsp">Payment Details</a>
        
        
        <br>
        <br>
        <form action="Admin_Options_Selector" method="POST">
            <input type="Submit" value="Teacher Verification List" name="adminoption">
        </form>
        <br>
        
        <form action="Admin_Options_Selector" method="POST">
            <input type="Submit" value="Course Verification List" name="adminoption">
        </form>
        <br>
        
        <form action="Admin_Options_Selector" method="POST">
            <input type="Submit" value="Student Deletion Requests" name="adminoption">
        </form>
        <br>
        
        
        <%
                if(trainerverificationlist)
                {
        %>
                    <H2>Trainer Verification List</H2>
                    <H3>Verified Trainers</H3>
                    <table>
                        <tr>
                            <td>Trainer ID</td>
                            <td>Trainer Name</td>
                        </tr>
          <%
                    ArrayList <Trainer_DTO> verifiedTrainers = new ArrayList <>((ArrayList)session.getAttribute("verifiedTrainers"));
                    if(!verifiedTrainers.isEmpty())
                    {
                        for(int i=0; i<verifiedTrainers.size(); i++)
                        {
          %>          
                        <form action="Admin_Trainer_Details" method="POST">
                            <tr>
                                <td><input type="text" value="<%=verifiedTrainers.get(i).getTrainerid()%>" name="verifiedtrainerid" readonly></td>
                                <td><input type="text" value="<%=verifiedTrainers.get(i).getTrainername()%>" name="verifiedtrainername" readonly></td>
                                <td><input type="submit" value="Show Details"></td>
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
                      </tr>
           <%   
                    }
            %>
                     </table> 
                    <H3>Unverified Trainers</H3>
            <table>
                        <tr>
                            <td>Trainer ID</td>
                            <td>Trainer Name</td>
                        </tr>
          <%
                    ArrayList <Trainer_DTO> unverifiedTrainers = new ArrayList <>((ArrayList)session.getAttribute("unverifiedTrainers"));
                    if(!unverifiedTrainers.isEmpty())
                    {
                        for(int i=0; i<unverifiedTrainers.size(); i++)
                        {
          %>          
                        <form action="Admin_Trainer_Details" method="POST">
                            <tr>
                                <td><input type="text" value="<%=unverifiedTrainers.get(i).getTrainerid()%>" name = "unverifiedtrainerid" readonly></td>
                                <td><input type="text" value="<%=unverifiedTrainers.get(i).getTrainername()%>" name = "unverifiedtrainername" readonly></td>
                                <td><input type="submit" value="Show Details"></td>
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
                      </tr>
           <%   
                    }
                }

            if(courseverificationlist)
            {
        %>
                    <H2>Course Verification List</H2>
                    <H3>Verified Courses</H3>
                    <table>
                         <tr>
                             <td>Course Id</td>
                             <td>Course Title</td>
                             <td>Trainer ID</td>
                             <td>Trainer Name</td>
                         </tr>
         <%
                    Map <Course_DTO, Trainer_DTO> verifiedCourseTrainer = new HashMap<>((Map)session.getAttribute("verifiedCourseTrainer"));
                    if(!verifiedCourseTrainer.isEmpty())
                    {
                        for(Map.Entry<Course_DTO, Trainer_DTO> entry : verifiedCourseTrainer.entrySet())
                        {
         %>          
                        <form action="Admin_Course_Details" method="POST">
                                <tr>
                                    <td><input type="text" value="<%=entry.getKey().getCourseid()%>" name="verifiedcourseid" readonly></td>
                                    <td><input type="text" value="<%=entry.getKey().getCoursetitle()%>" name="verifiedcoursename" readonly></td>
                                    <td><input type="text" value="<%=entry.getValue().getTrainerid()%>" name="verifiedtrainerid" readonly></td>
                                    <td><input type="text" value="<%=entry.getValue().getTrainername()%>" name="verifiedtrainername" readonly></td>
                                    <td><input type="Submit" value="Show Details"></td>
                                </tr>
                        </form>
                    </table>
        <%
                        }
                    }
                    else
                    {
         %>
                         <tr>
                                    <td><input type="text" value="No data Found" readonly></td>
                                    <td><input type="text" value="No data Found" readonly></td>
                                    <td><input type="text" value="No data Found" readonly></td>
                                    <td><input type="text" value="No data Found" readonly></td>
                         </tr>
                    </table>
                    <%
                    }
                    %>
                    <BR>
                    <BR>
                    <H3>Unverified Courses</H3>
                     <table>
                         <tr>
                             <td>Course Id</td>
                             <td>Course Title</td>
                             <td>Trainer ID</td>
                             <td>Trainer Name</td>
                         </tr>
         <%
                    Map <Course_DTO, Trainer_DTO> unverifiedCourseTrainer = new HashMap<>((Map)session.getAttribute("unverifiedCourseTrainer"));
                    if(!unverifiedCourseTrainer.isEmpty())
                    {
                        for(Map.Entry<Course_DTO, Trainer_DTO> entry : unverifiedCourseTrainer.entrySet())
                        {
         %>          
                        <form action="Admin_Course_Details" method="POST">
                                <tr>
                                    <td><input type="text" value="<%=entry.getKey().getCourseid()%>" name="unverifiedcourseid" readonly></td>
                                    <td><input type="text" value="<%=entry.getKey().getCoursetitle()%>" name="unverifiedcoursename" readonly></td>
                                    <td><input type="text" value="<%=entry.getValue().getTrainerid()%>" name="unverifiedtrainerid" readonly></td>
                                    <td><input type="text" value="<%=entry.getValue().getTrainername()%>" name="unverifiedtrainername" readonly></td>
                                    <td><input type="Submit" value="Show Details"></td>
                                </tr>
                        </form>
                     </table>
        <%
                        }
                    }
                    else
                    {
         %>
                    <tr>
                        <td><input type="text" value="No data found" readonly></td>
                        <td><input type="text" value="No data found" readonly></td>
                        <td><input type="text" value="No data found" readonly></td>
                        <td><input type="text" value="No data found" readonly></td>
                    </tr>
                  </table>
        <%
                    }
            }
            if(studentdeletionrequests)
                {
        %>
                    <H2>Student Deletion Requests</H2>
                    <table>
                        <tr>
                            <td>Course ID</td>
                            <td>Course Name</td>
                            <td>Action</td>
                        </tr>
        <%
                ArrayList <Course_DTO> deletetionRequestCourses = new ArrayList<>((ArrayList)session.getAttribute("deletetionRequestCourses"));
                    if(!deletetionRequestCourses.isEmpty())
                    {
                        for(int i=0; i<deletetionRequestCourses.size(); i++)
                        {
        %>
                    <form method="POST" action="Admin_Delete_Students_List">
                            <tr>
                                <td><input type="text" value="<%=deletetionRequestCourses.get(i).getCourseid()%>" name="courseid" readonly></td>
                                <td><input type="text" value="<%=deletetionRequestCourses.get(i).getCoursetitle()%>" name="coursename" readonly></td>
                                <td><input type="Submit" value="Show Students"></td>
                            </tr>
                    </form>
                    </table>
        <%
                        }
                    }
                }
        %>
                       
        
    </body>
</html>
