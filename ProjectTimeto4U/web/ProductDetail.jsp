<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Detail</title>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/all.css"/>
        <link rel="icon" type="image/png" sizes="64x64" href="images/oie_transparent.png">
    </head>
    <style>
        p{
          margin-top: 5%;
        }
    </style>
    <body style="background-color: #fffffe">
        <jsp:include page="include/NavBarBackColor.jsp"/>
        <c:set value="${productDetail}" var="p"></c:set>
          <div style="margin-top: 40px;" class="container">
              <div class="row">
                  <div class="col">
                      <img src="include/img/product/${p.productcode}.jpg" width="300px;"/>
              </div>
              <div class="col">
                  <h3><b>ชื่อสินค้า : </b>${p.productname}</h3>
                  <b>ชนิดของสาย : </b>${p.producttype}<br>
                  <b>รายละเอียดสินค้า :</b> ${p.productdescription}<br>
                  <b>ประกัน :</b> ${p.warrenty == '' ? '-':p.warrenty}<br>
                  <b>เพศ :</b> ${p.sex == "F" ? "หญิง":"ชาย"}<br>
                  <b>ราคา/1 ชิ้น : </b>${p.buyprice}<br>
                  <b>สี : </b><input style="height: 20px;width: 20px;background-color: ${p.productcolor};border: 1px solid black;border-radius: 10px;" disabled/><br>
                  <div class="row justify-content-center">
                      <div class="col">
                      </div>
                      <div class="col-6 text-right">
                          <form action="AddToCart" style="margin-top: 40px">
                              <input hidden type="text" value="${p.productcode}" name="addProductCode"/>
                              <button type="submit" class="btn btn-outline-secondary">
                                  <i class="fas fa-cart-plus"></i> Add To Cart
                              </button>
                              <a href="ShowProductServlet" class="btn btn-outline-secondary">Back</a>
                          </form>      
                      </div>
                  </div>
              </div>
          </div>
        </div>
        <jsp:include page="include/footer.jsp"/>
    </body>
</html>
