<%-- 
    Document   : ActivateAccount
    Created on : Nov 11, 2018, 1:11:53 PM
    Author     : kao-tu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ActivateRepass Page</title>
        <link rel="stylesheet" href="include/css/Register.style.css"/>
    </head>
    <body>
        <jsp:include page="include/NavBar.jsp"/>
        <form action="ActivateRepass" method="get">
            <div id="overlay"></div>
            <div class="registerbox" style="text-align: center;color: whitesmoke">
                <h1>Activate</h1>
                <br>
                <input type="text" name="email" value="${email}" hidden>
                <input type="text" name="email" value="${email}" disabled>
                <input type="text" name="activatekey" placeholder="Activate Key">
                <br>
                ${MsActivate}
                <br>
                <input type="submit" value="SUBMIT">
                </form>
                <p style="color: red;text-align: center">${messageActivate}</p>
            </div>
    </body>
</html>
