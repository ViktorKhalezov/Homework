<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Product List</title>
    </head>
    <body>
        <h1>Product List</h1>
        <ul>
           <c:forEach var="product" items="${products}">
            <li>${product.toString()}</li>
           <br>
           </c:forEach>
        </ul>
        <br>
        <c:url var="addProductUrl" value="/products/add_product"/>
        <a href="${addProductUrl}">Add Product</a>
    </body>
</html>