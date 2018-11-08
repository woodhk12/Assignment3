<%-- 
    Document   : top
    Created on : Nov 7, 2018, 9:02:26 PM
    Author     : yangd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Traffic Report</title>
        <jsp:useBean id="uname" class="servelets.userBean" scope="session" />
    </head>
    <body>
        <div style = "width:70%;margin:auto;">
            <h2>Hello, <jsp:getProperty name="uname" property="name" />!</h2>
            
            <form action = "subTraffic" >
                Road To Report:
                <input type="text" name = "road" placeholder = "Road Name" /><br><br>
                <input type="radio" name = "situation" value = "Good" />Good
                <input type="radio" name = "situation" value = "Normal" />Normal
                <input type="radio" name = "situation" value = "Busy" />Busy<br><br>
                <input type="submit" value="Submit">
            </form>
            <br>
            <a href="list.jsp">See all reported road conditions</a>
        </div>
    </body>
</html>
