<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
﻿<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment</title>
        <link rel="stylesheet" href="include/css/Payment.style.css"/>
        <link rel="icon" type="image/png" sizes="64x64" href="images/oie_transparent.png"/>
    </head>
    <style>
        .buttons input{
            cursor: pointer;
        }
    </style>
    <body style="background-color: #000">
        <jsp:include page="include/NavBar.jsp" />
        <form action="Payment" method="post">
            <script>$(function () {
                    $('.card-input').payment('formatCardNumber');
                    $('.expiry-input').payment('formatCardExpiry');
                    $('.cvc-input').payment('formatCardCVC');
                    $('.form').on('submit', function (e) {
                        e.preventDefault();
                    });
                });
            </script>
            <div class="container">
                <div class="icing">
                    <h2>Your Order</h2>
                    <ul class="order">
                        <c:forEach items="${All.lineItems}" var="s" varStatus="vc">
                            <li><span class="item-count">${vc.count}.</span>
                                <span class="item-name">${s.product.productname}</span>
                                <span class="item-name">${s.quantity}</span>
                                <span class="item-name">${s.totalPrice} ฿</span>
                            </li>
                        </c:forEach>
                    </ul>
                    <div class="total"><input type="submit" value="${shoppingCart.totalPrice}" hidden>${shoppingCart.totalPrice} ฿</div>
                </div>
                <div class="dough">
                    <h2>Payment</h2>
                    <form class="form">
                        <div class="inputs">
                            <div class="row">
                                <div class="column card-group">
                                    <label class="label" for="card">Card</label>
                                    <input class="text-input card-input" id="cardno" placeholder="1234 5678 9012 3456" name="cardno"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="column name-group">
                                    <label class="label" for="name">Name on Card</label>
                                    <input class="text-input name-input" id="cardholder" name="cardholder"/>
                                </div>
                                <div class="column expiry-group">
                                    <label class="label" for="expiry">Expiry</label>
                                    <input class="text-input expiry-input" id="exp" placeholder="MMYYYY" name="exp"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="column cvc-group">
                                    <label class="label" for="cvc">CVV/Security Code</label>
                                    <input class="text-input cvc-input" id="cvv" name="cvv"/>
                                </div>
                                <div class="column cvc-help">3 digit code. Usually on the back, by the signature.</div>
                            </div>
                        </div>
                        <div class="buttons">
                            <p style="color: red">${messagePayment}</p>
                            <input class="order-button" type="submit" value="Pay Now">
                        </div>
                    </form>
                </div>
            </div>
        </form>
        <%--<jsp:include page="include/footer.jsp"/>--%>
    </body>
</html>