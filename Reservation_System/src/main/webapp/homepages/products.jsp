<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="objects.*,java.util.*"%>
    <%

List<Products> list=(List<Products>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
    <title>商品一覧</title>
    <
</head>
<body>商品一覧
 <%if(list !=null && list.size()>0){ %>
 <table border="1">

<tr><th>商品ID</th><th>商品名</th><th>値段</th><th>配達日</th><th></th></tr>

<%for(Products products:list){ %>

<tr>

<td><%=products.getProductNumber() %></td>

<td><%=products.getProductName() %></td>

<td><%=products.getProductPrice() %></td>

<td><%=products.getDeliveryDate() %></td>

<td>
<a href="/Reservation_System/Confirm?ProductNumber=<%=products.getProductNumber() %>">予約</a>
</td>

</tr>

<%} %>
</table>

<%} %>

</body>
</html>