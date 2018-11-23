<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="include/css/Payment.style.css"/>
    </head>
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
                        <li><span class="item-count">10</span><span class="item-name">Donuts</span><span class="item-price">$1.99ea</span></li>
                    </ul>
                    <div class="total">${shoppingCart.totalPrice}</div>
                </div>
                <div class="dough">
                    <h2>Payment</h2>
                    <form class="form">
                        <div class="inputs">
                            <div class="row">
                                <div class="column card-group">
                                    <label class="label" for="card">Card</label>
                                    <input class="text-input card-input" id="card" placeholder="1234 5678 9012 3456"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="column name-group">
                                    <label class="label" for="name">Name on Card</label>
                                    <input class="text-input name-input" id="name"/>
                                </div>
                                <div class="column expiry-group">
                                    <label class="label" for="expiry">Expiry</label>
                                    <input class="text-input expiry-input" id="expiry" placeholder="dd/yy"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="column cvc-group">
                                    <label class="label" for="cvc">CVC/Security Code</label>
                                    <input class="text-input cvc-input" id="cvc"/>
                                </div>
                                <div class="column cvc-help">3-4 digit code. Usually on the back, by the signature.</div>
                            </div>
                        </div>
                        <div class="buttons">
                            <button class="order-button">Order</button>
                        </div>
                    </form>
                </div>
            </div>
        </form>
    </body>
</html>