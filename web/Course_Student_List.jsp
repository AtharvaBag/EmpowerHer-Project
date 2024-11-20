<%@page import="dto.Course_DTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.Student_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
            ArrayList <Student_DTO> studentsList = new ArrayList<>((ArrayList)session.getAttribute("studentsList"));
            ArrayList <Student_DTO> deletedStudentsList = new ArrayList<>((ArrayList)session.getAttribute("deletedStudentsList"));
            Course_DTO registeredCourseDetails = (Course_DTO)session.getAttribute("registeredCourseDetails");
            if(studentsList == null  || registeredCourseDetails == null)
            {
                response.sendRedirect("home.html");
                return;
            }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EmpowerHer | Student List</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style> 
        * {
            font-family: 'Nunito', sans-serif;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            border: none;
            outline: none;
            text-decoration: none;
            transition: all .4s linear;
        }
        :root {
            --green: #90bcc4;
            --light-green: #f0fdfa;
            --light-color: #777;
            --light-white: #eee;
            --black: #444;
            --white: #fff;
            --border: .2rem solid var(--black);
            --box-shadow: 0 0.8rem 1rem rgba(0, 0, 0, .5);
        }
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
        body{
            background-image: url('images/EmpowerHer background Image.png');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            height: 100vh;
        }
        html {
            font-size: 62.5%;
            overflow-x: hidden;
        }
        html::-webkit-scrollbar {
            width: 1rem;
        }
        html::-webkit-scrollbar-track {
            background: transparent;
        }
        html::-webkit-scrollbar-thumb {
            background: #507484;
        }
        .home {
            display: flex;
            align-items: center;
            justify-content: center;
            
            height: 100vh;
        }
        .btn {
            display: inline-block;
            margin-top: 1rem;
            padding: 1rem 3rem;
            font-size: 1.8rem;
            border: var(--border);
            border-radius: 8px;
            color: #507484;
            cursor: pointer;
            text-transform: capitalize;
        }
        .btn:hover {
            background: #507484;
            color: var(--white);
        }
        .header {
            position: sticky;
            top: 0;
            left: 0;
            right: 0;
            background-color: var(--black);
            box-shadow: var(--box-shadow);
            padding: 2rem 10%;
            display: flex;
            align-items: center;
            z-index: 1000;
        }
        .header .logo {
            color: var(--light-white);
            margin-right: auto;
            font-size: 2.5rem;
            font-weight: bolder;
        }
        .header .logo span {
            color: var(--green);
        }
        .navbar {
            position: relative;
        }
        .navbar #close-navbar {
            position: absolute;
            top: 1.5rem;
            right: 2rem;
            font-size: 4rem;
            cursor: pointer;
            color: var(--black);
            display: none;
        }
        .navbar #close-navbar:hover {
            transform: rotate(90deg);
        }
        .navbar a {
            color: var(--light-white);
            margin-right: 2rem;
            font-size: 2rem;
            font-weight: bold;
        }
        .navbar a:hover {
            color: var(--green);
        }
        .icons div {
            cursor: pointer;
            font-size: 2.5rem;
            color: var(--light-white);
            margin-left: 1.5rem;
        }
        .icons div:hover {
            color: var(--green);
        }
        #menu-btn {
            display: none;
        }
        .dropdown {
            display: none;
            position: absolute;
            right: 0;
            background: var(--white);
            box-shadow: var(--box-shadow);
            z-index: 100;
            flex-direction: column;
            min-width: 150px;
        }
        .dropdown div {
            color: var(--black);
        }
/* Style for the Student List Table Section */
.container {
    margin-top: 30px; 
    max-width: 1200px;
    margin-left: 200px;
    height: auto;
    padding: 1rem; 
    background: var(--black);
    opacity: 0.7;
    border-radius: 10px;
    box-shadow: var(--box-shadow);
    display: flex;
    flex-direction: column; 
    align-items: center; 
}
h2 {
    text-align: left; 
    color: var(--white);
    margin-bottom: 1rem;
    font-size: 25px; 
}

