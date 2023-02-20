<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register User</title>
</head>
<body>
<!-- when the use click the submit button regUser method in Controller.java will be invoked -->
<form action="regUser" method="post">
<pre>
<h2>User Registration:</h2>
First name: <input type="text" name="firstName"/>
Last name: <input type="text" name="lastName"/>
User name: <input type="text" name="email"/>
Password: <input type="password" name="password"/>
Confirm Password: <input type="password" name="confirmPassword"/>
<input type="submit" value="register"/>
</pre>
</form>
</body>
</html>