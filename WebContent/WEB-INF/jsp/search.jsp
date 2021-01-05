<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>商品検索</title>
  </head>

  <body>
    <h2>商品検索</h2>
    <span style="color:#ff0000;">${search_result_message}</span>
    <form action="SearchServlet" method="get">
        <input type="text" name="product_name">
        <br>
        カテゴリ：
        <select name="category">
            <c:forEach var="category" items="${sessionScope.category_list}" >
                <option value="${category.categoryId}"><c:out value="${category.categoryName}" /></option>
            </c:forEach>
        </select>
        <br>
        <input type="submit" value="検索">
    </form>
    <c:if test="${not empty requestScope.product_list}">
        <form action="ProductDetail" method="get">
        <br />
        <table border="1">
            <tr>
                <td>商品名</td>
                <td>価格</td>
                <td>在庫</td>
            </tr>
            <c:forEach var="product" items="${requestScope.product_list}">
                <tr>
                    <td><a href="/ec-site/ProductDetailServlet?product_code=${product.productCode}">${product.productName}</a></td>
                    <td align="right"><fmt:formatNumber value="${product.productPrice}" type="CURRENCY" maxFractionDigits="0" currencySymbol="\\" /></td>
                    <td align="right"><fmt:formatNumber value="${product.stockNo}" /></td>
                </tr>
            </c:forEach>
        </table>
        </form>
    </c:if>
  </body>
</html>