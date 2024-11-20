<%@page import="dto.Payment_DTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("isAdminVerified")==null)
    {
        response.sendRedirect("home.html");
        return;
    }
    if(!(boolean)session.getAttribute("isAdminVerified"))
    {
        response.sendRedirect("home.html");
        return;
    }
    boolean showpayments = false;
    if(session.getAttribute("showpayments")!=null)
    {
        showpayments = (boolean)session.getAttribute("showpayments");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EmpowerHer | Admin Payment Details</title>
    </head>
    <body>
        <h1>Hello Admin!</h1>
        <p>Enter student id or course id or both to fetch the payment details!</p>
        <a href="Admin_Home.jsp">Home</a>
        <form action="Check_Payment_Input" method="POST">
            <table>
                <tr>
                    <td>Student Id</td>
                    <td>Course Id</td>
                    <td>Action</td>
                </tr>
                <tr>
                    <td><input type="text" name="studentid"></td>
                    <td><input type="text" name="courseid"></td>
                    <td><input type="Submit" value="Search"></td>
                </tr>
            </table>
            
            
            <%
            if(showpayments)
            {
            %>
                <H2>Payment Details</H2>
                <table>
                    <tr>
                        <td>Student ID</td>
                        <td>Course ID</td>
                        <td>Payment Mode</td>
                        <td>Amount Paid</td>
                        <td>Transaction Date and Time</td>
                    </tr>
            <%
                    ArrayList <Payment_DTO> paymentDetailsList = new ArrayList <>((ArrayList)session.getAttribute("paymentDetailsList"));
                    if(paymentDetailsList.isEmpty())
                    {
            %>
                     <tr>
                        <td><input type="Text" value="No Data Found" readonly></td>
                        <td><input type="Text" value="No Data Found" readonly></td>
                        <td><input type="Text" value="No Data Found" readonly></td>
                        <td><input type="Text" value="No Data Found" readonly></td>
                        <td><input type="Text" value="No Data Found" readonly></td>
                    </tr>   
            <%
                    }
                    else
                    {
                        for(int i=0; i<paymentDetailsList.size(); i++)
                        {
            %>
                            <tr>
                                <td><input type="text" value="<%=paymentDetailsList.get(i).getStudentid()%>" readonly></td>
                                <td><input type="text" value="<%=paymentDetailsList.get(i).getCourseid()%>" readonly></td>
                                <td><input type="text" value="<%=paymentDetailsList.get(i).getPaymentmode()%>" readonly></td>
                                <td><input type="text" value="<%=paymentDetailsList.get(i).getCoursefee()%>" readonly></td>
                                <td><input type="text" value="<%=paymentDetailsList.get(i).getCurrentdatetime()%>" readonly></td>
                            </tr>
            <%
                        }
                    }
            %>
                </table>
            <%
            }
            %>
        </form>
    </body>
</html>
