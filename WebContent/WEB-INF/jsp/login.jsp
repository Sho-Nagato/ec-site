<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>ログイン</title>
  </head>

  <body>
    <h2>ログイン</h2>
    <span style="color:#ff0000;">${error_message}</span>
    <form action="LoginServlet" method="post">
      名前：
      <input type="text" name="login_cd">
      <br>
      パスワード：
      <input type="password" name="login_pw">
      <br>
      <input type="submit" value="ログイン">
    </form>
  </body>
</html>