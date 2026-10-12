<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>ログイン画面</title>
</head>
<body>
  <h1>IDとパスワードを入力してください。</h1>
<form action="/Reservation_System/login"method="post">
  Id:<input type="text" name = "id" size="10"/><br/>
  Password:<input type="text" name="password"size="10"/>
<br/>
<input type="submit" value="ログイン"/>
</form>
<a href ="/Reservation_System/homepages/register.jsp">会員登録</a>
</body>
</html>
