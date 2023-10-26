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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href="style.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <div class="row">
        <div class="col-lg-6 offset-lg-3">
            <form method="post" action="login-servlet" class="border p-4">
                <h2 class="text-center">Login</h2>
                <div class="mb-3">
                    <label for="username" class="form-label">Username:</label>
                    <input type="text" name="username" class="form-control" id="username" />
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password:</label>
                    <input type="password" name="password" class="form-control" id="password" />
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
            <c:if test="${not empty sessionScope.message}">
                <div class="alert alert-danger mt-3">${sessionScope.message}
                </div>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
