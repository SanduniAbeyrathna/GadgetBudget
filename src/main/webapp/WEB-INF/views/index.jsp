<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gadget-Budget Inventory Management System</title>
</head>
<body>
	<div align="center">
		<h1>Products List</h1>
		<h3>
			<a href="new">New Product</a>
		</h3>
		<table border="1" cellpadding="5">
			<tr>
				<th>No.</th>
				<th>Product Name</th>
				<th>Bar Code</th>
				<th>Unit Price</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${listProduct}" var="product" varStatus="status">
				<tr>
					<td>${status.index +1}</td>
					<td>${product.productName}</td>
					<td>${product.barcode}</td>
					<td>${product.unitePrice}</td>
					<td><a href="edit?productCode=${product.productCode}">Edit</a>
						&nbsp;&nbsp; <a href="delete?productCode=${product.productCode}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>