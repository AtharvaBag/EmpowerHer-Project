
<%@page import="model.TrainerCourses_Fetcher"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        String trainerid = (String)session.getAttribute("trainerid");
        if(trainerid==null)
        {
            response.sendRedirect("Trainer_Home_page.jsp");
            return;
        }
        HashMap <String, String> verifiedCoursesHashMap = new HashMap<>();
        HashMap <String, String> unverifiedCoursesHashMap = new HashMap<>();
        verifiedCoursesHashMap.clear();
        unverifiedCoursesHashMap.clear();
        TrainerCourses_Fetcher courses = (TrainerCourses_Fetcher)session.getAttribute("courses");
        courses.getAllCourses();
        courses.filterCourses();
        verifiedCoursesHashMap = courses.mapVerifiedCourseName();
        unverifiedCoursesHashMap = courses.mapUnverifiedCourseName();
        
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EmpowerHer | Trainer Courses</title>
    </head>
    <body>
        <H1>Your Course List: </H1>
          <H2>List of your Verified and Registered Courses</H2>
       
            
        <%
            if(!verifiedCoursesHashMap.isEmpty())
            {
         %>
          <table>
         
         <tr>
                <td>Course Id</td>
                <td>Course Name</td>    
            </tr>
         <%
                 for(String i: verifiedCoursesHashMap.keySet())
                {
                 %>
                 <form action="Trainer_Get_Verified_Course_Details" method="post">   
                 <tr>
                     <td><input type = "text" name="trainercourseid" id = "trainercourseid" value="<%=i%>" readonly ></td>
                    <td><input type="text" name = "trainercoursename" id="trainercoursename" value="<%=verifiedCoursesHashMap.get(i)%>" readonly></td>
                    <td><input type="submit" value="Show Details"></td>
                 </tr>
                 </form>
                 <%
                }
                %>
          </table>
                <%
            }
            else
            {
                %>
                        <table>
                            <tr>
                                <td>Course Id</td>
                                <td>Course Name</td>
                            <tr>
                            
                            <tr>
                                <td><input type="text" value="No data found" readonly></td>
                                <td><input type="text" value="No data found" readonly></td>
                            <tr>
                                
                        </table>
                <%
            
            }
            %>    
          <H2>List of your Registered Courses</H2>
          
           <%
            if(!unverifiedCoursesHashMap.isEmpty())
            {
             %>
             <table> 
            <tr>
                <td>Course Id</td>
                <td>Course Name</td>    
            </tr>
          <%
                for(String i : unverifiedCoursesHashMap.keySet())
                {
                 %>
                 
               <form action="Trainer_Get_Course_Details" method="post">    
                 <tr>   
                     <td><input type="text" name="trainercourseid" id="trainercourseid" value="<%=i%>" readonly></td>
                     <td><input type="text" name="trainercoursename" id="trainercoursename" value="<%=unverifiedCoursesHashMap.get(i)%>" readonly></td>
                    <td><input type="submit" value="Show Details"></td>
                  </tr>
               </form>
                 <%
                }
                  %>
           
             </table>
                  <%
            } 
            else
            {
                %>
                        <table>
                            <tr>
                                <td>Course Id</td>
                                <td>Course Name</td>
                            <tr>
                            
                            <tr>
                                <td><input type="text" value="No data found" readonly=""></td>
                                <td><input type="text" value="No data found" readonly=""></td>
                            <tr>
                                
                        </table>
                <%
            }
            %>
      
    </body>
</html>
