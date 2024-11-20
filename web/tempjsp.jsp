<%@page import="dto.Course_DTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.Student_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    boolean isStudentLoggedIn = false;
       if(session.getAttribute("isStudentLoggedIn") != null )
       {
           isStudentLoggedIn = (boolean)session.getAttribute("isStudentLoggedIn");
       }
       
    if(!isStudentLoggedIn)
    {
        response.sendRedirect("student_login.html");
        return;
    }
    
    Student_DTO studentDetails = (Student_DTO)session.getAttribute("studentDetails");
    ArrayList <Course_DTO> studentEnrolledCourseDetails = (ArrayList)session.getAttribute("studentEnrolledCourseDetails"); 
    ArrayList <Course_DTO> studentWishlistCourseDetails = (ArrayList)session.getAttribute("studentWishlistCourseDetails");


%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EmpowerHer | Student Home</title>
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
        body {
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
       
        .home {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
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
    display: flex;
    gap: 2rem; 
    align-items: center;
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
        .btn {
            display: inline-block;
            padding: 1rem 3rem;
            font-size: 2rem;
            font-weight: bolder;
            border-radius: 30px;
            color: var(--white);
            cursor: pointer;
            text-transform: capitalize;
            width: 200px;
            height: 35px;
            background: #507484;
    box-shadow: var(--box-shadow);
        }
        .btn:hover {
            background: transparent;
            color: var(--white);
        }
        .nav-btn {
            background: none;
            border: none;
            color: var(--light-white);
            font-size: 2rem;
            font-weight: bold;
            cursor: pointer;
            padding: 1rem 1rem;
            transition: color 0.4s, background-color 0.4s;
}

.nav-btn:hover {
    color: var(--green);
}
        table {
    width: 100%; 
    max-width: 1100px;
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
    width: 90%;
    padding: 0.4rem; 
    font-size: 20px; 
    border-radius: 20px;
    text-align: center;
    margin-left: 40px;
    box-shadow: var(--box-shadow);
    margin-bottom: 0.5rem; 
    background: var(--light-green);
    color: var(--black);
    
}
h1 {
    margin-top: 25px;
    text-align: center; 
    color: var(--black);
    margin-bottom: 1rem;
    font-size: 35px; 
}
h2 {
    text-align: left; 
    color: var(--white);
    margin-bottom: 1rem;
    font-size: 25px; 
}

.container{
    margin-left: 200px;
}
        </style>
    </head>
    <body>
        <!-- Header Section Starts -->
        <header class="header">
            <a href="Student_Home_Page.jsp" class="logo">Empower<span>Her</span></a>
            <nav class="navbar">
                <form action="home.html" method="get" style="display: inline;">
                    <input type="submit" value="Search Courses" class="nav-btn">
                </form>
                <form>
                </form>
                <form action="Student_Details" method="get" style="display: inline;">
                    <input type="submit" value="My Profile" class="nav-btn">
                </form>
                <form>
                </form>
                <form action="Student_Logout.jsp" method="post" style="display: inline;">
                    <input type="submit" value="Logout" class="nav-btn">
                </form>
            </nav>
        </header>
        <!-- Header Section Ends -->

        <h1>Hello <span style="color: #507484"><%=studentDetails.getStudentname() %></span>!</h1>
        <br>
        <div class="container">
            
            <table>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="3"><h2 style="text-align: center; color: #90bcc4">Enrolled Courses</h2></td>
                </tr>
                <tr>
                    <td>Course ID</td>
                    <td>Course Title</td>
                </tr>
        <%
            if(studentEnrolledCourseDetails.isEmpty())
            {
                %>
                <tr>
                    <td><input type="text" value="No data found" readonly></td>
                    <td><input type="text" value="No data found" readonly></td>
                </tr>
                <%
            }
            else
            {
                for(int i=0; i<studentEnrolledCourseDetails.size(); i++)
                {
        %>
                         <form method="POST" action="Student_Course_Details">
                        <tr>
                            <td><input type="text" value="<%=studentEnrolledCourseDetails.get(i).getCourseid() %>" name="enrolledcourseid" readonly="readonly"></td>       
                            <td><input type="text" value="<%=studentEnrolledCourseDetails.get(i).getCoursetitle() %>" name="enrolledcoursename" readonly="readonly"></td>
                            <td><input type="submit" class="btn" value="Course Details"></td>
                        </tr>
                         </form>
                <%
                }
            }
                %>
            <tr>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            </table>
        </div>
        <br><br>
        <div class="container">
                <table>
                    <tr>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="3"><h2 style="text-align: center; color: #90bcc4">Courses from Wishlist</h2></td>
                    </tr>

                 <tr>
                    <td>Course ID</td>
                    <td>Course Title</td>
                </tr>
            <%
                if(studentWishlistCourseDetails.isEmpty())
                {
                    %>
                    <tr>
                        <td><input type="text" value="No data found" readonly=""></td>
                        <td><input type="text" value="No data found" readonly=""></td>
                    </tr>
                    <%
                }
                else
                {
                    for(int i=0; i<studentWishlistCourseDetails.size(); i++)
                    {
            %>
                              <form method="POST" action="Student_Course_Details">
                            <tr>
                                <td><input type="text" value="<%=studentWishlistCourseDetails.get(i).getCourseid() %>" name="wishlistedcourseid" readonly="readonly"></td>       
                                <td><input type="text" value="<%=studentWishlistCourseDetails.get(i).getCoursetitle() %>" name="wishlistedcoursename" readonly="readonly"></td>
                                <td><input type="submit" class="btn" value="Course Details"></td>
                            </tr>
                              </form>
                    <%
                    }
                }
                    %>
                <tr>
                <td></td>
                <td></td>
                <td></td>
            </tr>
                </table>
            </div>
    </body>
</html>
