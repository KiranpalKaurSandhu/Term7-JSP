<%--
  Created by IntelliJ IDEA.
  User: kiran
  Date: 2023-10-03
  Time: 8:54 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%
    if (session.getAttribute("message")!=null && !session.getAttribute("message").equals(""))
    {
        response.getWriter().println("<h2 style='color:red'>" + session.getAttribute("message") + "</h2>");
        session.removeAttribute("message");
    }
%>
    <form method="get" action="login-servlet">
        Username: <input type="text" name="username" /> <br/>
        <br/>
        Password: <input type="password" name="password">
        <br/>
        <button type="submit">Login</button>
    </form>
</body>
</html>
