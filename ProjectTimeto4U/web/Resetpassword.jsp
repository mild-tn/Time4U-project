<%-- 
    Document   : Resetpassword
    Created on : Nov 27, 2018, 6:11:55 PM
    Author     : maysmiler
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Resetpassword Page</title>
    <link rel="stylesheet" href="include/css/Register.style.css"/>
</head>
<body>
    <jsp:include page="include/NavBar.jsp"/>
    <form action="Resetpass" method="get">
        <div id="overlay"></div>
        <div class="registerbox" style="text-align: center;color: whitesmoke">
            <h2>Reset</h2>
            <br>
            <input type="text" name="email" value="${email}" hidden>
            <input type="text" name="email" value="${email}" disabled>
            <input type="password" name="pass" placeholder="New Password">
            <input type="password" name="confirmpass" placeholder="Password Confirmation">
            ${messageError}
            <br>

            <input type="submit" value="SUBMIT">
            </form>

        </div>
</html>
