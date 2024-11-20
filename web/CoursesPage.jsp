<%@page import="java.util.HashSet"%>
<%@page import="dto.Course_DTO"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList <Course_DTO> courseBasicDetails = (ArrayList)session.getAttribute("courseBasicDetails");
    String searchquery = new String();
     if(session.getAttribute("searchquery") != null)
     {
            searchquery = (String)session.getAttribute("searchquery");
     }

    if(courseBasicDetails==null) {
        response.sendRedirect("home.html");
    }
    boolean isStudentLoggedIn = false;
    boolean filtersApplied = false;
    if(session.getAttribute("isStudentLoggedIn") != null) {
        isStudentLoggedIn = (boolean)session.getAttribute("isStudentLoggedIn");
    }
    if(session.getAttribute("filtersApplied") != null) {
        filtersApplied = (boolean)session.getAttribute("filtersApplied");
    }
      HashSet <Course_DTO> removeDuplicate = new HashSet<>(courseBasicDetails);
        courseBasicDetails = new ArrayList<>(removeDuplicate);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>EmpowerHer | Courses Page</title>
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
        body {
            background-image: url('images/EmpowerHer background Image.png');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            height: 100vh;
            background-attachment: fixed;
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
            padding: 1rem 3rem;
            font-size: 1.8rem;
            font-weight: bolder;
            margin-left: 1.5rem;
            border: var(--border);
            border-radius: 25px;
            color: var(white);
            cursor: pointer;
            text-transform: capitalize;
            background-color: transparent;
        }
        .btn:hover {
            background: #507484;
            color: var(--white);
        }
        .container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: center;
        }
        select {
            font-size: 1.6rem;
            padding: 1rem;
            border-radius: 8px;
            border: var(--border);
            color: var(--black);
            margin-left: 1.5rem;
            background-color: var(--light-white);
            box-shadow: var(--box-shadow);
            cursor: pointer;
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
            display: flex;
            align-items: center;
            justify-content: flex-end;
            flex: 1;
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
        .box {
            margin-top: 30px;
            max-width: 1200px;
            margin-left: 200px;
            height: 500px;
            padding: 1rem;
            background: var(--black);
            opacity: 0.7;
            border-radius: 10px;
            box-shadow: var(--box-shadow);
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .course-box {
            background: var(--black);
            opacity: 0.7;
            max-width: 48%;
            margin: 2rem auto;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: var(--box-shadow);
            flex: 1 1 45%; 
        }
        .course-box table {
            width: 100%;
            border-collapse: collapse;
        }
        .course-box td {
            padding: 0.8rem;
            vertical-align: top;
            font-size: 1.8rem ;
        }
        .course-box td.label {
            text-align: center;
            font-weight: bold;
            color: var(--light-white);
        }
        .course-box td.value {
            text-align: center;
            color: var(--light-white);
        }
        .btn-final{
            display: inline-block;
            padding: 1rem 2rem;
            font-size: 1.5rem;
            font-weight: bolder;
            margin-left: 1.5rem;
            border: 0px;
            border-radius: 20px;
            color: var(white);
            cursor: pointer;
            text-transform: capitalize;
            background-color: #507484;
            box-shadow: var(--box-shadow); 
        }
        .btn-final:hover {
            background: transparent;
            color: var(--white);
        }
        h1 {
    text-align: center;
    font-size: 2.5rem; 
    color: var(--black);    
    }
    .course-box h2 {
    text-align: center;
    font-size: 2.4rem; 
    color: var(--light-white);
}

.course-box .btn-final {
    display: block;
    margin: 2rem auto 0;
}

       

    </style>
</head>
<body>
    <!-- Header Section Starts -->
    <header class="header">
        <a href="index.html" class="logo">Empower<span>Her</span></a>
        <nav class="navbar">
            <a href="home.html">Home</a>
            <%
            if(isStudentLoggedIn)
            {
            %>
            <a></a>
            <a href="Student_Logout.jsp">Logout</a>
            <%
            }
            %>
        </nav>
    </header>
    <br><br>
    <table>
        <form class="box" action="Apply_Search_Filters" method="POST">
            <tr>
                <td>
                    <select name="coursemodefilter" id="coursemodefilter">
                        <option value="" selected disabled>Select course mode</option>
                        <option value="online">Online</option>
                        <option value="offline">Offline</option>
                        <option value="hybrid">Hybrid (Online + Offline)</option>
                    </select>
                </td>
                <td>
                    <select name="coursefeesfilter" id="coursefeesfilter">
                        <option value="" selected disabled>Select course fee</option>
                        <option value="fee1">below 500</option>
                        <option value="fee2">500 - 1500</option>
                        <option value="fee3">1501 - 2500</option>
                        <option value="fee4">2501 - 3500</option>
                        <option value="fee5">above 3500</option>
                    </select>
                </td>
                <td>
                    <select name="coursedurationfilter" id="coursedurationfilter">
                        <option value="" selected disabled>Select course duration</option>
                        <option value="1">1 month</option>
                        <option value="2">2 months</option>
                        <option value="3">3 months</option>
                        <option value="4">4 months</option>
                        <option value="5">5 months</option>
                        <option value="6">more than 5 months</option>
                    </select>
                </td>
                <td>
                    <select name="courselevelfilter" id="courselevelfilter">
                        <option value="" selected disabled>Select course difficulty</option>
                        <option value="beginner">Beginner Level</option>
                        <option value="intermediate">Intermediate Level</option>
                        <option value="advanced">Advanced Level</option>
                    </select>
                </td>
                <td>
                    <select name="coursecertificatefilter" id="coursecertificatefilter">
                        <option value="" selected disabled>Course Certificate</option>
                        <option value="1">Required</option>
                        <option value="0">Not required</option>
                    </select>
                </td>
                <td>
                    <input type="submit" class="btn" value="Apply Filters">
                </td>
            </tr>
        </form>
    </table>
    <br>
    <% if(courseBasicDetails.isEmpty()) { %>
        <br><h1>Sorry, no course found based on "<%=searchquery.toUpperCase()%>" <% if(filtersApplied) { %> and applied filters <% } %></h1>
    <% } else { %>
    <br><h1>Some of our verified courses based on <span style="color: #507484">"<%=searchquery.toUpperCase()%>"</span> <% if(filtersApplied) { %> and applied filters <% } %></h1>
    <% } %>
        <br>
    <div class="container">
        <% for(int i=0; i<courseBasicDetails.size(); i++) {
            Course_DTO details = courseBasicDetails.get(i);
        %>
        <div class="course-box">
            <form action="Course_Details" method="Post">
                <input type="hidden" name="inputcourseid" value="<%=details.getCourseid()%>">
                <h2 style="color: #90bcc4"><%=details.getCoursetitle()%></h2>
                <table>
                    
                    <tr>
                        <td class="value" colspan="2" style="text-align: center"><i><%=details.getCoursedescription()%></i></td>
                    </tr>
                     <tr>
                         <td><input type="hidden" name="inputcourseid" value="<%=details.getCourseid()%>" readonly></td>
                     </tr>
                    <tr>
                        <td class="label">Institute:</td><td class="value"><%=details.getCourseclassname()%></td>
                    </tr>
                    <tr>
                        <td class="label">Fees:</td><td class="value"><%=details.getCoursefee()%></td>
                    </tr>
                    <tr>
                        <td class="label">Mode:</td><td class="value"><%=details.getCoursemode()%></td>
                    </tr>
                    <tr>
                        <td class="label">Difficulty Level:</td><td class="value"><%=details.getCourseviewers()%></td>
                    </tr>
                    <tr><td></td></tr>
                </table>
                <% if(isStudentLoggedIn) { %>
                    <input type="submit" class="btn-final" value="Show Details">
                <% } else { %>
                    <input type="button" class="btn-final" value="Login to see details" onclick="location.href='home.html'">
                <% } %>
            </form>
        </div>
        <% } %>
    </div>
</body>
</html>
