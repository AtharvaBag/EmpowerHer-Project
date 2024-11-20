<%@page import="dto.Course_DTO"%>
<%
    boolean isStudentLoggedIn = false;
    Course_DTO courseDetails = null;
    if (session.getAttribute("isStudentLoggedIn") == null || session.getAttribute("courseDetails") == null) {
        response.sendRedirect("student_login.html");
        return;
    }


    isStudentLoggedIn = (boolean) session.getAttribute("isStudentLoggedIn");
    courseDetails = (Course_DTO) session.getAttribute("courseDetails");
    if (!isStudentLoggedIn) {
        response.sendRedirect("student_login.html");
        return;
    }
    
    boolean wishlisted = false;
    if(session.getAttribute("wishlisted")!=null)
    {
        System.out.println("Already in wishlist");
        wishlisted = (boolean)session.getAttribute("wishlisted");
    }
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EmpowerHer | Course Enrollment Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        * {
            font-family: 'Nunito', sans-serif;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
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


       
       
        body, html {
            height: 100%;
            background-image: url('images/EmpowerHer background Image.png');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            font-size: 62.5%;
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


        .navbar a {
            color: var(--light-white);
            margin-right: 2rem;
            font-size: 2rem;
            font-weight: bold;
        }


        .navbar a:hover {
            color: var(--green);
        }


        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: calc(100vh - 100px);
            padding: 20px;
            margin-left: -10%;
        }


        .box-container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 25px;
            padding-top: 10px;
            padding-bottom: 10px;
            border-radius: 10px;
            box-shadow: var(--box-shadow);
            max-width: 800px;
            width: 100%;
            margin: 20px;
            background: var(--black);
            opacity: 0.7;
        }


        h1 {
            font-size: 3rem;
            color: var(--white);
            text-align: center;
            margin-bottom: 1.5rem;
        }
        
         h3 {
             margin-top: 1.5rem;
            font-size: 2.0rem;
            color: var(--black);
            text-align: center;
            margin-bottom: 1.5rem;
        }


        table {
            width: 100%;
            /* border-collapse: collapse; */
            margin-top: 1rem;
        }


        td {
            padding: 1.2rem;
            font-size: 1.6rem;
            text-align: center;
        }


        label {
            font-weight: bold;
            color: var(--black);
            font-size: 20px;
        }


        input[type="text"] {
            width: 100%;
            padding: 1rem;
            color: var(--white);
            border: var(--border);
            border-radius: 20px;
            margin-top: .5rem;
            box-sizing: border-box;
            background-color: var(--black);
            font-size: 1.6rem;
            text-align: center;
            width: 300px;
            box-shadow: var(--box-shadow);
        }


        .radio-group {
            padding-top: 1rem;
            font-size: 1.75rem;
        }


        .btn {
            background-color: #507484;
            color: var(--white);
            padding: 1.2rem 2rem;
            font-size: 1.8rem;
            border-radius: 25px;
            cursor: pointer;
            transition: background-color 0.3s;
            width: 100%;
            text-align: center;
            box-shadow: var(--box-shadow);
            border: 0px;
        }


        .btn:hover {
            background-color: var(--black);
        }


        .btn-group {
            display: flex;
            gap: 1rem;
            justify-content: center;
            margin-top: 2rem;
        }


        @media (max-width: 700px) {
            .box-container {
                width: 90%;
                padding: 20px;
            }
        }
        .table-container {
    max-width: 700px;
    max-height: 500px;
    overflow: auto;
    margin-top: 1rem;
    margin-left: 25px;
    background-color: var(--white);
    padding: 1.5rem;
    border-radius: 10px;
}


table {
    width: 100%; /* Table width set to container width */
    margin-top: 1rem;
}


td, th {
    padding: 1.2rem;
    font-size: 1.6rem;
}


textarea {
    width: 100%;
    padding: 1.2rem;
    font-size: 1.6rem;
    color: var(--white);
    background-color: var(--black);
    border: var(--border);
    border-radius: 20px;
    box-sizing: border-box;
    box-shadow: var(--box-shadow);
    resize: none;
}
    </style>
