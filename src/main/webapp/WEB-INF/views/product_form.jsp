<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New/Edit Product</title>
</head>
<body>
	<div align="center">
		<h1>New/Edit Product</h1>
		<form:form action="save" method="POST" modelAttribute="product">
			<table cellpadding="8">
			<form:hidden path="productCode"/>
				<tr>
					<td>Product Name:</td>
					<td><form:input path="productName" /></td>
				</tr>
				<tr>
					<td>Bar Code:</td>
					<td><form:input path="barcode" /></td>
				</tr>
				<tr>
					<td>Unit Price:</td>
					<td><form:input path="unitePrice" /></td>
				</tr>
				<tr>
					<td colspan="8" align="center"><input type="submit"
						value="save" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>