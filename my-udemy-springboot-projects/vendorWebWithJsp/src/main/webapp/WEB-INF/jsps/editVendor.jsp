<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create vendor</title>
</head>
<body>
<form action = "updateVendor" method="post">
<pre>
Id: <input type="text" name="id" value="${vendor.id}" readonly="true"/>
Code: <input type="text" name="code" value="${vendor.code}"/>
Name: <input type="text" name="name" value="${vendor.name}"/>
Type: <select name="type">
 <option  ${vendor.type=='Regular' ? 'selected':''}>Regular</option>
 <option ${vendor.type=='Contract' ? 'selected':''}>Contract</option>
</select>
Name: <input type="text" name="email" value="${vendor.email}"/>
Name: <input type="text" name="phone" value="${vendor.phone}"/>
Name: <input type="text" name="address" value="${vendor.address}"/>
<input type="submit" value="save"/>
</pre>
</form>
</body>
</html>