</head>
<body>
    <header class="header">
        <a href="#" class="logo">Empower<span>Her</span></a>
        <nav class="navbar">
            <a href="Student_Home.jsp">Home</a>
            <a></a>
            <a href="courses.html">Courses</a>
            <a></a>
            <a href="Student_Logout.jsp">Logout</a>
        </nav>
    </header>


    <div class="container">
        <div class="box-container">
            <br>
            <h1>Course Details</h1>
            <form method="POST" action="Wishlist_Course">
                <div class="table-container">
                <table>
                    <tr>
                        <td><label for="skill-domain">Skill Domain:</label></td>
                        <td><input type="text" value="<%= courseDetails.getTrainerskill() %>" name="skill-domain" id="skill-domain" readonly></td>
                    </tr>
                    <tr>
                        <td><label for="class-name">Class/Institute Name:</label></td>
                        <td><input type="text" value="<%= courseDetails.getCourseclassname() %>" name="class-name" id="class-name" readonly></td>
                    </tr>
                    <tr>
                        <td><label for="contact">Contact Number:</label></td>
                        <td><input type="text" value="<%= courseDetails.getCourseclasscontact() %>" name="contact" id="contact" readonly></td>
                    </tr>
                    <tr>
                        <td><label for="course-title">Course Title:</label></td>
                        <td><input type="text" value="<%= courseDetails.getCoursetitle() %>" name="course-title" id="course-title" readonly></td>
                    </tr>
                    <tr>
                        <td><label>Course Level:</label></td>
                        <td class="radio-group">
                            <input type="radio" name="selectedcourseviewers" value="Beginner" <% if (courseDetails.getCourseviewers().equalsIgnoreCase("beginner")) { %> checked <% } %> disabled> Beginner
                            <br>
                            <input type="radio" name="selectedcourseviewers" value="Intermediate" <% if (courseDetails.getCourseviewers().equalsIgnoreCase("intermediate")) { %> checked <% } %> disabled> Intermediate
                            <br>
                            <input type="radio" name="selectedcourseviewers" value="Advance" <% if (courseDetails.getCourseviewers().equalsIgnoreCase("advance")) { %> checked <% } %> disabled> Advance
                        </td>
                    </tr>
                    <tr>
                        <td><label>Course Duration (in months): </label></td>
                        <td><input type="text" value="<%= courseDetails.getCourseduration() %>" name="selectedcourseduration" id="selectedcourseduration" placeholder="in months" readonly></td>
                    </tr>
                    <tr>
                        <td><label for="">Number of class days (per week): </label></td>
                        <td><input type="text" value="<%= courseDetails.getCoursedays() %>" name="selectedcoursedays" id="selectedcoursedays" readonly></td>
                    </tr>
                    <tr>
                        <td><label for="">Mode of class: </label></td>
                        <td class="radio-group">
                            <input type="radio" name="selectedcoursemode" value="Online" <% if (courseDetails.getCoursemode().equalsIgnoreCase("Online")) { %> checked="true" <% } %>disabled="true"> Online <br>
                            <input type="radio" name="selectedcoursemode" value="Offline" <% if (courseDetails.getCoursemode().equalsIgnoreCase("Offline")) { %> checked="true" <% } %>disabled="true"> Offline <br>
                            <input type="radio" name="selectedcoursemode" value="Hybrid" <% if (courseDetails.getCoursemode().equalsIgnoreCase("Hybrid")) { %> checked="true" <% } %>disabled="true"> Hybrid (Online + Offline)
                        </td>
                    </tr>
                    <% if (!courseDetails.getCoursemode().equalsIgnoreCase("online")) { %>
                    <tr>
                        <td><label for="">Address of institute/class: </label></td>
                        <td><input type="text" name="selectedcourseclassaddress" id="selectedcourseclassaddress" placeholder="if available" value="<%= courseDetails.getCourseclassaddress() %>" readonly></td>
                    </tr>
                    <tr>
                        <td><label for="">City of institute/class: </label></td>
                        <td><input type="text" name="selectedcourseclasscity" id="selectedcourseclasscity" placeholder="if available" value="<%= courseDetails.getCourseclasscity() %>" readonly></td>
                    </tr>
                    <tr>
                        <td><label for="">State of institute/class: </label></td>
                        <td><input type="text" name="selectedcourseclassstate" id="selectedcourseclassstate" placeholder="if available" value="<%= courseDetails.getCourseclassstate() %>" readonly></td>
                    </tr>
                    <% } %>
                    <tr>
                        <td><label for="selectedcoursedescription">Course Description:</label></td>
                        <td><textarea name="selectedcoursedescription" id="selectedcoursedescription" rows="4" cols="30" placeholder="in about 100 words" readonly><%= courseDetails.getCoursedescription() %></textarea></td>
                    </tr>
                    <tr>
                        <td><label for="">Certification provided? </label></td>
                        <td class="radio-group">
                            <input type="radio" name="selectedcoursecertificate" value="1" <% if (courseDetails.getCoursecertification().equals("1")) { %> checked="true"  <% } %>disabled="true"> Yes<br>
                            <input type="radio" name="selectedcoursecertificate" value="0" <% if (courseDetails.getCoursecertification().equals("0")) { %> checked="true"  <% } %>disabled="true"> No
                        </td>
                    </tr>
                    <tr>
                        <td><label for="">Total fees of course: </label></td>
                        <td><input type="text" name="selectedcoursefee" id="selectedcoursefee" placeholder="in INR" value="<%= courseDetails.getCoursefee() %>" readonly></td>
                    </tr>
                </table>
                <div class="btn-group">
                    <input type="submit" value="Add to Wishlist" class="btn">
                    <input type="button" value="Enroll Now!" class="btn" onclick="location.href='Payment_Page.jsp'">
                </div>
                    <%
                    if(wishlisted)
                    {
                    %>
                        <H3>Course is already present in your wishlist</H3>
                    <%
                    }
                    %>
                </div>
               
            </form>
        </div>
    </div>
</body>
</html>

