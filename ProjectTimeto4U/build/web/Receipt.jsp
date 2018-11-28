<%-- 
    Document   : Receipt
    Created on : Nov 28, 2018, 3:05:07 PM
    Author     : kao-tu
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Receipt</title>
        <!--<link rel="stylesheet" href="include/css/Receipt.style.css"/>-->
        <link rel="icon" type="image/png" sizes="64x64" href="images/oie_transparent.png"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />
    </head>
    <style>
        .card{
            margin-top: 400px;
        }
    </style>
    <body style="background-color: #000">
        <jsp:include page="include/NavBar.jsp" />
        <div class="container">
            <div class="row">
                <div class="col-12 d-flex justify-content-center align-content-center flex-wrap">
                    <div class="card bg-light mb-3" style="max-width: 50rem;">
                        <div class="card-header">Payment</div>
                        <div class="card-body">
                            <h5 class="card-title">Receipt</h5>
                            <p class="card-text">
                             <c:forEach items="${All.lineItems}" var="s" varStatus="vc">
                            <li><span class="item-count">${vc.count}.</span>
                                <span class="item-name">${s.product.productname}</span>
                                <span class="item-name">${s.quantity}</span>
                                <span class="item-name">${s.totalPrice} ฿</span>
                            </li>
                            </c:forEach>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
    </body>
</html>
