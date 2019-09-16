<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html lang="en" >

<head>
  <meta charset="UTF-8">

    <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <title>Simple Form with Icons</title>
  <link rel="stylesheet" href="resources/css/style.css">

	<script type="text/javascript">
		function loginFocus() {
			var element = document.getElementById("login");
			element.focus();
		}
		function checkForm(){
    		var user = document.getElementById("login").value;
    		var pass = document.getElementById("password").value;
    	      if(user.length === 0) {
    	          alert('ユーザ名が入力されていません！');
    	          return false;
    	      }
    	      if(pass.length === 0) {
    	          alert('パスワードが入力されていません！');
    	          return false;
    	      }
    	}
	</script>
</head>

<body onload='loginFocus()'>

  <fieldset>
    <h1>Login</h1>
    <form:form modelAttribute="loginForm" >
		<div class="iconUser"></div>
		<form:input path="loginId" id="login" placeholder="Username"/>
		<div class="iconPassword"></div>
		<form:input path="password" type="password" placeholder="Password"/>
		<input type="submit" value="Enter"  onclick="checkForm();">

    </form:form>
  </fieldset>
</body>

</html>