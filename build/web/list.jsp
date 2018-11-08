<%-- 
    Document   : list
    Created on : Nov 7, 2018, 9:28:28 PM
    Author     : yangd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reports</title>
    </head>
    <body style = "width:60%;margin:auto;">
        <br>
        <table>
            <tr>
                <th>Road</th>
                <th>Condition</th>
                <th>Time</th>
                <th>Contributor</th>
            </tr>
        <jsp:include page = "list"/>
        </table>
        <br>
        <a href="top.jsp">Back to submitting another report</a>
    </body>
</html>
