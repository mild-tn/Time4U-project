<%-- 
    Document   : Login
    Created on : Nov 10, 2018, 1:59:20 PM
    Author     : maysmiler
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password Page</title>
        <link rel="stylesheet" href="include/css/Login.style.css"/>
    </head>
    <style>
        body h1{
          color: white;
        }
    </style>
    <body>
        <jsp:include page="include/NavBar.jsp"/>
        <form action="CheckmailRepass" method="post">
            <div id="overlay"></div>
            <div class="loginbox">
                <h2>Forgot Password</h2><br>
                <br>
                <input type="text" name="email" placeholder="Email">
                <br>
                <p style="text-align: center">${message}</p>
                <br>
                <input type="submit" value="send">
                </form>
                <span id="text-account">Already registered? </span>
                <a id="create-account" <a href="Login.jsp">Sign In</a></a><br>
                <span id="text-account">Don't have an account?</span>
                <a id="create-account" href="Register.jsp"> Create here.</a>
            </div>
    </body>
</html>