h1 {
    margin-top: 50px;
    text-align: center; 
    color: var(--white);
    margin-bottom: 1rem;
    font-size: 25px; 
}
table {
    width: 100%; 
    max-width: 1200px;
    border-collapse: collapse; 
    margin-bottom: 1rem; 
    background: var(--black);
    opacity: 0.7;
    border-radius: 10px;
    box-shadow: var(--box-shadow);
}
table td {
    padding: 1rem; 
    font-size: 20px; 
    color: var(--white);
    vertical-align: top; 
    text-align: center;
}
input[type="text"] {
    width: 100%;
    padding: 0.4rem; 
    font-size: 20px; 
    border-radius: 20px;
    text-align: center;
    box-shadow: var(--box-shadow);
    margin-bottom: 0.5rem; 
    background: var(--light-green);
    color: var(--black);
    
}
input[type="submit"] {
    background-color: #507484;
    color: var(--white);
    border: 0px;
    padding: 0.4rem 1rem; 
    cursor: pointer;
    border-radius: 25px;
    transition: background-color 0.3s;
    font-size: 20px; 
    width: 200px;
    opacity: 1;
    box-shadow: var(--box-shadow);
}
input[type="submit"]:hover {
    background-color: var(--black); 
    color: var(--white);
}
</style>

    </head>
    <body>
        <!-- Header Section Starts -->
    <header class="header">
        <a href="Trainer_Home_Page.jsp" class="logo">Empower<span>Her</span></a>
        <nav class="navbar">
            <a href="Trainer_Home_Page.jsp">Home</a>
            <a></a>
            <a href="Trainer_Course_View.jsp">Back</a>
            <a></a>
            <a href="Trainer_Logout.jsp">Logout</a>
        </nav>
        </div>
        <div id="menu-btn" class="fa fa-bars"></div>
    </header>
    <!-- Header Section Ends -->

    <!-- Student List Section starts -->
   
        <!-- Student List Section starts -->

        <h1 style="color: var(--black)">Course Name: <span style="color: #507484"><%= registeredCourseDetails.getCoursetitle().toUpperCase() %></span></h1>
    <BR>
    <div class="container">
     <h2>Enrolled Students List:</h2>
    <table>
        <tr>
            <td>Student Id</td>
            <td>Student Name</td>
            <td>Student Contact</td>
            <td>Action</td>
        </tr>
        <% if (studentsList.size() == 0) { %>
            <tr>
                <td colspan="4">No data found</td>
            </tr>
        <% } else {
            for (int i = 0; i < studentsList.size(); i++) {
                Student_DTO studentDetails = studentsList.get(i); %>
                <form action="Request_For_Deletion" method="POST">
                    <tr>
                        <td><input type="text" value="<%=studentDetails.getStudentid()%>" name="studentid" readonly></td>
                        <td><input type="text" value="<%=studentDetails.getStudentname().toUpperCase()%>" name="studentname" readonly></td>
                        <td><input type="text" value="<%=studentDetails.getStudentcontact()%>" name="studentcontact" readonly></td>
                        <td><input type="submit" value="Request Deletion"></td>
                    </tr>
                </form>
        <% }
        } %>
    </table>
    </div>
    
    <BR>
    <BR>
    <% if (deletedStudentsList != null && !deletedStudentsList.isEmpty()) { %>
    
    <div class="container">
        <h2>Deleted Students List:</h2>
        <table>
            <tr>
                <td>Student Id</td>
                <td>Student Name</td>
                <td>Student Contact</td>
                <td>Action</td>
            </tr>
            <% for (int i = 0; i < deletedStudentsList.size(); i++) {
                Student_DTO deletedStudent = deletedStudentsList.get(i); %>
                <form action="Cancel_Deletion_Request" method="POST">
                    <tr>
                        <td><input type="text" value="<%=deletedStudent.getStudentid()%>" name="deletedstudentid" readonly></td>
                        <td><input type="text" value="<%=deletedStudent.getStudentname().toUpperCase()%>" name="deletedstudentname" readonly></td>
                        <td><input type="text" value="<%=deletedStudent.getStudentcontact()%>" name="deletedstudentcontact" readonly></td>
                        <td><input type="submit" value="Cancel Deletion"></td>
                    </tr>
                </form>
            <% } %>
        </table>
    <% } %>
</div>
<!-- Student List Section ends -->

     
   <!-- Student List Section ends -->
    </body>
</html>