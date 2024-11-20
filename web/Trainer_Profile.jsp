<%@page import="dto.Trainer_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
           Trainer_DTO trainerDetails = (Trainer_DTO)session.getAttribute("trainerDetails");
           if(trainerDetails == null)
           {
               response.sendRedirect("trainer_login.html");
               return;
           }
           String trainerVerificationStatus = (String)session.getAttribute("trainerVerificationStatus");
           System.out.println("Trainer verification: "+trainerVerificationStatus);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trainer Profile | EmpowerHer</title>
    </head>
    <style>
        /* Global styles consistent with home.html */
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
            color: var(--green);
            cursor: pointer;
            text-transform: capitalize;
        }
        .btn:hover {
            background: var(--green);
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
            display: flex;
            align-items: center;
            justify-content: flex-end;
            flex: 1; /* Take full width */
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
        .role-option {
            padding: 1rem;
            cursor: pointer;
            font-size: 1.6rem;
            color: var(--black);
            text-align: left;
        }
        .role-option:hover {
            background: var(--light-green);
        }
        .account-form {
            position: fixed;
            top: 0;
            right: -105%;
            width: 45rem;
            background: var(--white);
            display: flex;
            flex-flow: column;
            gap: 2rem;
            justify-content: center;
            height: 100%;
            z-index: 1200;
            padding: 2rem;
            text-align: center;
            overflow-y: hidden;
        }
        .account-form.active {
            right: 0;
            box-shadow: 0 0 0 100vw rgba(0, 0, 0, 0.8);
        }
        .account-form #close-form {
            position: absolute;
            top: 1.5rem;
            right: 2.5rem;
            font-size: 4rem;
            cursor: pointer;
            color: var(--black);
        }
        .account-form #close-form:hover {
            transform: rotate(90deg);
        }
        .account-form form {
            border: var(--border);
            border-radius: 8px;
            padding: 2rem;
            display: none;
        }
        .account-form form.active {
            display: block;
        }
        .account-form form h3 {
            font-size: 2.5rem;
            padding-bottom: .5rem;
            text-transform: uppercase;
        }
        .account-form form .box {
            width: 100%;
            padding: 1.2rem 1.4rem;
            border: var(--border);
            border-radius: 8px;
            font-size: 1.6rem;
            margin: .7rem 0;
        }
        .account-form form .btn {
            width: 100%;
        }
        .buttons {
            display: flex;
            justify-content: center;
        }
        .buttons .btn {
            margin: 0 .5rem;
        }
        .scrollable-form {
            max-height: 80vh; 
            overflow-y: auto; 
            padding-right: 1rem; 
        }
        .scrollable-form::-webkit-scrollbar {
            width: 0.8rem;
        }
        .scrollable-form::-webkit-scrollbar-thumb {
            background-color: var(--green);
            border-radius: 1rem;
        }
        .scrollable-form::-webkit-scrollbar-track {
            background: transparent;
        }
        .domain-heading {
            font-size: 3rem;
            font-weight: bold;
            color: var(--black);
            text-align: left;
            margin-left: 12%;
            margin-top: 3rem;
            margin-bottom: 2rem;
        }
        .container {
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
        h2 {
            text-align: left; 
            color: var(--white);
            margin-bottom: 1rem;
            font-size: 25px; 
        }
        /* Form styling */
        form {
            width: 100%; 
        }
        table {
            width: 100%; 
            max-width: 1200px;
            border-collapse: collapse; 
            margin-bottom: 1rem; 
        }
        table td {
            padding: 1rem; 
            font-size: 20px; 
            color: var(--white);
            vertical-align: top; 
            text-align: center;
        }
        input[type="text"],
        input[type="tel"],
        input[type="date"] {
            width: 100%;
            padding: 0.4rem; 
            font-size: 20px; 
            border-radius: 20px;
            text-align: center;
            box-shadow: var(--box-shadow);
            margin-bottom: 0.5rem; 
        }
        input[type="submit"],
        input[type="reset"] {
            background-color: #507484;
            color: var(--white);
            border: 0px;
            padding: 0.4rem 1rem; 
            cursor: pointer;
            border-radius: 25px;
            transition: background-color 0.3s;
            font-size: 20px; 
            width: 120px; 
            box-shadow: var(--box-shadow);
        }
        
        input[type="reset"]{
            margin-left: 75px;
        }
        
        input[type="submit"]{
            margin-left: 75px;
        }
        
        input[type="submit"]:hover,
        input[type="reset"]:hover {
            background-color: var(--black); 
        }
        h3 {
            text-align: center; 
            font-size: 1.4rem; 
            color: var(--white);
        }
    </style>
    <body>
         <!-- Header Section Starts -->
    <header class="header">
        <a href="index.html" class="logo">Empower<span>Her</span></a>
        <nav class="navbar">
            <a href="Trainer_Home_Page.jsp">Home</a>
            <a></a>
            <a href="Trainer_Profile_Delete.jsp">Delete Profile</a>
        </nav>
    </header>
    <!-- Header Section Ends -->
    <br><br>
        <div class="container">
            <br>
        <H2>Trainer Profile</H2>
        <br><br>
        <form method="post" action="TrainerProfile_Update">
            <table>
                <tr>
                    <td>First Name:</td>
                    <td><input type="text" name="trainerfirstname" id="trainerfirstname" required value="<%=trainerDetails.getTrainerfirstname()%>"></td>
                    <td>Middle Name:</td>
                    <td><input type="text" name="trainermiddlename" id="trainermiddlename" value="<%=trainerDetails.getTrainermiddlename()%>"></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><input type="text" name="trainerlastname" id="trainerlastname" required value="<%=trainerDetails.getTrainerlastname()%>"></td>
                    <td>Date of Birth:</td>
                    <td><input type="date" name="trainerdob" id="trainerdob" required value="<%=trainerDetails.getTrainerdob()%>"></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><input type="text" name="traineraddress" id="traineraddress" required value="<%=trainerDetails.getTraineraddress()%>">
                    </td>  <td>Contact Number:</td>
                    <td><input type="tel" name="trainercontact" id="trainercontact" required pattern="[0-9]{10}" placeholder="10 digit mobile number" value="<%=trainerDetails.getTrainercontact()%>"></td>
                   
                </tr>
                <tr>
                    <td>City:</td>
                    <td><input type="text" name="trainercity" id="trainercity" required value="<%=trainerDetails.getTrainercity()%>"></td>
                     <td>Email ID:</td>
                    <td><input type="text" name="traineremailid" id="traineremailid" required value="<%=trainerDetails.getTraineremailid()%>"></td>
                
                </tr>
                    <td>State:</td>
                    <td><input type="text" name="trainerstate" id="trainerstate" required value="<%=trainerDetails.getTrainerstate()%>"></td>
                      <td>Adhaar ID:</td>
                    <td><input type="text" name="traineradharnumber" id="traineradharnumber" required pattern="[0-9]{12}" placeholder="12 digit aadhar number" value="<%=trainerDetails.getTraineradharnumber()%>"></td>
                </tr>
                <tr>
                    <td>
                    </td>
                    <td>
                        <input type="reset" value="Reset">
                    </td>
                    <td>
                        <input type="submit" value="Update">
                    </td>
                    <td>
                    </td>
                </tr>
            </table>
        </form>
        <%
            if(trainerVerificationStatus.equals("1"))
            {
        %>
        <br>
        <h3>*If you will update your profile, then you will loose verified status and re-verification process will start!</h3>
        <%
            }
        %>
        </div>
        <script src="script.js"></script>
    </body>
</html>
