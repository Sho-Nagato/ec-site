<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>購入確認</title>
  </head>

  <body>
    <h2>購入してよろしいでしょうか？</h2>
    <table border="1">
        <tr>
            <td>商品名</td>
            <td>単価</td>
            <td>数量</td>
        </tr>
        <c:forEach var="cartProduct" items="${sessionScope.cart.cartProductModelList}">
            <tr>
                <td>${cartProduct.productName}</td>
                <td align="right"><fmt:formatNumber value="${cartProduct.productPrice}" type="CURRENCY" maxFractionDigits="0" currencySymbol="\\" /></td>
                <td align="right"><fmt:formatNumber value="${cartProduct.purchasePlanNumber}" /></td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="2">消費税</td>
            <td align="right"><fmt:formatNumber value="${sessionScope.cart.tax}" type="CURRENCY" maxFractionDigits="0" currencySymbol="\\" /></td>
        </tr>
        <tr>
            <td colspan="2">合計金額</td>
            <td align="right"><fmt:formatNumber value="${sessionScope.cart.totalPrice}" type="CURRENCY" maxFractionDigits="0" currencySymbol="\\" /></td>
        </tr>
    </table>
    <table>
        <tr>
            <td>
                <form action="CompleteServlet" method="POST">
                    <input type="submit" value="はい">
                </form>
            </td>
            <td>
                <button type="button" onclick="history.back()">いいえ</button>
            </td>
        </tr>
    </table>
  </body>
</html>