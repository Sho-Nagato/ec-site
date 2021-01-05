<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>商品紹介</title>
  </head>

  <body>
    <h2>商品紹介</h2>
    <br />
    <div style="display:inline-flex">
      <img src="img/${requestScope.product.productImage}" width="250" height="250">
      <div style="padding-left:50px; padding-top:30px">
        <form action="CartServlet" method="post">
          <table border="1" >
            <tr>
              <td>商品名</td>
              <td>${requestScope.product.productName}</td>
            </tr>
            <tr>
              <td>カテゴリ</td>
              <td>${requestScope.product.categoryName}</td>
            </tr>
            <tr>
              <td>価格</td>
              <td align="right"><fmt:formatNumber value="${requestScope.product.productPrice}" type="CURRENCY" maxFractionDigits="0" currencySymbol="\\" /></td>
            </tr>
            <tr>
              <td>在庫</td>
              <td align="right"><fmt:formatNumber value="${requestScope.product.stockNo}" /></td>
            </tr>
            <tr>
              <td>商品紹介</td>
              <td>${requestScope.product.productMessage}</td>
            </tr>
          </table>
          <table>
            <tr>
              <td>
                                     個数
                <select name="number">
                  <c:forEach begin="1" end="${requestScope.product.stockNo}" var="i">
                    <option><c:out value="${i}" /></option>
                  </c:forEach>
                </select>
              </td>
              <td>
                <input type="hidden" name="product_code" value="${product.productCode}">
                <input type="submit" value="カートへ">
              </td>
              <td>
                <button type="button" onclick="history.back()">戻る</button>
              </td>
            </tr>
          </table>
        </form>
      </div>
    </div>
  </body>
</html>