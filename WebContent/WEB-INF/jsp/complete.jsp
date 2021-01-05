<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>購入完了</title>
  </head>

  <body>
    <h2>お買い上げありがとうございました！</h2>
    <table>
        <tr>
            <td>
                <form action="SearchServlet" method="POST">
                    <input type="submit" value="買い物を続ける">
                </form>
            </td>
            <td>
                <form action="LogoutServlet" method="GET">
                    <input type="submit" value="ログアウト">
                </form>
            </td>
        </tr>
    </table>
  </body>
</html>