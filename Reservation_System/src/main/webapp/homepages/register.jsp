<<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客情報登録画面</title>

</head>
<body>
<p>情報登録</p>
    <form action="/Reservation_System/register" method="POST">
        <label>ユーザーID</label><input type="number" name="id"><br>
        <label>お客様名</label><input type="text" name="name"><br>
        <label>メールアドレス</label><input type="text" name="email"><br>
        <label>パスワード</label><input type="text" name="password"><br>
        
        <label>性別</label>
        <input type="radio" name="gender" value="M" checked> 男
        <input type="radio" name="gender" value="F"> 女
        
        
        
        <label>権限</label>
        <input type="radio" name="authority" value="1" checked> 1
        <input type="radio" name="authority" value="2"> 2
        <input type="radio" name="authority" value="3"> 3
        <input type="submit" value="登録">
    </form>
</body>
</html>