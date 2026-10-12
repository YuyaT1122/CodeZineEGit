<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="objects.*,java.util.*"%>
<%
Products product = (Products) session.getAttribute("selectedProduct");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品受注確認</title>
</head>
<body>
ID<%=product.getProductNumber() %>の商品<%=product.getProductName() %>を<%=product.getDeliveryDate() %>に配送する予約を完了しました。
<%=product.getProductPrice() %>円用意してください。
<a href="/Reservation_System/Complete">商品一覧に戻る
<a href="/Reservation_System/Logout">ログイン画面に戻る
</body>
</html>

