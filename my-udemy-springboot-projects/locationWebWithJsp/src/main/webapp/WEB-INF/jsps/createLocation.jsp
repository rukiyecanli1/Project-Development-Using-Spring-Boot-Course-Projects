<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Location</title>
</head>
<body>
<form action = "saveLoc" method="post">

<!--we need to use the same names inside our Location.java here.
this is how we are binding these fields to our model object. 
    private int id;
	private String code;
	private String name;
	private String type;
	
when the request comes into the server, Spring will automatically
create a model object for us and if we follow the same naming
convention here, it will map them all to our location object

All those values here, will be set into this without doing any
additional coding!!! Wonderful! -->

<pre>
Id: <input type="text" name="id"/>
Code: <input type="text" name="code"/>
Name: <input type="text" name="name"/>
Type: Urban <input type="radio" name="type" value="URBAN"/>
      Rural <input type="radio" name="type" value="RURAL"/>
<input type="submit" value="save"/>
</pre>
</form>
<!-- successMsg comes from LocationController class -->
${successMsg}

<a href="displayLocs">View All</a>
</body>
</html>